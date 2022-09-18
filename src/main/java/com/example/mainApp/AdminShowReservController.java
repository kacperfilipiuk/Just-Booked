package com.example.mainApp;

import com.example.mainApp.projekt_z_javy.entity.Godziny;
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
    /*
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
     */
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

    ObservableList<Rezerwacje> RezerwacjeList = FXCollections.observableArrayList();
    ObservableList<Rezerwacja> RezerwacjeList1 = FXCollections.observableArrayList();

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

    private void refreshTable() {
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

            /*NOWY KOD*/
            for (Rezerwacje rezerwacje1 : rezerwacje) {
                /* Pokoje - nazwa */
                Pokoje pokoje;

                TypedQuery<Pokoje> typedQueryP = entityManager.createQuery("SELECT pok FROM Pokoje pok WHERE pok.idP = :custIdP", Pokoje.class);
                typedQueryP.setParameter("custIdP", rezerwacje1.getIdP());
                pokoje = typedQueryP.getSingleResult();

                /* Uzytkownik - nazwa */
                Uzytkownicy uzytkownicy;

                TypedQuery<Uzytkownicy> typedQueryU = entityManager.createQuery("SELECT uzy FROM Uzytkownicy uzy WHERE uzy.idU = :custIdU", Uzytkownicy.class);
                typedQueryU.setParameter("custIdU", rezerwacje1.getIdU());
                uzytkownicy = typedQueryU.getSingleResult();

                /* Godziny - nazwa */
                Godziny godziny;

                TypedQuery<Godziny> typedQueryG = entityManager.createQuery("SELECT god FROM Godziny god WHERE god.idH = :custIdH", Godziny.class);
                typedQueryG.setParameter("custIdH", rezerwacje1.getIdH());
                godziny = typedQueryG.getSingleResult();

                /*-----------------------*/

                RezerwacjeList1.add(new Rezerwacja(String.valueOf(rezerwacje1.getIdRez()), pokoje.getNazwa(), uzytkownicy.getLogin(), godziny.getGodzinaOd() + "-" + godziny.getGodzinaDo(), rezerwacje1.getData()));

                /*-------------------------------------------------------*/
                /*
                rezerwacje.forEach(rezerwacje1 -> RezerwacjeList.add(new Rezerwacje(rezerwacje1.getIdRez(),
                        rezerwacje1.getIdP(),
                        rezerwacje1.getIdU(),
                        rezerwacje1.getIdH(),
                        rezerwacje1.getData())));

                //System.out.println(RezerwacjeList.size());
                rezTable.setItems(RezerwacjeList);
                //System.out.println(rezTable);
                 */
            }

            rezTable.setItems(RezerwacjeList1);

        } catch (NoResultException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        /*
        idCol.setCellValueFactory(new PropertyValueFactory<>("idRez"));
        idPok.setCellValueFactory(new PropertyValueFactory<>("idP"));
        idUs.setCellValueFactory(new PropertyValueFactory<>("idU"));
        idGodz.setCellValueFactory(new PropertyValueFactory<>("idH"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("data"));
        */
        idCol.setCellValueFactory(new PropertyValueFactory<>("id_rezerwacji"));
        idPok.setCellValueFactory(new PropertyValueFactory<>("id_pokoju"));
        idUs.setCellValueFactory(new PropertyValueFactory<>("id_uzyt"));
        idGodz.setCellValueFactory(new PropertyValueFactory<>("id_godz"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("data"));
        /*

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
                } while (resultSet.next());
                rezTable.setItems(RezerwacjaList);
                System.out.println(RezerwacjaList);
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgreSQL_register.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

         */

    }

}