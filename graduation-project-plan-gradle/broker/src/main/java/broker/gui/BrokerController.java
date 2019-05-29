package broker.gui;

import content.enricher.StudentInfo;
import content.enricher.StudentInfoContentEnricher;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import jmsmessenger.Constants;
import jmsmessenger.gateways.AsyncReceiverGateway;
import jmsmessenger.gateways.AsyncSenderGateway;
import jmsmessenger.gateways.IRequest;
import jmsmessenger.gateways.IResponse;
import jmsmessenger.models.GraduationApprovalReply;
import jmsmessenger.models.GraduationApprovalRequest;
import jmsmessenger.models.GraduationClientReply;
import jmsmessenger.models.GraduationClientRequest;
import jmsmessenger.serializers.GsonSerializer;

import static jmsmessenger.Constants.STUDENT_CLIENT_REQUEST_QUEUE;
import static jmsmessenger.Constants.HTTP_LOCALHOST_8080_ADMINISTRATION_REST_STUDENTS;

import java.net.URL;
import java.util.*;


public class BrokerController implements Initializable {

    public ListView<ListViewLine> lvBroker;

    // Map to store bank request messageId (correlationId for reply from bank)
    public Map<IRequest, ListViewLine> map;

    // Gateways
    private AsyncReceiverGateway studentGateway;
    private AsyncSenderGateway approvalGateway;
//    private ScatterGetter scatterGetter;

    private StudentInfoContentEnricher studentInfoContentEnricher;

    public BrokerController() {
        // init map
        map = new HashMap<>();

        // init enricher
        studentInfoContentEnricher = new StudentInfoContentEnricher(HTTP_LOCALHOST_8080_ADMINISTRATION_REST_STUDENTS);

        // init gateways
        studentGateway = new AsyncReceiverGateway(new GsonSerializer(GraduationClientRequest.class, GraduationClientReply.class), STUDENT_CLIENT_REQUEST_QUEUE, null) {
            @Override
            public void onMessageArrived(IRequest request, IResponse response, Integer aggregationId) {
                GraduationClientRequest graduationClientRequest = (GraduationClientRequest) request;

                // content enricher
                GraduationApprovalRequest graduationApprovalRequest = enrichStudentInfo(graduationClientRequest);

                // add to list view
                ListViewLine listViewLine = new ListViewLine(graduationClientRequest, graduationApprovalRequest);
                lvBroker.getItems().add(listViewLine);

                // map
                map.put(graduationApprovalRequest, listViewLine);

                // send to approval
                for (Constants.APPROVAL approval : Constants.APPROVAL.values()) {
                    approvalGateway.sendRequest(graduationApprovalRequest, approval + Constants.APPROVAL_CLIENT_REQUEST_QUEUE, null);
                }
            }
        };

        approvalGateway = new AsyncSenderGateway(new GsonSerializer(GraduationApprovalRequest.class, GraduationApprovalReply.class), Constants.APPROVAL_CLIENT_RESPONSE_QUEUE, null) {
            @Override
            public void onMessageArrived(IRequest request, IResponse response, Integer aggregationId) {
                // get and add response to list view
                ListViewLine listViewLine = map.get(request);
                GraduationApprovalReply graduationApprovalReply = (GraduationApprovalReply) response;
                listViewLine.setGraduationApprovalReply(graduationApprovalReply);
                GraduationClientReply graduationClientReply = new GraduationClientReply(graduationApprovalReply.isApproved(), graduationApprovalReply.getName());
                listViewLine.setGraduationClientReply(graduationClientReply);

                lvBroker.refresh();

                // send reply to student
                studentGateway.sendReply(listViewLine.getGraduationClientRequest(), graduationClientReply);
            }
        };
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private GraduationApprovalRequest enrichStudentInfo(GraduationClientRequest graduationClientRequest) {

        StudentInfo studentInfo = studentInfoContentEnricher.getStudentInfo(graduationClientRequest.getStudentNumber());
        if (studentInfo == null) {
            // TODO: throw new exception, service not working...
            return null;
        }

        return new GraduationApprovalRequest(
                graduationClientRequest.getStudentNumber(),
                graduationClientRequest.getCompany(),
                graduationClientRequest.getProjectTitle(),
                studentInfo.getGraduationPhaseECs(),
                studentInfo.getMentor());

    }

}
