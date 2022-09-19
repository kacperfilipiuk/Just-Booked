package com.example.mainApp;

import com.example.mainApp.projekt_z_javy.entity.*;
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

public class AdminContactController implements Initializable {


    ObservableList<Wiadomosc> WiadomosciList = FXCollections.observableArrayList();
    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
    @FXML
    public TableView<Wiadomosc> tabelaWiadomosci;
    @FXML
    public TableColumn<Wiadomosc, String> idWiadomosci;
    @FXML
    public TableColumn<Wiadomosc, String> idUzytkownika;
    @FXML
    public TableColumn<Wiadomosc, String> trescWiado;
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

    private Stage stage;
    private Scene scene;
    private Parent root;

    String myUserName;

    int id_uzyt;

    /**Metoda getUserName2 - odpowiada za przekazanie z poprzednich stagow/scen nazwy uzytkownika*/

    public void getUserName2(String username){
        myUserName = username;
        id_uzyt = JavaPostgreSQL_adding.getUserId(myUserName);
        //System.out.println(id_uzyt);
    }

    /**Metoda exit - wyjscie z programu*/
    @FXML
    private void exit(ActionEvent e) {
        System.exit(0);
    }

    /**
     * Metoda initialize - odpowiada za "przygotowanie" i wykonuje sie przed kazdym wywowałniem @FXML
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        refreshTable();

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

    /**
     * Metoda userLogout - odpowiada za wylogowanie uzytkownika i przejscie do ekranu ponownego logowania
     * !PRACE TRWAJĄ!
     */
    public void userLogout(ActionEvent actionEvent) throws SQLException, IOException {
        //System.out.println("Wylogowuje...");
        root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Metoda addRoom - odpowiada za przejscie do kolejnego stage'a
     */
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

    /**
     * Metoda editRoom - odpowiada za przejscie do kolejnego stage'a
     */
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

    /**
     * Metoda showReservations - odpowiada za przejscie do kolejnego stage'a
     */
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

    /**
     * Metoda cancelReservation - odpowiada za przejscie do kolejnego stage'a
     */
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

    /**
     * Metoda contactAdmin - odpowiada za przejscie do kolejnego stage'a
     */
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

    private void refreshTable() {
        WiadomosciList.clear();

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //EntityTransaction entityTransaction = entityManager.getTransaction();

        List<Wiadomosci> wiadomosci;

        try {
            //entityTransaction.begin();
            TypedQuery<Wiadomosci> typedQuery = entityManager.createQuery("SELECT wiad FROM Wiadomosci wiad", Wiadomosci.class);
            wiadomosci = typedQuery.getResultList();

            /*NOWY KOD*/
            for (Wiadomosci wiadomosci1 : wiadomosci) {
                /* Uzytkownik - nazwa */
                Uzytkownicy uzytkownicy;

                TypedQuery<Uzytkownicy> typedQueryU = entityManager.createQuery("SELECT uzy FROM Uzytkownicy uzy WHERE uzy.idU = :custIdU", Uzytkownicy.class);
                typedQueryU.setParameter("custIdU", wiadomosci1.getIdU());
                uzytkownicy = typedQueryU.getSingleResult();


                /*-----------------------*/

                WiadomosciList.add(new Wiadomosc(String.valueOf(wiadomosci1.getIdW()), uzytkownicy.getLogin(), wiadomosci1.getWiadomosc()));

                /*-------------------------------------------------------*/
            }

            tabelaWiadomosci.setItems(WiadomosciList);

        } catch (NoResultException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        idWiadomosci.setCellValueFactory(new PropertyValueFactory<>("id_wiadomosci"));
        idUzytkownika.setCellValueFactory(new PropertyValueFactory<>("id_uzytwkonika"));
        trescWiado.setCellValueFactory(new PropertyValueFactory<>("pelna_wiadomosc"));

    }
    @FXML
    public void deleteRow(ActionEvent actionEvent) {
        ObservableList<Wiadomosc> id_of_mess  = FXCollections.observableArrayList();
        Wiadomosc wiadomosc = tabelaWiadomosci.getSelectionModel().getSelectedItems().get(0);
        int idWiad = Integer.parseInt(wiadomosc.getId_wiadomosci());
        System.out.println(idWiad);
        tabelaWiadomosci.getItems().removeAll(tabelaWiadomosci.getSelectionModel().getSelectedItem());

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            Wiadomosci wiadomosci = entityManager.find(Wiadomosci.class, idWiad);
            //System.out.println(rezerwacje);
            entityManager.remove(wiadomosci);
            entityManager.getTransaction().commit();
            AlertBox.display("Uwaga!", "Wiadomość została usunięta!");
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }


    }

    @FXML
    public void infoRow(ActionEvent actionEvent) {
        Wiadomosc wiadomosc = tabelaWiadomosci.getSelectionModel().getSelectedItems().get(0);
        AlertBox.display2("Notka", " Nadawca:    " + wiadomosc.id_uzytwkonika, wiadomosc.pelna_wiadomosc);

    }
}
