package com.example.projekt_z_javy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TableViewController implements Initializable {
    @FXML
    public Button dodajButton;
    @FXML
    public Button edytujButton;
    @FXML
    public Button wyswietlButton;
    @FXML
    public Button wylogujButton;

    String query = "";
    ResultSet resultSet = null;
    Rezerwacja rezerwacja = null;

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

        query = "SELECT * FROM rezerwacje";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = connection.prepareStatement(query)) {

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
            Logger lgr = Logger.getLogger(JavaPostgreSQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDate();
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
            Logger lgr = Logger.getLogger(JavaPostgreSQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public void userLogout(ActionEvent actionEvent) throws SQLException, IOException {
        System.out.println("Wylogowuje...");

        Main m = new Main();
        m.changeScene("login.fxml");
        m.onScene();

    }

    public void goToAddReservation(ActionEvent actionEvent) throws SQLException {
        System.out.println("Dodaje rezerwacje...");
        //tabPane.getSelectionModel().select(dodajTable);
    }

    public void goToEditReservation(ActionEvent actionEvent) throws SQLException {
        System.out.println("Edytuje rezerwacje...");
        //tabPane.getSelectionModel().select(edytujTable);
    }

    public void goToShowReservation(ActionEvent actionEvent) throws SQLException {
        System.out.println("Wyswietlam rezerwacje...");
        //tabPane.getSelectionModel().select(wyswietlTable);
    }
}
