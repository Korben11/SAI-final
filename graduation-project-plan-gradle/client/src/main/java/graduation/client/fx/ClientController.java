package graduation.client.fx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import jmsmessenger.models.GraduationClientRequest;


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


    public ClientController(){

    }

    @FXML
    private void btnSendRequestClicked(){
        // create the GraduationClientRequest
        int studentNumber = Integer.parseInt(tfStudentNumber.getText());
        String company = tfCompany.getText();
        String projectName = tfProjectName.getText();
        GraduationClientRequest graduationClientRequest = new GraduationClientRequest(studentNumber,company,projectName);

        //create the ListViewLine line with the request and add it to lvRequestReply
        ListViewLine listViewLine = new ListViewLine(graduationClientRequest);
        this.lvRequestReply.getItems().add(listViewLine);
        /**
         * To DO: send the graduationClientRequest
         */

    }


    /**
     * This method returns the line of lvMessages which contains the given request.
     * @param request GraduationClientRequest for which the line of lvMessages should be found and returned
     * @return The ListViewLine line of lvMessages which contains the given request
     */
    private ListViewLine getRequestReply(GraduationClientRequest request) {
        for (ListViewLine listViewLine: lvRequestReply.getItems()) {
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
