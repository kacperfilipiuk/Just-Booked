package com.example.mainApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Client extends Application {
    private static Stage stg;

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
            Scene scene = new Scene(root);
            stg = primaryStage;
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.initStyle(StageStyle.DECORATED.UNDECORATED);
            primaryStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    /*-----------------------------------------------------------------------------*/

    public void changeScene(String fxml) throws IOException {
        if (fxml.contains("lobby")) {
            stg.hide();
            Parent pane = FXMLLoader.load(getClass().getClassLoader().getResource(fxml));
            stg.getScene().setRoot(pane);
        } else {
            Parent pane = FXMLLoader.load(getClass().getClassLoader().getResource(fxml));
            stg.getScene().setRoot(pane);
        }
    }
}