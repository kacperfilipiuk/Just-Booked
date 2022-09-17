package com.example.mainApp;

import com.example.mainApp.projekt_z_javy.entity.Rezerwacje;
import com.example.mainApp.sql.JavaPostgreSQL_adding;
import jakarta.persistence.*;
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
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AdminShowReservController implements Initializable {

    @FXML
    private Label Menu;

    @FXML
    private Label MenuClose;

    @FXML
    private Button MessageButton;

    @FXML
    private TextField MessageField;

    @FXML
    private AnchorPane slider;

    @FXML
    private TableView<Rezerwacje> rezTable;
    @FXML
    private TableColumn<Rezerwacje, String> idCol;
    @FXML
    private TableColumn<Rezerwacje, String> idPok;
    @FXML
    private TableColumn<Rezerwacje, String> idUs;
    @FXML
    private TableColumn<Rezerwacje, String> idGodz;
    @FXML
    private TableColumn<Rezerwacje, String> dateCol;

    ObservableList<Rezerwacje> RezerwacjeList = FXCollections.observableArrayList();

    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");

    private Stage stage;
    private Scene scene;
    private Parent root;

    String myUserName;

    int id_uzyt;

    public void getUserName2(String username) {
        myUserName = username;
        id_uzyt = JavaPostgreSQL_adding.getUserId(myUserName);
        //System.out.println(id_uzyt);
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

    public void userLogout(ActionEvent actionEvent) throws SQLException, IOException {
        //System.out.println("Wylogowuje...");
        root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void sendMessage(ActionEvent actionEvent) {
        //System.out.println("wysylam wiadomosc do Admina");
    }

    @FXML
    public void addRoom(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader;
        //if (myUserName.equals("admin")) {
        loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyAddRoomAdmin.fxml"));
        //}
        root = loader.load();
        AdminAddController adminAddController = loader.getController();
        adminAddController.getUserName2(myUserName);
        //root = FXMLLoader.load(getClass().getClassLoader().getResource("lobbyOther.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void editRoom(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader;
        //if (myUserName.equals("admin")) {
        loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyEditRoomAdmin.fxml"));
        //}
        root = loader.load();
        AdminEditController adminEditController = loader.getController();
        adminEditController.getUserName2(myUserName);
        //root = FXMLLoader.load(getClass().getClassLoader().getResource("lobbyOther.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void showReservations(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader;
        //if (myUserName.equals("admin")) {
        loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyShowReserv.fxml"));
        //}
        root = loader.load();
        AdminShowReservController adminShowReservController = loader.getController();
        adminShowReservController.getUserName2(myUserName);
        //root = FXMLLoader.load(getClass().getClassLoader().getResource("lobbyOther.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void cancelReservation(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader;
        //if (myUserName.equals("admin")) {
        loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyCancelResrv.fxml"));
        //}
        root = loader.load();
        AdminCancelReservController adminCancelReservController = loader.getController();
        adminCancelReservController.getUserName2(myUserName);
        //root = FXMLLoader.load(getClass().getClassLoader().getResource("lobbyOther.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void contactAdmin(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader;
        //if (myUserName.equals("admin")) {
        loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyContactAdmin.fxml"));
        //}
        root = loader.load();
        AdminContactController adminContactController = loader.getController();
        adminContactController.getUserName2(myUserName);
        //root = FXMLLoader.load(getClass().getClassLoader().getResource("lobbyOther.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void refreshTable(ActionEvent actionEvent){
        RezerwacjeList.clear();

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //EntityTransaction entityTransaction = entityManager.getTransaction();

        List<Rezerwacje> rezerwacje;

        try {
            //entityTransaction.begin();
            TypedQuery<Rezerwacje> typedQuery = entityManager.createQuery("SELECT rez FROM Rezerwacje rez", Rezerwacje.class);
            rezerwacje = typedQuery.getResultList();
        /*    rezerwacje.forEach(rezerwacje1 -> System.out.println(
                    "Id Rezerwacji: " + rezerwacje1.getIdRez() +
                    " Id uzytkownika: " + rezerwacje1.getIdU() +
                    " Id godziny: " + rezerwacje1.getIdH()));
        */
            rezerwacje.forEach(rezerwacje1 -> RezerwacjeList.add(new Rezerwacje(rezerwacje1.getIdRez(),
                                                                                rezerwacje1.getIdP(),
                                                                                rezerwacje1.getIdU(),
                                                                                rezerwacje1.getIdH(),
                                                                                rezerwacje1.getData())));

            //System.out.println(RezerwacjeList.size());
            rezTable.setItems(RezerwacjeList);
            //System.out.println(rezTable);


        } catch (NoResultException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        idCol.setCellValueFactory(new PropertyValueFactory<>("idRez"));
        idPok.setCellValueFactory(new PropertyValueFactory<>("idP"));
        idUs.setCellValueFactory(new PropertyValueFactory<>("idU"));
        idGodz.setCellValueFactory(new PropertyValueFactory<>("idH"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("data"));


    }
}