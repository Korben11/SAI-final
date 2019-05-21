package graduation.approval.fx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;

public class ApprovalMain extends Application {

    private static String approvalName = null;

    /**
     * @param args One argument is needed: the name of the approval application name (e.g, Grad.Coordinator or Bert or Chung or Rafayel).
     */
    public static void main(String[] args) {
        if (args.length <  1){
            throw new IllegalArgumentException("Argument is missing. You must provide one argument: APPROVAL_APPLICATION_NAME");
        }
        approvalName =args[0];
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });

        URL url  = getClass().getClassLoader().getResource( "approval.fxml" );
        if (url != null) {


            FXMLLoader loader = new FXMLLoader(url);

            // Create and set controller instance

            ApprovalController controller = new ApprovalController(approvalName);
            // Set it in the FXMLLoader
            loader.setController(controller);


            Parent root = loader.load();
            primaryStage.setTitle("Approval - " + approvalName);

            primaryStage.setOnCloseRequest(t -> {
                Platform.exit();
                System.exit(0);
            });

            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/approval.png")));
            primaryStage.setScene(new Scene(root, 360, 270));
            primaryStage.show();
        }
        else {
            System.err.println("Error: Could not load frame from approval.fxml");
        }
    }
}
