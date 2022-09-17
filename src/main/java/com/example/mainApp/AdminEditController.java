package com.example.mainApp;

import com.example.mainApp.projekt_z_javy.entity.Pokoje;
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

public class AdminEditController implements Initializable {

    private ObservableList<String> pokojeList = FXCollections.observableArrayList();

    @FXML
    private Label Menu;

    @FXML
    private Label MenuClose;

    @FXML
    private Button ButtonEditRoom;

    @FXML
    private TextField userRoomName;
    @FXML
    private ChoiceBox choiceBox_list;

    @FXML
    private AnchorPane slider;

    private Stage stage;
    private Scene scene;
    private Parent root;

    String myUserName;

    int id_uzyt;

    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");


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

        fillTheList();
        //Prawie działa tak jak ma
        //pokojeList.get(1);
        choiceBox_list.setItems(pokojeList);

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


    ///!!!! PORPRAWIĆ PRZYCISKI DO PRZENOSZENIA


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
    public void pressedButton(ActionEvent actionEvent) {
        replaceNameOfRoom();
        //System.out.println("Zrobione");
        AlertBox.display("Uwaga!", "Nazwa pokoju została zmieniona!");

    }


    //Funkcja odpowiadajaa za wypełnienie obserbeList, ktora bedzie potrzebna do uzupełniaina choiceboxa
    public void fillTheList() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            List<Pokoje> listaPokoi;
            entityTransaction.begin();

            //Trzeba pozmieniac na stringi
            TypedQuery<Pokoje> listOfRooms = entityManager.createQuery("SELECT pok FROM Pokoje pok", Pokoje.class);
            listaPokoi = listOfRooms.getResultList();

            List<String> names = listaPokoi
                    .stream()
                    .map(Pokoje::getNazwa)
                    .collect(Collectors.toList());

            pokojeList.addAll(names);


            entityTransaction.commit();
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public void replaceNameOfRoom() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        int idPokoju;
        String nazwaPokojuPrzedZmina = (String) choiceBox_list.getSelectionModel().getSelectedItem();
        String nazwaPokojuPoZmianie = userRoomName.getText();
        //System.out.println("Nazwa pokoju przed zmiana: " + nazwaPokojuPrzedZmina);

        Pokoje pokoje;
        Pokoje pokoje1 = new Pokoje();

        try {
            entityTransaction.begin();

            //Trzeba pozmieniac na stringi
            TypedQuery<Pokoje> typedQuery = entityManager.createQuery("SELECT pok FROM Pokoje pok WHERE pok.nazwa = :custNazwa", Pokoje.class);
            typedQuery.setParameter("custNazwa", nazwaPokojuPrzedZmina); //Wybieram wybrrana sale
            pokoje = typedQuery.getSingleResult();
            idPokoju = pokoje.getIdP();
            //System.out.println(idPokoju);

            entityTransaction.commit();

            entityTransaction.begin();

            pokoje1 = entityManager.find(Pokoje.class, idPokoju);
            //System.out.println(pokoje1);
            //System.out.println(nazwaPokojuPoZmianie);
            pokoje1.setNazwa(nazwaPokojuPoZmianie);

            entityManager.persist(pokoje1);
            entityTransaction.commit();
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
        } finally {
            entityManager.close();
        }
    }
    //1) znjadz id wybranego pokoju
    //2) pobierz nazwe nowego pokoju
    //3) w miejsce id starego pokoju wstaw nowa nazwe
}
