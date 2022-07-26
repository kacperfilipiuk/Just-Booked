package com.example.projekt_z_javy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

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

            if(JavaPostgreSQL.checkDatabase(name.getText(),email.getText())){
                System.out.println("Login lub maile jest już używany!");
                m.changeScene("register-view-error.fxml");
            } else {
                JavaPostgreSQL.writeToDatabase(name.getText(),password.getText(),email.getText());
                m.changeScene("login-view.fxml");
            }
        }
        //else -> wyczyscicic i dac powidomienie ze juz istneieje
            //if(uzytkownik)
            //else if(email)213
    }

    public void backToMain(ActionEvent actionEvent) throws SQLException, IOException {
        Main m = new Main();
        m.changeScene("login-view.fxml");
    }
}