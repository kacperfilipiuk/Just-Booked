package com.example.projekt_z_javy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.sql.SQLException;

public class addTab {

    @FXML
    private Button zapiszDodajButton;

    public void addReservation(ActionEvent actionEvent) throws SQLException, IOException {
        //JavaPostgreSQL.writeToDatabase();
        System.out.println("Dodano rezerwacje ...");
    }

}
