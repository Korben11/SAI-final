package graduation.client.fx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;

public class ClientMain extends Application {

    private static String name;

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });

        URL url  = getClass().getClassLoader().getResource("client.fxml");
        if (url != null) {
            FXMLLoader loader = new FXMLLoader(url);

            // Create and set controller instance

            ClientController controller = new ClientController(name);
            // Set it in the FXMLLoader
            loader.setController(controller);

            Parent root = loader.load();

            primaryStage.setTitle("Graduation Approval Client " + name);

            primaryStage.setOnCloseRequest(t -> {
                Platform.exit();
                System.exit(0);
            });
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/client.png")));
            primaryStage.setScene(new Scene(root, 500, 300));
            primaryStage.show();
        }else {
            System.err.println("Error: Could not load frame from client.fxml");
        }
    }


    public static void main(String[] args) {
        if (args.length <  1){
            throw new IllegalArgumentException("Argument is missing. You must provide one argument: CLIENT_APPLICATION_NAME");
        }
        name = args[0];
        launch(args);
    }
}
