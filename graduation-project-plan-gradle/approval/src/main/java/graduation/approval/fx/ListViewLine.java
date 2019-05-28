package graduation.approval.fx;


import jmsmessenger.models.GraduationApprovalReply;
import jmsmessenger.models.GraduationApprovalRequest;

/**
 * This class is an item/line for a ListViewLine. It makes it possible to put both approvalRequest and approvalReply object in one item in a ListViewLine.
 */
class ListViewLine {
	
	private GraduationApprovalRequest approvalRequest;
	private GraduationApprovalReply approvalReply = null;
	
	public ListViewLine(GraduationApprovalRequest graduationApprovalRequest) {
		setApprovalRequest(graduationApprovalRequest);
	}
	
	public GraduationApprovalRequest getApprovalRequest() {
		return approvalRequest;
	}
	
	private void setApprovalRequest(GraduationApprovalRequest approvalRequest) {
		this.approvalRequest = approvalRequest;
	}


    /**
     * This method defines how one line is shown in the ListViewLine.
     * @return
     *  a) if approvalReply is null, then this item will be shown as "approvalRequest.toString ---> waiting for reply..."
     *  b) if approvalReply is not null, then this item will be shown as "approvalRequest.toString ---> approvalReply.toString"
     */
	@Override
	public String toString() {
	   return approvalRequest.toString() + "  --->  " + ((approvalReply !=null)? approvalReply.toString():"waiting for reply...");
	}
	
}
