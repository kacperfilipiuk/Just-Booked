package com.example.projekt_z_javy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
        Main m = new Main();
        if (name.getText().isEmpty() || password.getText().isEmpty()) {
            m.changeScene("login-view-error.fxml");
            name.setPromptText("Brak nazywy ");
            password.setPromptText("Brak hasła");
        } else {
            if (JavaPostgreSQL_login.checkUserCor(name.getText(), password.getText())) {
                goToMainPage();
            } else {
                m.changeScene("login-view-error.fxml");
                System.out.println("Nie ma przejscia");
            }
        }
    }

    public void userSignUp(ActionEvent actionEvent) throws SQLException, IOException {
        System.out.println("Rejestruje...");
        goToRegistration();
    }

    private void goToMainPage() throws IOException {
        try {
            Main m = new Main();
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            Pane root = fxmlLoader.load(getClass().getResource("lobby-view.fxml").openStream());

            stage.setScene(new Scene(root, 600, 400));
            m.offScene();
            stage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void goToRegistration() throws IOException {
        Main m = new Main();
        m.changeScene("register-view.fxml");
    }
}
