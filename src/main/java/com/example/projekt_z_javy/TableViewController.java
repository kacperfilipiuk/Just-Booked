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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TableViewController implements Initializable {

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
    String myUserName;

    int id_uzyt;


    public static final String url = "jdbc:postgresql://ec2-54-228-218-84.eu-west-1.compute.amazonaws.com:5432/de710thmop4rit";
    public static final String user = "dpbwovovhjsruv";
    public static final String password = "20482d0224e13b90ddcba4fd4e828746739cadef005e44a9bbad4acb6a7b64cf";

    ObservableList<Rezerwacja> RezerwacjaList = FXCollections.observableArrayList();

    @FXML
    private Button refresh;
    @FXML
    private TableView<Rezerwacja> rezTable;
    @FXML
    private TableColumn<Rezerwacja, String> idCol;
    @FXML
    private TableColumn<Rezerwacja, String> idPok;
    @FXML
    private TableColumn<Rezerwacja, String> idUs;
    @FXML
    private TableColumn<Rezerwacja, String> idGodz;
    @FXML
    private TableColumn<Rezerwacja, String> dateCol;

    @FXML
    private void refreshTable() {
        RezerwacjaList.clear();


        query = "SELECT * FROM rezerwacje WHERE id_u = ? ";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = connection.prepareStatement(query)) {

            pst.setInt(1,id_uzyt);

            //resultSet = pst.getResultSet();
            resultSet = pst.executeQuery();

            if (!resultSet.next()) {
                System.out.println("no data");
            } else {

                do {
                    RezerwacjaList.add(new Rezerwacja(
                            resultSet.getInt("id_rez"),
                            resultSet.getInt("id_p"),
                            resultSet.getInt("id_u"),
                            resultSet.getInt("id_h"),
                            resultSet.getDate("data")));
                    rezTable.setItems(RezerwacjaList);
                } while (resultSet.next());
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgreSQL_register.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

    }
    public void getUserName3(String username){
        myUserName = username;
        id_uzyt = JavaPostgreSQL_adding.getUserId(myUserName);
        System.out.println(id_uzyt);
    }

    @FXML
    private void exit(ActionEvent e) {
        System.exit(0);
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


    private void loadDate() {

        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            refreshTable();


            idCol.setCellValueFactory(new PropertyValueFactory<>("id_rezerwacji"));
            idPok.setCellValueFactory(new PropertyValueFactory<>("id_pokoju"));
            idUs.setCellValueFactory(new PropertyValueFactory<>("id_uzyt"));
            idGodz.setCellValueFactory(new PropertyValueFactory<>("id_godz"));
            dateCol.setCellValueFactory(new PropertyValueFactory<>("data"));


        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgreSQL_register.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
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

    public void setArray(ActionEvent actionEvent) {
        loadDate();
    }
}
