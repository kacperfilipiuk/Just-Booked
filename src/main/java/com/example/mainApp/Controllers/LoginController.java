package com.example.mainApp.Controllers;

import com.example.mainApp.Hibernate.JavaPostgreHIB_login;
import com.example.mainApp.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
        Client m = new Client();

        if (name.getText().isEmpty() || password.getText().isEmpty()) {
            m.changeScene("login-error.fxml");
            name.setPromptText("Brak nazwy ");
            password.setPromptText("Brak hasła");
        } else {
            //if (JavaPostgreSQL_login.checkUserCor(name.getText(), password.getText()))
            if (JavaPostgreHIB_login.checkUserCord(name.getText(), password.getText())) {
                String userName = name.getText();
                FXMLLoader loader;
                if (userName.equals("admin")) {
                    loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyAdmin.fxml"));
                    Parent home_page_parent = loader.load();
                    AdminLobbyController adminLobbyController = loader.getController();
                    adminLobbyController.getName(userName);
                    adminLobbyController.displayName(userName);
                    //Parent home_page_parent = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("lobby.fxml")));
                    Scene home_page_scene = new Scene(home_page_parent);
                    Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    app_stage.setScene(home_page_scene);
                    app_stage.centerOnScreen();
                    app_stage.show();
                    //System.out.println("Udalo sie");
                }else {
                    loader = new FXMLLoader(getClass().getClassLoader().getResource("lobby.fxml"));
                    Parent home_page_parent = loader.load();
                    LobbyController lobbyController = loader.getController();
                    lobbyController.getName(userName);
                    lobbyController.displayName(userName);
                    //Parent home_page_parent = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("lobby.fxml")));
                    Scene home_page_scene = new Scene(home_page_parent);
                    Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    app_stage.setScene(home_page_scene);
                    app_stage.centerOnScreen();
                    app_stage.show();
                    //System.out.println("Udalo sie");
                }
            } else {
                m.changeScene("login-error.fxml");
                //System.out.println("Nie ma przejscia");
            }
        }
    }

    private void goToRegistration() throws IOException {
        Client m = new Client();
        m.changeScene("register.fxml");
    }

    /**
     * Funkcja odpowiedzjaca z aobsulge przycisku rejestracji
     */
    public void handleButtonPressedRegister(ActionEvent event) throws IOException {
        try {
            //System.out.println("Rejestruje...");
            goToRegistration();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Funkcja odpowiadajca za zakoncenie działania apliakcji
     */
    @FXML
    private void exit(ActionEvent e) {
        System.exit(0);
    }
}
