package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterController {
    @FXML
    private TextField name;
    @FXML
    private TextField mail;
    @FXML
    private PasswordField password;

    @FXML
    private void registerUser(ActionEvent actionEvent) throws SQLException, IOException {
        Main m = new Main();
        if(name.getText().isEmpty() || mail.getText().isEmpty() || password.getText().isEmpty()){
            name.setPromptText("Brak nazwy ");
            mail.setPromptText("Brak maila");
            password.setPromptText("Brak hasła");
        }
        else {
            if(mail.getText().contains("@o2.pl") || mail.getText().contains("@gmail.com") || mail.getText().contains("@wp.pl")){
                if (JavaPostgreSQL_register.checkDatabase(name.getText(), mail.getText())) {
                    System.out.println("Login lub maile jest już używany!");
                    m.changeScene("register-error.fxml"); //Powiadomienie o uzywanym loginie badz mailu
                } else {
                    JavaPostgreSQL_register.writeToDatabase(name.getText(), password.getText(), mail.getText());
                    m.changeScene("login.fxml");
                }
            } else { //Ewentualnie stworzyć nowa scenerie
                mail.clear();
                mail.setPromptText("Niepoprawny mail");
            }
        }
    }

    @FXML
    private void backToLogin(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("login.fxml");
    }

    @FXML
    private void close(ActionEvent actionEvent) {
        System.exit(0);
    }
}
