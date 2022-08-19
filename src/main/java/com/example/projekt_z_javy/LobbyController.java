package com.example.projekt_z_javy;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LobbyController implements Initializable {

    String nameOfUser;

    @FXML
    private Label labelName;
    @FXML
    private Label Menu;

    @FXML
    private Label MenuClose;

    @FXML
    private AnchorPane slider;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        slider.setTranslateX(-200);

        Menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(0);
            slide.play();

            slider.setTranslateX(-200);

            slide.setOnFinished((ActionEvent e) -> {
                Menu.setVisible(false);
                MenuClose.setVisible(true);
            });
        });

        MenuClose.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(-200);
            slide.play();

            slider.setTranslateX(0);

            slide.setOnFinished((ActionEvent e) -> {
                Menu.setVisible(true);
                MenuClose.setVisible(false);
            });
        });
    }

    public void displayName(String userName){
        labelName.setText("Witaj " + userName + " !");
    }

    public void getName(String userName){
        nameOfUser = userName;
    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void addReservation(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("lobbyNew.fxml"));
        root = loader.load();
        AddingController addingController = loader.getController();
        addingController.getUserName(nameOfUser);
        //root = FXMLLoader.load(getClass().getResource("lobbyNew.fxml"));
        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }
    @FXML
    public void deleteReservation(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("lobbyDelete.fxml"));
        root = loader.load();
        DeletingController deletingController = loader.getController();
        deletingController.getUserName1(nameOfUser);
        //root = FXMLLoader.load(getClass().getResource("lobbyDelete.fxml"));
        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void myReservation(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("lobbyHistory.fxml"));
        root = loader.load();
        TableViewController tableViewController = loader.getController();
        tableViewController.getUserName3(nameOfUser);
        //root = FXMLLoader.load(getClass().getResource("lobbyNew.fxml"));
        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void otherReservation(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("lobbyOther.fxml"));
        root = loader.load();
        OtherController otherController = loader.getController();
        otherController.getUserName2(nameOfUser);
        //root = FXMLLoader.load(getClass().getResource("lobbyOther.fxml"));
        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    @FXML
    private void exit(ActionEvent e) {
        System.exit(0);
    }

}
