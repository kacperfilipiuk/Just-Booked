package com.example.projekt_z_javy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

import java.io.IOException;
import java.sql.SQLException;

public class Lobby {

    @FXML
    private TabPane tabPane;

    @FXML
    private DatePicker myDataPicker;
    @FXML
    private Button wylogujButton;
    @FXML
    private Button dodajButton;
    @FXML
    private Button edytujButton;
    @FXML
    private Button wyswietlButton;

    @FXML
    private Tab glownaTable;
    @FXML
    private Tab dodajTable;
    @FXML
    private Tab edytujTable;
    @FXML
    private Tab wyswietlTable;


    public void getDate(ActionEvent event){
        LocalDate myDate = myDataPicker.getValue();
        System.out.println(myDate.toString());
    }

    public void userLogout(ActionEvent actionEvent) throws SQLException, IOException {
        System.out.println("Wylogowuje...");
        Main m = new Main();
        m.changeScene("login-view.fxml");
        m.onScene();
    }

    public void goToAddReservation(ActionEvent actionEvent) throws SQLException {
        System.out.println("Dodaje rezerwacje...");
        tabPane.getSelectionModel().select(dodajTable);
    }

    public void goToEditReservation(ActionEvent actionEvent) throws SQLException {
        System.out.println("Edytuje rezerwacje...");
        tabPane.getSelectionModel().select(edytujTable);
    }

    public void goToShowReservation(ActionEvent actionEvent) throws SQLException {
        System.out.println("Wyswietlam rezerwacje...");
        tabPane.getSelectionModel().select(wyswietlTable);
    }

    public void goToHomePageReservation(ActionEvent actionEvent) throws SQLException {
        System.out.println("Powrot do lobby...");
        tabPane.getSelectionModel().select(glownaTable);
    }

}




