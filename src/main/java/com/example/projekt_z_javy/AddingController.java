package com.example.projekt_z_javy;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddingController implements Initializable {

    private ObservableList<Integer> hourList = FXCollections.observableArrayList(8,10,12,14,16,18);
    private ObservableList<String> roomList = FXCollections.observableArrayList();

    LocalDate myDate = null;


    @FXML
    private ChoiceBox roomChoiceBox;
    @FXML
    private ChoiceBox hourChoiceBox;
    @FXML
    private DatePicker dataPicker;
    @FXML
    private Label Menu;

    @FXML
    private Label MenuClose;

    @FXML
    private AnchorPane slider;

    private Stage stage;
    private Scene scene;
    private Parent root;

    String query = "";
    ResultSet resultSet = null;
    Rezerwacja rezerwacja = null;

    public static final String url = "jdbc:postgresql://ec2-54-228-218-84.eu-west-1.compute.amazonaws.com:5432/de710thmop4rit";
    public static final String user = "dpbwovovhjsruv";
    public static final String password = "20482d0224e13b90ddcba4fd4e828746739cadef005e44a9bbad4acb6a7b64cf";


    @FXML
    private void exit(ActionEvent e) {
        System.exit(0);
    }

    public void fillRoomComboBox(){
        String query = "SELECT nazwa FROM pokoje";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {
            ResultSet rs = pst.executeQuery();

            while (rs.next()){
                roomList.add(rs.getString("nazwa"));
            }
            pst.close();
            rs.close();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgreSQL.class.getName());
            lgr.log(Level.SEVERE,ex.getMessage(),ex);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fillRoomComboBox();

        hourChoiceBox.setItems(hourList);
        roomChoiceBox.setItems(roomList);


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
    

    public void userLogout(ActionEvent actionEvent) throws SQLException, IOException {
        System.out.println("Wylogowuje...");
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void addReservation(ActionEvent actionEvent) throws IOException{
        root = FXMLLoader.load(getClass().getResource("lobbyNew.fxml"));
        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }
    @FXML
    public void deleteReservation(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("lobbyDelete.fxml"));
        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void myReservation(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("lobbyHistory.fxml"));
        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void otherReservation(ActionEvent actionEvent) throws IOException{
        root = FXMLLoader.load(getClass().getResource("lobbyOther.fxml"));
        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void getDate(ActionEvent actionEvent) {
        myDate = dataPicker.getValue();
        System.out.println(myDate);
    }
}