package com.example.projekt_z_javy;

import com.jfoenix.controls.JFXTextArea;
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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeletingController implements Initializable {

    private ObservableList<Integer> reservationList = FXCollections.observableArrayList();

    @FXML
    private Label Menu;

    @FXML
    private Label MenuClose;

    @FXML
    private ChoiceBox reservationChoiceBox;

    @FXML
    private AnchorPane slider;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button iniButton;

    @FXML
    private TextField textFieldRoom;

    @FXML
    private TextField textFieldData;

    @FXML
    private TextField textFieldHour;


    String query = "";
    ResultSet resultSet = null;
    Rezerwacja rezerwacja = null;
    String myUserName;

    int id_uzyt;


    public static final String url = "jdbc:postgresql://ec2-54-228-218-84.eu-west-1.compute.amazonaws.com:5432/de710thmop4rit";
    public static final String user = "dpbwovovhjsruv";
    public static final String password = "20482d0224e13b90ddcba4fd4e828746739cadef005e44a9bbad4acb6a7b64cf";

    public void getUserName1(String username){
        myUserName = username;
        id_uzyt = JavaPostgreSQL_adding.getUserId(myUserName);
        System.out.println(id_uzyt);
    }

    @FXML
    private void exit(ActionEvent e) {
        System.exit(0);
    }



    public void userLogout(ActionEvent actionEvent) throws SQLException, IOException {
        System.out.println("Wylogowuje...");
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    ///!!!! PORPRAWIĆ PRZYCISKI DO PRZENOSZNIEA


    @FXML
    public void addReservation(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("lobbyNew.fxml"));
        root = loader.load();
        AddingController addingController = loader.getController();
        addingController.getUserName(myUserName);
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
        deletingController.getUserName1(myUserName);
        //root = FXMLLoader.load(getClass().getResource("lobbyDelete.fxml"));
        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void myReservation(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("lobbyHistory_v2.fxml"));
        root = loader.load();
        TableViewController tableViewController = loader.getController();
        tableViewController.getUserName3(myUserName);
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
        otherController.getUserName2(myUserName);
        //root = FXMLLoader.load(getClass().getResource("lobbyOther.fxml"));
        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void fillReservationChoiceBox(){
        String query = "SELECT * FROM rezerwacje WHERE id_u = ?";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {
            //System.out.println(id_uzyt + " idik");
            pst.setInt(1,id_uzyt);


            ResultSet rs = pst.executeQuery();

            while (rs.next()){
                reservationList.add(rs.getInt("id_rez"));
            }
            pst.close();
            rs.close();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgreSQL_register.class.getName());
            lgr.log(Level.SEVERE,ex.getMessage(),ex);
        }
    }

    @FXML
    public void iniCombo(ActionEvent actionEvent)
    {
        fillReservationChoiceBox();
        reservationChoiceBox.setItems(reservationList);
    }

    @FXML
    public void iniField(ActionEvent actionEvent)
    {
        fillReservationFields();
    }

    public void fillReservationFields(){
        String query = "SELECT * FROM rezerwacje WHERE id_rez= ?";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            int id_r = (int) reservationChoiceBox.getSelectionModel().getSelectedItem();
            System.out.println(id_r + " id_rezer");

            pst.setInt(1, id_r);
            ResultSet rs = pst.executeQuery();


            textFieldRoom.appendText("id_p");
            textFieldData.setText("data");
            textFieldHour.setText("id_h");

            pst.close();
            rs.close();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgreSQL_register.class.getName());
            lgr.log(Level.SEVERE,ex.getMessage(),ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Problem jest w tym ze initialazje dzieje sie szybciej niż szczytanie nazyw uzytkownika

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
}




