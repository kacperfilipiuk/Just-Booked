package com.example.mainApp;

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
import javafx.scene.control.cell.PropertyValueFactory;
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

    ObservableList<Rezerwacje> RezerwacjeList = FXCollections.observableArrayList();

    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");

    String myUserName;

    int id_uzyt;

    //ObservableList<Rezerwacja> RezerwacjaList = FXCollections.observableArrayList();

    @FXML
    private Button refresh;
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

    @FXML
    private void refreshTable() {
        RezerwacjeList.clear();

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //EntityTransaction entityTransaction = entityManager.getTransaction();

        List<Rezerwacje> rezerwacje;

        try {
            //entityTransaction.begin();
            TypedQuery<Rezerwacje> typedQuery = entityManager.createQuery("SELECT rez FROM Rezerwacje rez WHERE rez.idU = :custIdU", Rezerwacje.class);
            typedQuery.setParameter("custIdU", id_uzyt);
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
    public void getUserName3(String username){
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
            loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyNew.fxml"));
        else
            loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyNew.fxml"));
        root = loader.load();
        AddingController addingController = loader.getController();
        addingController.getUserName(myUserName);
        //root = FXMLLoader.load(getClass().getClassLoader().getResource("lobbyNew.fxml"));
        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }
    @FXML
    public void deleteReservation(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader;
        if(myUserName.equals("admin"))
            loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyDelete.fxml"));
        else
            loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyDelete.fxml"));
        root = loader.load();
        DeletingController deletingController = loader.getController();
        deletingController.getUserName1(myUserName);
        //root = FXMLLoader.load(getClass().getClassLoader().getResource("lobbyDelete.fxml"));
        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void myReservation(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader;
        if(myUserName.equals("admin"))
            loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyHistory_v2.fxml"));
        else
            loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyHistory_v2.fxml"));
        root = loader.load();
        TableViewController tableViewController = loader.getController();
        tableViewController.getUserName3(myUserName);
        //root = FXMLLoader.load(getClass().getClassLoader().getResource("lobbyNew.fxml"));
        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void otherReservation(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader;
        if(myUserName.equals("admin"))
            loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyOther.fxml"));
        else
            loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyOther.fxml"));
        root = loader.load();
        OtherController otherController = loader.getController();
        otherController.getUserName2(myUserName);
        //root = FXMLLoader.load(getClass().getClassLoader().getResource("lobbyOther.fxml"));
        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setArray(ActionEvent actionEvent) {
        //loadDate();
        refreshTable();
    }
}
