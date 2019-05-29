package broker.gui;

import content.enricher.StudentInfo;
import content.enricher.StudentInfoContentEnricher;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import jmsmessenger.gateways.AsyncReceiverGateway;
import jmsmessenger.gateways.IRequest;
import jmsmessenger.gateways.IResponse;
import jmsmessenger.models.GraduationApprovalRequest;
import jmsmessenger.models.GraduationClientReply;
import jmsmessenger.models.GraduationClientRequest;
import jmsmessenger.serializers.GsonSerializer;
import scattergetter.ScatterGetter;

import static jmsmessenger.Constants.STUDENT_CLIENT_REQUEST_QUEUE;
import static jmsmessenger.Constants.HTTP_LOCALHOST_8080_ADMINISTRATION_REST_STUDENTS;

import java.net.URL;
import java.util.*;


public class BrokerController implements Initializable {

    public ListView<ListViewLine> lvBroker;

    public Map<GraduationApprovalRequest, ListViewLine> map;

    // Gateways
    private AsyncReceiverGateway studentGateway;
    private ScatterGetter scatterGetter;

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
                int passed = scatterGetter.applyForApproval(graduationApprovalRequest);

                if (passed > 0)
                    return;

                // refuse
                GraduationClientReply graduationClientReply = new GraduationClientReply(false, "Not enough ECs: " + graduationApprovalRequest.getEcs());
                listViewLine.setGraduationClientReply(graduationClientReply);
                studentGateway.sendReply(request, graduationClientReply);
                lvBroker.refresh();
            }
        };

        scatterGetter = new ScatterGetter() {
            @Override
            public void onResponseSelected(GraduationApprovalRequest request, GraduationClientReply response) {
                ListViewLine listViewLine = map.get(request);
                listViewLine.setGraduationClientReply(response);
                lvBroker.refresh();

                // send reply to student
                studentGateway.sendReply(listViewLine.getGraduationClientRequest(), response);
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
