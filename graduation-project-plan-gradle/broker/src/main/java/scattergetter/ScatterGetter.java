package scattergetter;

import jmsmessenger.Constants;
import jmsmessenger.gateways.AsyncSenderGateway;
import jmsmessenger.gateways.IRequest;
import jmsmessenger.gateways.IResponse;
import jmsmessenger.models.GraduationApprovalReply;
import jmsmessenger.models.GraduationApprovalRequest;
import jmsmessenger.models.GraduationClientReply;
import jmsmessenger.serializers.GsonSerializer;

import java.util.HashMap;
import java.util.Map;

public abstract class ScatterGetter {

    private AsyncSenderGateway approvalGateway;
    private ApprovalRecipientList recipientList;
    private Aggregator aggregator;

    private Map<Integer, GraduationApprovalRequest> mapAggregationIdToRequest;

    public ScatterGetter() {
        mapAggregationIdToRequest = new HashMap<>();

        aggregator = new Aggregator() {
            @Override
            public void onAllRepliesReceived(GraduationClientReply response, Integer aggregationId) {
                onResponseSelected(mapAggregationIdToRequest.get(aggregationId), response);
            }
        };

        approvalGateway = new AsyncSenderGateway(new GsonSerializer(GraduationApprovalRequest.class, GraduationApprovalReply.class), Constants.APPROVAL_CLIENT_RESPONSE_QUEUE, null) {
            @Override
            public void onMessageArrived(IRequest request, IResponse response, Integer aggregationId) {
                if (!mapAggregationIdToRequest.containsKey(aggregationId)) mapAggregationIdToRequest.put(aggregationId, (GraduationApprovalRequest) request);
                aggregator.addReply(response, aggregationId);
            }
        };

        recipientList = new ApprovalRecipientList(approvalGateway);
    }

    public int applyForApproval(GraduationApprovalRequest request) {
        Aggregator.id++;
        int passed = recipientList.sendRequest(request, Aggregator.id);
        if (passed == 0) {
            return passed;
        }
        aggregator.addAggregator(Aggregator.id, passed);
        return passed;
    }

    public abstract void onResponseSelected(GraduationApprovalRequest request, GraduationClientReply response);
}
