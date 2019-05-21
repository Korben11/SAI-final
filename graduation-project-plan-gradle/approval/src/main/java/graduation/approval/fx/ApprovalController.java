package graduation.approval.fx;

import graduation.approval.model.GraduationApprovalReply;
import graduation.approval.model.GraduationApprovalRequest;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;

@SuppressWarnings("unused")
class ApprovalController {

    private final String approvalName;
    @FXML
    private ListView<ListViewLine> lvApprovalRequestReply;

    @FXML
    private CheckBox cbApproved;

    public ApprovalController(String approvalName){
        this.approvalName = approvalName;
   }

    @FXML
    private void btnSendApprovalReplyClicked(){
        boolean isAccepted = cbApproved.isSelected();
        System.out.println(isAccepted);
        GraduationApprovalReply graduationApprovalReply = new GraduationApprovalReply(isAccepted, approvalName);

        ListViewLine listViewLine = lvApprovalRequestReply.getSelectionModel().getSelectedItem();
        if (listViewLine != null) {
            GraduationApprovalRequest graduationApprovalRequest = listViewLine.getApprovalRequest();
            /**
             * TO DO: send the graduationApprovalReply as THE REPLY for graduationApprovalRequest
             */
            System.out.println("Approval application " + approvalName + " is sending " + graduationApprovalReply + " for " + graduationApprovalRequest);
        } else {
            System.err.println("Please select one request in the list!");
        }
    }

    /**
     * Adds a new ListViewLine to lvApprovalRequestReply with graduationApprovalRequest
     * @param graduationApprovalRequest
     */
    private void addApprovalRequest(GraduationApprovalRequest graduationApprovalRequest){
        ListViewLine listViewLine = new ListViewLine(graduationApprovalRequest);
        this.lvApprovalRequestReply.getItems().add(listViewLine);
    }

}
