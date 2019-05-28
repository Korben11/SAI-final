package graduation.client.fx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import jmsmessenger.gateways.AsyncSenderGateway;
import jmsmessenger.gateways.IRequest;
import jmsmessenger.gateways.IResponse;
import jmsmessenger.models.GraduationClientReply;
import jmsmessenger.models.GraduationClientRequest;
import jmsmessenger.serializers.GsonSerializer;

import static jmsmessenger.Constants.STUDENT_CLIENT_REQUEST_QUEUE;
import static jmsmessenger.Constants.STUDENT_CLIENT_RESPONSE_QUEUE;


import java.net.URL;
import java.util.ResourceBundle;

@SuppressWarnings("WeakerAccess")
public class ClientController implements Initializable {

    @FXML
    private TextField tfStudentNumber;
    @FXML
    private TextField tfCompany;
    @FXML
    private TextField tfProjectName;
    @FXML
    private ListView<ListViewLine> lvRequestReply;

    private static String clientId;

    AsyncSenderGateway gateway;

    public ClientController(String clientId) {
        this.clientId = clientId;
        gateway = new AsyncSenderGateway(new GsonSerializer(GraduationClientRequest.class, GraduationClientReply.class), STUDENT_CLIENT_RESPONSE_QUEUE + clientId, STUDENT_CLIENT_REQUEST_QUEUE) {
            @Override
            public void onMessageArrived(IRequest request, IResponse response, Integer aggregationId) {
                ListViewLine listViewLine = getRequestReply((GraduationClientRequest) request);
                listViewLine.setClientReply((GraduationClientReply) response);
                lvRequestReply.refresh();
            }
        };
    }

    @FXML
    private void btnSendRequestClicked() {
        // create the GraduationClientRequest
        int studentNumber = Integer.parseInt(tfStudentNumber.getText());
        String company = tfCompany.getText();
        String projectName = tfProjectName.getText();
        GraduationClientRequest graduationClientRequest = new GraduationClientRequest(studentNumber, company, projectName);

        //create the ListViewLine line with the request and add it to lvRequestReply
        ListViewLine listViewLine = new ListViewLine(graduationClientRequest);
        this.lvRequestReply.getItems().add(listViewLine);

        gateway.sendRequest(graduationClientRequest, null, null);
    }


    /**
     * This method returns the line of lvMessages which contains the given request.
     *
     * @param request GraduationClientRequest for which the line of lvMessages should be found and returned
     * @return The ListViewLine line of lvMessages which contains the given request
     */
    private ListViewLine getRequestReply(GraduationClientRequest request) {
        for (ListViewLine listViewLine : lvRequestReply.getItems()) {
            if (listViewLine.getClientRequest() == request) {
                return listViewLine;
            }
        }
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tfStudentNumber.setText("123");
        tfCompany.setText("Philips");
        tfProjectName.setText("New website");
    }
}
