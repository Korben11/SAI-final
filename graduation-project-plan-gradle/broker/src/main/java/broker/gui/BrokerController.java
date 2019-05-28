package broker.gui;

import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import jmsmessenger.gateways.AsyncReceiverGateway;
import jmsmessenger.gateways.IRequest;

import java.net.URL;
import java.util.*;


public class BrokerController implements Initializable {

    public ListView<ListViewLine> lvBroker;

    // Map to store bank request messageId (correlationId for reply from bank)
    public Map<IRequest, ListViewLine> map;

    // Gateways
    private AsyncReceiverGateway clientGateway;
//    private ScatterGetter scatterGetter;

    public BrokerController() {

        // init map
        map = new HashMap<>();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
