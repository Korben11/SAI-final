package broker.gui;

import jmsmessenger.models.GraduationApprovalReply;
import jmsmessenger.models.GraduationApprovalRequest;
import jmsmessenger.models.GraduationClientReply;
import jmsmessenger.models.GraduationClientRequest;

import java.text.MessageFormat;

public class ListViewLine {

    private GraduationApprovalRequest graduationApprovalRequest;
    private GraduationApprovalReply graduationApprovalReply;
    private GraduationClientRequest graduationClientRequest;
    private GraduationClientReply graduationClientReply;

    public ListViewLine(GraduationApprovalRequest graduationApprovalRequest, GraduationClientRequest graduationClientRequest) {
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
