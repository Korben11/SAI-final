package broker.gui;

import jmsmessenger.models.GraduationApprovalReply;
import jmsmessenger.models.GraduationApprovalRequest;
import jmsmessenger.models.GraduationClientReply;
import jmsmessenger.models.GraduationClientRequest;

import java.text.MessageFormat;

public class ListViewLine {

    public GraduationApprovalRequest getGraduationApprovalRequest() {
        return graduationApprovalRequest;
    }

    public void setGraduationApprovalRequest(GraduationApprovalRequest graduationApprovalRequest) {
        this.graduationApprovalRequest = graduationApprovalRequest;
    }

    public GraduationApprovalReply getGraduationApprovalReply() {
        return graduationApprovalReply;
    }

    public void setGraduationApprovalReply(GraduationApprovalReply graduationApprovalReply) {
        this.graduationApprovalReply = graduationApprovalReply;
    }

    public GraduationClientRequest getGraduationClientRequest() {
        return graduationClientRequest;
    }

    public void setGraduationClientRequest(GraduationClientRequest graduationClientRequest) {
        this.graduationClientRequest = graduationClientRequest;
    }

    public GraduationClientReply getGraduationClientReply() {
        return graduationClientReply;
    }

    public void setGraduationClientReply(GraduationClientReply graduationClientReply) {
        this.graduationClientReply = graduationClientReply;
    }

    private GraduationApprovalRequest graduationApprovalRequest;
    private GraduationApprovalReply graduationApprovalReply;
    private GraduationClientRequest graduationClientRequest;
    private GraduationClientReply graduationClientReply;

    public ListViewLine(GraduationClientRequest graduationClientRequest, GraduationApprovalRequest graduationApprovalRequest) {
        this.graduationApprovalRequest = graduationApprovalRequest;
        this.graduationClientRequest = graduationClientRequest;
        this.graduationClientReply = null;
        this.graduationApprovalReply = null;
    }

    @Override
    public String toString() {

        String reply = "waiting...";
        String format = "{0} ---> {1}";

        if (graduationClientReply != null) {
            reply = graduationClientReply.toString();
        }
        return MessageFormat.format(format, graduationClientRequest.toString(), reply);

    }
}
