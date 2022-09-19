package com.example.mainApp;

import javafx.css.converter.FontConverter;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

    /**Metoda display - odpowiada za wyswietlanie okna (okna ma na celu informownaie uzytkownika)*/
    public static void display(String title, String message){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(250);
        window.setHeight(200);

        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("OK");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    /**Metoda display - odpowiada za wyswietlanie podgladu wiadomosci */
    public static void display2(String title, String login, String message){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(400);
        window.setHeight(300);

        Label nadawca = new Label();
        nadawca.setText(login);
        TextArea wiadomosc = new TextArea();
        wiadomosc.setText(message);
        wiadomosc.setEditable(false);
        
        Label napis = new Label();
        napis.setText(" Treść wiadomości: ");
        
        Button closeButton = new Button("OK");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(6);
        layout.getChildren().addAll(nadawca, napis, wiadomosc, closeButton);
        layout.setAlignment(Pos.CENTER_LEFT);


        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    public static void display3(){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Brak polaczenia!");
        window.setWidth(300);
        window.setHeight(250);

        Label label = new Label();
        Label label1 = new Label();
        label.setText("Nie mozna nawiazac polaczenia z serwerem");
        label1.setText("Prosze sprobowac pozniej");
        Button closeButton = new Button("OK");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, label1, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

}
