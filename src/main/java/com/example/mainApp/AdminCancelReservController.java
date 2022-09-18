package com.example.mainApp;

import com.example.mainApp.projekt_z_javy.entity.Pokoje;
import com.example.mainApp.projekt_z_javy.entity.Rezerwacje;
import com.example.mainApp.projekt_z_javy.entity.Uzytkownicy;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AdminCancelReservController implements Initializable {

    @FXML
    private Label Menu;

    @FXML
    private Label MenuClose;

    @FXML
    private AnchorPane slider;

    @FXML
    private ChoiceBox reservationChoiceBox;
    @FXML
    private TextField textFieldRoom;
    @FXML
    private TextField textFieldUser;
    @FXML
    private TextField textFieldData;
    @FXML
    private TextField textFieldHour;

    private Stage stage;
    private Scene scene;
    private Parent root;

    String myUserName;

    int id_uzyt;

    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
    private ObservableList<Integer> reservationList = FXCollections.observableArrayList();

    /**
     * Metoda getUserName2 - odpowiada za przekazanie z poprzednich stagow/scen nazwy uzytkownika
     */
    public void getUserName2(String username) {
        myUserName = username;
        id_uzyt = JavaPostgreSQL_adding.getUserId(myUserName);
        //System.out.println(id_uzyt);
    }

    /**
     * Metoda exit - wyjscie z programu
     */
    @FXML
    private void exit(ActionEvent e) {
        System.exit(0);
    }

    /**
     * Metoda initialize - odpowiada za "przygotowanie" i wykonuje sie przed kazdym wywowałniem @FXML
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fillReservationChoiceBox();
        reservationChoiceBox.setItems(reservationList);

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
     * Metoda addRoom - odpowiada za przejscie do kolejnego stage'a
     */
    @FXML
    public void addRoom(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader;
        loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyAddRoomAdmin.fxml"));
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
        loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyEditRoomAdmin.fxml"));
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
        loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyShowReserv.fxml"));
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
        loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyCancelResrv.fxml"));
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
        loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyContactAdmin.fxml"));
        root = loader.load();
        AdminContactController adminContactController = loader.getController();
        adminContactController.getUserName2(myUserName);
        //root = FXMLLoader.load(getClass().getClassLoader().getResource("lobbyOther.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Funkcja odpowiadajaca za uzupełnenienie Listy wkładanej do choice boxa
     */
    public void fillReservationChoiceBox() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            List<Rezerwacje> listaRezerwacjiUzytkownika;
            entityTransaction.begin();

            //System.out.println(id_uzyt);

            //Trzeba pozmieniac na stringi
            TypedQuery<Rezerwacje> listOfUserReserv = entityManager.createQuery("SELECT rez FROM Rezerwacje rez", Rezerwacje.class);
            listaRezerwacjiUzytkownika = listOfUserReserv.getResultList();

            //System.out.println(listaRezerwacjiUzytkownika.size());

            List<Integer> reserv = listaRezerwacjiUzytkownika
                    .stream()
                    .map(Rezerwacje::getIdRez)
                    .collect(Collectors.toList());

            reservationList.addAll(reserv);


            entityTransaction.commit();
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    /**
     * Funkcja odpowiedzialna z wypelnienie pol, po wybraniu konkretnej rezerwacji
     */

    public void fillReservationFields() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        int id_r = (int) reservationChoiceBox.getSelectionModel().getSelectedItem();

        try {
            entityTransaction.begin();

            /* Cała rezerwacja */

            TypedQuery<Rezerwacje> listOfUserReserv = entityManager.createQuery("SELECT rez FROM Rezerwacje rez WHERE rez.idRez = :custIdRez", Rezerwacje.class);
            listOfUserReserv.setParameter("custIdRez", id_r);
            Rezerwacje rezerwacje = listOfUserReserv.getSingleResult();

            /* * */

            /* Użytkownik */

            Integer id_uzytkownika = rezerwacje.getIdU();

            TypedQuery<Uzytkownicy> oneUser = entityManager.createQuery("SELECT uzyt FROM Uzytkownicy uzyt WHERE uzyt.idU = :custIdU", Uzytkownicy.class);
            oneUser.setParameter("custIdU", id_uzytkownika);
            Uzytkownicy uzytkownicy = oneUser.getSingleResult();
            String nazwa_uzyt = uzytkownicy.getLogin();

            /* * */

            /* Pokoje */

            Integer id_pokoju = rezerwacje.getIdP();

            TypedQuery<Pokoje> listOfRooms = entityManager.createQuery("SELECT pok FROM Pokoje pok WHERE pok.idP = :custIdP", Pokoje.class);
            listOfRooms.setParameter("custIdP", id_pokoju);
            Pokoje pokoje = listOfRooms.getSingleResult();
            String nazwa_pokoju = pokoje.getNazwa();
            /* * */

            String id_godziny = String.valueOf(rezerwacje.getIdH());

            //System.out.println(id_godziny);

            switch (id_godziny) {
                case "1":
                    id_godziny = "8-10";
                    break;
                case "2":
                    id_godziny = "10-12";
                    break;
                case "3":
                    id_godziny = "12-14";
                    break;
                case "4":
                    id_godziny = "14-16";
                    break;
                case "5":
                    id_godziny = "16-18";
                    break;
                case "6":
                    id_godziny = "18-20";
                    break;
            }

            String pelna_data = String.valueOf(rezerwacje.getData());

            textFieldUser.setText(nazwa_uzyt);
            textFieldRoom.setText(nazwa_pokoju);
            textFieldHour.setText(id_godziny);
            textFieldData.setText(String.valueOf(pelna_data));

            entityTransaction.commit();
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }

    }

    /**
     * Metoda acceptChoice - odpowiada za wywołanie funkcji odpowiedzialne za wypelnienie pozostałych pol
     */
    public void acceptChoice(ActionEvent actionEvent) {
        fillReservationFields();
    }

    /**
     * Metoda deleteReservationAdmin - odpowiada za usuniecie rezerwacji o konkretnym numerze id
     */
    public void deleteReservationAdmin(ActionEvent actionEvent) throws SQLException {
        int id_r = (int) reservationChoiceBox.getSelectionModel().getSelectedItem();
        //System.out.println(id_r);
        JavaPostgreHIB_deleting.deleteReservFromDatabase(id_r);
    }
}