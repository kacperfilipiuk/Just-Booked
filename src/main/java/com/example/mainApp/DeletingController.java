package com.example.mainApp;

import com.example.mainApp.projekt_z_javy.entity.Pokoje;
import com.example.mainApp.projekt_z_javy.entity.Rezerwacje;
import com.example.mainApp.sql.JavaPostgreSQL_adding;
import com.example.mainApp.sql.JavaPostgreSQL_register;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
    private Button deleteButton;

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

    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");

    public static final String url = "jdbc:postgresql://ec2-54-228-218-84.eu-west-1.compute.amazonaws.com:5432/de710thmop4rit";
    public static final String user = "dpbwovovhjsruv";
    public static final String password = "20482d0224e13b90ddcba4fd4e828746739cadef005e44a9bbad4acb6a7b64cf";

    public void getUserName1(String username) {
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
        root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    ///!!!! PORPRAWIĆ PRZYCISKI DO PRZENOSZNIEA


    @FXML
    public void addReservation(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader;
        if (myUserName.equals("admin"))
            loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyNew.fxml"));
        else
            loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyNew.fxml"));
        root = loader.load();
        AddingController addingController = loader.getController();
        addingController.getUserName(myUserName);
        //root = FXMLLoader.load(getClass().getClassLoader().getResource("lobbyNew.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    public void deleteReservation(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader;
        if (myUserName.equals("admin"))
            loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyDelete.fxml"));
        else
            loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyDelete.fxml"));
        root = loader.load();
        DeletingController deletingController = loader.getController();
        deletingController.getUserName1(myUserName);
        //root = FXMLLoader.load(getClass().getClassLoader().getResource("lobbyDelete.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void myReservation(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader;
        if (myUserName.equals("admin"))
            loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyHistory_v2.fxml"));
        else
            loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyHistory_v2.fxml"));
        root = loader.load();
        TableViewController tableViewController = loader.getController();
        tableViewController.getUserName3(myUserName);
        //root = FXMLLoader.load(getClass().getClassLoader().getResource("lobbyNew.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void otherReservation(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader;
        if (myUserName.equals("admin"))
            loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyOther.fxml"));
        else
            loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyOther.fxml"));
        root = loader.load();
        OtherController otherController = loader.getController();
        otherController.getUserName2(myUserName);
        //root = FXMLLoader.load(getClass().getClassLoader().getResource("lobbyOther.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    //Zastosowac w inicjallizacji
    public void fillReservationChoiceBox() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            List<Rezerwacje> listaRezerwacjiUzytkownika;
            entityTransaction.begin();

            System.out.println(id_uzyt);

            //Trzeba pozmieniac na stringi
            TypedQuery<Rezerwacje> listOfUserReserv = entityManager.createQuery("SELECT rez FROM Rezerwacje rez WHERE rez.idU = :custIdU", Rezerwacje.class);
            listOfUserReserv.setParameter("custIdU", id_uzyt);
            listaRezerwacjiUzytkownika = listOfUserReserv.getResultList();

            System.out.println(listaRezerwacjiUzytkownika.size());

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


        /*String query = "SELECT id_rez FROM rezerwacje WHERE id_u = ? ";

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
        }*/
    }

    @FXML
    public void iniCombo(ActionEvent actionEvent) {
        fillReservationChoiceBox();
        reservationChoiceBox.setItems(reservationList);
    }

    @FXML
    public void iniField(ActionEvent actionEvent) {
        fillReservationFields();
    }

    public void fillReservationFields() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        int id_r = (int) reservationChoiceBox.getSelectionModel().getSelectedItem();

        try {
            entityTransaction.begin();

            TypedQuery<Rezerwacje> listOfUserReserv = entityManager.createQuery("SELECT rez FROM Rezerwacje rez WHERE rez.idRez = :custIdRez", Rezerwacje.class);
            listOfUserReserv.setParameter("custIdRez", id_r);
            Rezerwacje rezerwacje = listOfUserReserv.getSingleResult();

            /* Pokoje */

            Integer id_pokoju = rezerwacje.getIdP();

            TypedQuery<Pokoje> listOfRooms = entityManager.createQuery("SELECT pok FROM Pokoje pok WHERE pok.idP = :custIdP", Pokoje.class);
            listOfRooms.setParameter("custIdP", id_pokoju);
            Pokoje pokoje = listOfRooms.getSingleResult();
            String nazwa_pokoju = pokoje.getNazwa();
            /* * */

            String id_godziny = String.valueOf(rezerwacje.getIdH());

            System.out.println(id_godziny);

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

            textFieldRoom.setText(nazwa_pokoju);
            textFieldHour.setText(id_godziny);
            textFieldData.setText(String.valueOf(pelna_data));

            entityTransaction.commit();
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }


/*
        String query = "SELECT * FROM rezerwacje WHERE id_rez = ?";

        try (Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, id_r);
            ResultSet rs = pst.executeQuery();
            System.out.println();
            System.out.println(id_r + " id_rez");


            int id_h = 0;
            int id_p = 0;
            Date data = null;
            String id_p1 = null;
            String id_h1 = null;

            while (rs.next()) {
                //System.out.println(rs.getString("id_p") + " id_pokoju");
                // System.out.println(rs.getString("id_h") + " id_godziny");
                //System.out.println(rs.getDate("data") + " dataa");

                id_p = rs.getInt("id_p");
                id_p1 = JavaPostgreHIB_deleting.getRoomName(id_p);
                if(id_p1 == null){
                    System.out.println("JESTEM NULLEM");
                    break;
                }
                //Jezeli jest nullem to nie mozemy przejsc dalej
                data = rs.getDate("data");
                id_h = rs.getInt("id_h");
                id_h1 = JavaPostgreHIB_deleting.getHourName(id_h);
                if(id_h1 == null){
                    System.out.println("JESTEM NULLEM");
                    break;
                }
                //Jezeli jest nullem to nie mozemy przejść dalej
            }

            switch(id_h1) {
                case "8": id_h1 = "8-10"; break;
                case "10": id_h1 = "10-12"; break;
                case "12": id_h1 = "12-14"; break;
                case "14": id_h1 = "14-16"; break;
                case "16": id_h1 = "16-18"; break;
                case "18": id_h1 = "18-20"; break;
            }

            textFieldRoom.setText(id_p1);
            textFieldHour.setText(id_h1);
            textFieldData.setText(String.valueOf(data));

            pst.close();
            rs.close();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgreSQL_register.class.getName());
            lgr.log(Level.SEVERE,ex.getMessage(),ex);
        }

 */
    }

    @FXML
    public void deleteReserv(ActionEvent actionEvent) throws SQLException {
        int id_r = (int) reservationChoiceBox.getSelectionModel().getSelectedItem();
        System.out.println(id_r);
        JavaPostgreHIB_deleting.deleteReservFromDatabase(id_r);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fillReservationChoiceBox();
        reservationChoiceBox.setItems(reservationList);

        //Problem jest w tym ze initialazje dzieje sie szybciej niż zczytanie nazyw uzytkownika

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

/*
USUWANIE składnia:
DELETE FROM nazwa_tabeli WHERE warunek

        DELETE FROM OSOBY
        WHERE imie = ‘Malgorzata’;
*/