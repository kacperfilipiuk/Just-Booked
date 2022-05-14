package com.example.projekt_z_javy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class Login {

    //Konstruktor
    public Login() {

    }

    @FXML
    private Label problem;
    @FXML
    private Button rejestracjaButton;
    @FXML
    private Button zalogujButton;
    @FXML
    private TextField name;
    @FXML
    private PasswordField password;

    public void getData(ActionEvent actionEvent) throws SQLException {
    }

    public void userLogIn(ActionEvent actionEvent) throws SQLException, IOException {
        System.out.println("Loguje...");
        checkLogin();
    }

    public void userSignUp(ActionEvent actionEvent) throws SQLException, IOException {
        System.out.println("Rejestruje...");
        goToRegistration();
    }

    private void checkLogin() throws IOException {
        Main m = new Main();
       /* if (name.getText().isEmpty() && password.getText().isEmpty()) {

        } else if (name.getText().isEmpty() && password.getText().isEmpty()) {
            problem.setText("Wprowadz dane!");
        } else {
            problem.setText("Wprowadz dane!");
        }*/

        m.changeScene("lobby-view.fxml");
    }

    private void goToRegistration() throws IOException {
        Main m = new Main();
        m.changeScene("register-view.fxml");
    }
}
