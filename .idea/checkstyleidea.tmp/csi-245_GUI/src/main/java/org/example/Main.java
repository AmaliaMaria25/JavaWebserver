package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import 

public class Main extends Application {

    public static void main(String[] args) { launch(args);  }

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("Chef`s Squad");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        */
        //System.out.println("Working Directory = " + System.getProperty("user.dir"));
        Parent fxml=FXMLLoader.load(getClass().getResource("/App.fxml"));
        Scene scene=new Scene(fxml);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();

        WebserverRunner

    }


}
