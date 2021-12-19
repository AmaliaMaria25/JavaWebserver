import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;


public class Controller {

    private WebserverRunner webserverRunner = new WebserverRunner();

    public void startServer(ActionEvent actionEvent) {
        System.out.println("Starting server...");
        webserverRunner.main(new String[]{""});
        Parent fxml = null;
        try {
            fxml = FXMLLoader.load(getClass().getResource("/App2.fxml"));
            Scene scene = new Scene(fxml);
            //scene.setFill(Color.TRANSPARENT);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void stopServer(ActionEvent actionEvent) {
        System.out.println("The server stopped!");
        webserverRunner.stop();

    }
}
