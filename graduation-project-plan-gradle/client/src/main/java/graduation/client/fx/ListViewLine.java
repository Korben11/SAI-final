package graduation.client.fx;


import graduation.client.model.GraduationClientReply;
import graduation.client.model.GraduationClientRequest;

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

	@Override
	public String toString() {
	   return clientRequest.toString() + "  --->  " + ((clientReply !=null)? clientReply.toString():"waiting for reply...");
	}
	
}
