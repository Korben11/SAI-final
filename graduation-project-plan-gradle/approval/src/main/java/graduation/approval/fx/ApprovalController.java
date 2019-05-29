package graduation.approval.fx;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import jmsmessenger.gateways.AsyncReceiverGateway;
import jmsmessenger.gateways.IRequest;
import jmsmessenger.gateways.IResponse;
import jmsmessenger.models.GraduationApprovalReply;
import jmsmessenger.models.GraduationApprovalRequest;
import jmsmessenger.serializers.GsonSerializer;

import static jmsmessenger.Constants.*;

@SuppressWarnings("unused")
class ApprovalController {

    AsyncReceiverGateway gateway;

    private final String approvalName;
    @FXML
    private ListView<ListViewLine> lvApprovalRequestReply;

    @FXML
    private CheckBox cbApproved;

    public ApprovalController(String approvalName) {
        this.approvalName = approvalName;
        gateway = new AsyncReceiverGateway(new GsonSerializer(GraduationApprovalRequest.class, GraduationApprovalReply.class), approvalName + APPROVAL_CLIENT_REQUEST_QUEUE, APPROVAL_CLIENT_RESPONSE_QUEUE) {
            @Override
            public void onMessageArrived(IRequest request, IResponse response, Integer aggregationId) {
                ListViewLine listViewLine = new ListViewLine((GraduationApprovalRequest) request);
                lvApprovalRequestReply.getItems().add(listViewLine);
            }
        };
    }

    @FXML
    private void btnSendApprovalReplyClicked() {
        if (!lvApprovalRequestReply.hasProperties()) return;

        boolean isAccepted = cbApproved.isSelected();
        System.out.println(isAccepted);
        GraduationApprovalReply graduationApprovalReply = new GraduationApprovalReply(isAccepted, approvalName);

        ListViewLine listViewLine = lvApprovalRequestReply.getSelectionModel().getSelectedItem();
        if (listViewLine == null) {
            System.err.println("Please select one request in the list!");
            return;
        }

        if (listViewLine.getApprovalReply() != null) {
            System.err.println("Reply already send!");
            return;
        }

        GraduationApprovalRequest graduationApprovalRequest = listViewLine.getApprovalRequest();
        listViewLine.setApprovalReply(graduationApprovalReply);

        gateway.sendReply(listViewLine.getApprovalRequest(), graduationApprovalReply);

        lvApprovalRequestReply.refresh();

        System.out.println("Approval application " + approvalName + " is sending " + graduationApprovalReply + " for " + graduationApprovalRequest);
    }

    /**
     * Adds a new ListViewLine to lvApprovalRequestReply with graduationApprovalRequest
     *
     * @param graduationApprovalRequest
     */
    private void addApprovalRequest(GraduationApprovalRequest graduationApprovalRequest) {
        ListViewLine listViewLine = new ListViewLine(graduationApprovalRequest);
        this.lvApprovalRequestReply.getItems().add(listViewLine);
    }

}
