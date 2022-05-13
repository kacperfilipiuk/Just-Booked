package com.example.projekt_z_javy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    private static Stage stg;

    @Override
    public void start(Stage primaryStage) throws IOException {
        int widthScene, hightScene;
        widthScene = 262;
        hightScene = 310;
        stg = primaryStage;
        primaryStage.setResizable(false);
        Parent root  = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login-view.fxml")));
        primaryStage.setTitle("Login");
        primaryStage.setScene( new Scene(root, widthScene, hightScene)); //254x345 rejestracja
        primaryStage.show();
    }

    public void changeScene(String fxml) throws IOException{
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }


    public static void main(String[] args) {
        launch(args);
    }
}