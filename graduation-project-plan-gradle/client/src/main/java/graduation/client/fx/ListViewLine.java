package graduation.client.fx;

import jmsmessenger.models.GraduationClientReply;
import jmsmessenger.models.GraduationClientRequest;

class ListViewLine {
	
	private GraduationClientRequest clientRequest;
	private GraduationClientReply clientReply = null;
	
	public ListViewLine(GraduationClientRequest graduationClientRequest) {
		setClientRequest(graduationClientRequest);
	}	
	
	public GraduationClientRequest getClientRequest() {
		return clientRequest;
	}
	
	private void setClientRequest(GraduationClientRequest clientRequest) {
		this.clientRequest = clientRequest;
	}

	public GraduationClientReply getClientReply() {
		return clientReply;
	}

	public void setClientReply(GraduationClientReply clientReply) {
		this.clientReply = clientReply;
	}

	@Override
	public String toString() {
	   return clientRequest.toString() + "  --->  " + ((clientReply !=null)? clientReply.toString():"waiting for reply...");
	}
	
}
