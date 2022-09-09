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

    //private ObservableList<Integer> hourList = FXCollections.observableArrayList(8,10,12,14,16,18);
    private ObservableList<String> hourList = FXCollections.observableArrayList();
    private ObservableList<String> roomList = FXCollections.observableArrayList();

    LocalDate myDate = null;
    String myUserName;

    @FXML
    private Button reservButton;
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

    public void getUserName(String username){
        myUserName = username;
        System.out.println(myUserName);
    }

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
            Logger lgr = Logger.getLogger(JavaPostgreSQL_register.class.getName());
            lgr.log(Level.SEVERE,ex.getMessage(),ex);
        }

    }

    public void fillHourComboBox(){
        String query = "SELECT godzina_od FROM godziny";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {
            ResultSet rs = pst.executeQuery();

            while (rs.next()){
                String h = rs.getString("godzina_od");
                switch(h) {
                    case "8": hourList.add("8-10"); break;
                    case "10": hourList.add("10-12"); break;
                    case "12": hourList.add("12-14"); break;
                    case "14": hourList.add("14-16"); break;
                    case "16": hourList.add("16-18"); break;
                    case "18": hourList.add("18-20"); break;
                }
            }
            pst.close();
            rs.close();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgreSQL_register.class.getName());
            lgr.log(Level.SEVERE,ex.getMessage(),ex);
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fillRoomComboBox();
        fillHourComboBox();
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
        FXMLLoader loader;
        if(myUserName.equals("admin"))
            loader = new FXMLLoader(getClass().getResource("lobbyNew.fxml"));
        else
            loader = new FXMLLoader(getClass().getResource("lobbyNew.fxml"));
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
        FXMLLoader loader;
        if(myUserName.equals("admin"))
            loader = new FXMLLoader(getClass().getResource("lobbyDelete.fxml"));
        else
            loader = new FXMLLoader(getClass().getResource("lobbyDelete.fxml"));
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
        FXMLLoader loader;
        if(myUserName.equals("admin"))
            loader = new FXMLLoader(getClass().getResource("lobbyHistory_v2.fxml"));
        else
            loader = new FXMLLoader(getClass().getResource("lobbyHistory_v2.fxml"));
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
        FXMLLoader loader;
        if(myUserName.equals("admin"))
            loader = new FXMLLoader(getClass().getResource("lobbyOther.fxml"));
        else
            loader = new FXMLLoader(getClass().getResource("lobbyOther.fxml"));
        root = loader.load();
        OtherController otherController = loader.getController();
        otherController.getUserName2(myUserName);
        //root = FXMLLoader.load(getClass().getResource("lobbyOther.fxml"));
        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void getDate(ActionEvent actionEvent) {
        myDate = dataPicker.getValue();
        System.out.println(myDate);
    }

    //Po nacisnieciu guzika
    @FXML
    public void makeReservation(ActionEvent actionEvent) throws SQLException {
        //Deklaracja zmiennych
        String wholeWord = (String) hourChoiceBox.getValue();
        char firstChar = wholeWord.charAt(0), secoundChar = wholeWord.charAt(1);
        int id_of_hour = 0;

        //Metody i warunki

        if(firstChar=='8'){
            id_of_hour = 8;
        } else if(firstChar=='1') {
            if (secoundChar == '0'){
                id_of_hour = 10;
            } else if (secoundChar == '2') {
                id_of_hour = 12;
            } else if (secoundChar == '4') {
                id_of_hour = 14;
            } else if (secoundChar == '6') {
                id_of_hour = 16;
            } else if (secoundChar == '8') {
                id_of_hour = 18;
            }
        }
        //int id_h = JavaPostgreSQL_adding.getHourId((Integer) hourChoiceBox.getValue());
        int id_h = JavaPostgreSQL_adding.getHourId(id_of_hour);
        int id_r = JavaPostgreSQL_adding.getRoomId((String) roomChoiceBox.getValue());
        int id_u = JavaPostgreSQL_adding.getUserId(myUserName);
        if(JavaPostgreSQL_adding.checkDatabase(Date.valueOf(myDate),id_r,id_h)){
            System.out.println("Niestety wybrany termin jest zajety");
        } else {
            JavaPostgreSQL_adding.writeReservToDatabase(Date.valueOf(myDate),id_r,id_h,id_u);
        }
        //sprawdz czy ta kombincja jest możliwa do rejestracji
        //if(tak){
        // } else {
        // }
    }
}
