package com.example.projekt_z_javy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;

public class Register {

    @FXML
    private Button submitButton;
    @FXML
    private TextField email;
    @FXML
    private TextField name;
    @FXML
    private PasswordField password;

    public void getData(ActionEvent actionEvent) throws SQLException, IOException {
        Main m = new Main();
        if(name.getText().isEmpty() || email.getText().isEmpty() || password.getText().isEmpty()){
            name.setPromptText("Brak nazywy ");
            email.setPromptText("Brak maila");
            password.setPromptText("Brak hasła");

        }
        else {
            if(email.getText().contains("@o2.pl") || email.getText().contains("@gmail.com") || email.getText().contains("@wp.pl")){
                if (JavaPostgreSQL.checkDatabase(name.getText(), email.getText())) {
                    System.out.println("Login lub maile jest już używany!");
                    m.changeScene("register.fxml");
                } else {
                    JavaPostgreSQL.writeToDatabase(name.getText(), password.getText(), email.getText());
                    m.changeScene("login.fxml");
                }
            } else { //Ewentulanie stowrzyć nowa scenerie
                email.clear();
                email.setPromptText("Niepoprawny mail");
            }
        }
    }

    public void backToMain(ActionEvent actionEvent) throws SQLException, IOException {
        Main m = new Main();
        m.changeScene("login.fxml");
    }
}