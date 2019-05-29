package scattergetter;

import jmsmessenger.gateways.IResponse;
import jmsmessenger.models.GraduationApprovalReply;
import jmsmessenger.models.GraduationClientReply;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public abstract class Aggregator {

    public static int id = 0;

    private Map<Integer, Integer> mapAggIdToSentCount;
    private Map<Integer, ArrayList<IResponse>> mapAggIdToResponses;

    public Aggregator() {
        mapAggIdToSentCount = new HashMap<>();
        mapAggIdToResponses = new HashMap<>();
    }

    public void addAggregator(Integer aggregationId, Integer sentCount) {
        mapAggIdToSentCount.put(aggregationId, sentCount);
        mapAggIdToResponses.put(aggregationId, new ArrayList<>());
    }

    public void addReply(IResponse response, Integer aggregationId) {
        mapAggIdToResponses.get(aggregationId).add(response);
        checkReplies(aggregationId);
    }

    private void checkReplies(Integer aggregatorId) {
        if (!mapAggIdToSentCount.get(aggregatorId).equals(this.getNrOfReplies(aggregatorId))) {
            return;
        }

        GraduationClientReply graduationClientReply = null;
        for (IResponse response : mapAggIdToResponses.get(aggregatorId)) {
            if (graduationClientReply == null) {
                graduationClientReply = new GraduationClientReply(((GraduationApprovalReply) response).isApproved(), ((GraduationApprovalReply) response).getName());
                continue;
            }
            GraduationApprovalReply graduationApprovalReply = (GraduationApprovalReply) response;
            if (graduationApprovalReply.isApproved()) {
                continue;
            }
            graduationClientReply.setApproved(false);
            graduationClientReply.setRejectedBy(graduationClientReply.getRejectedBy() + " " + graduationApprovalReply.getName());
        }

        onAllRepliesReceived(graduationClientReply, aggregatorId);
    }

    private Integer getNrOfReplies(Integer aggregatorId) {
        return mapAggIdToResponses.get(aggregatorId).size();
    }

    public abstract void onAllRepliesReceived(GraduationClientReply response, Integer aggregationId);
}
