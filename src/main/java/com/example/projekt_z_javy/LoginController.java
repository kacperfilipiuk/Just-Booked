package com.example.projekt_z_javy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private TextField name;
    @FXML
    private PasswordField password;

    /* DO ZROBIENIA */

    //Usunąc obramowania z przyiskami funkcyjnymi (zminimalizuj, powieksz zamknij)
    //Zablokowac możliwośc zmiany rozmiaru



    /**
     * Funkcja odpowiedzjaca z aobsulge przycisku logowania - poprawic przejscia miedzy scneanmi (odkomentowac)
     */
    public void handleButtonPressedLogin(ActionEvent actionEvent) throws SQLException, IOException {
        Main m = new Main();
        if (name.getText().isEmpty() || password.getText().isEmpty()) {
            //m.changeScene("login-view-error.fxml");
            name.setPromptText("Brak nazwy ");
            password.setPromptText("Brak hasła");
        } else {
            if (JavaPostgreSQL_login.checkUserCor(name.getText(), password.getText())) {
                //goToMainPage();
            } else {
                //m.changeScene("login-view-error.fxml");
                System.out.println("Nie ma przejscia");
            }
        }
    }

    private void goToRegistration() throws IOException {
        Main m = new Main();
        m.changeScene("register.fxml");
    }

    /**
     * Funkcja odpowiedzjaca z aobsulge przycisku rejestracji
     */
    public void handleButtonPressedRegister(ActionEvent event) throws IOException {
        try {
            System.out.println("Rejestruje...");
            goToRegistration();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Funkcja odpowiadajca za zakoncenie działania apliakcji
     */

    /* private void goToMainPage() throws IOException {
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
    */
    @FXML
    private void exit(ActionEvent e){
        System.exit(0);
    }
}
