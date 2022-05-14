package com.example.projekt_z_javy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;
import java.sql.SQLException;

public class Lobby {

    @FXML
    private TabPane tabPane;

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




