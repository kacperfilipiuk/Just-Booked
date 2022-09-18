package com.example.mainApp;

import com.example.mainApp.projekt_z_javy.entity.Godziny;
import com.example.mainApp.projekt_z_javy.entity.Pokoje;
import com.example.mainApp.projekt_z_javy.entity.Rezerwacje;
import com.example.mainApp.sql.JavaPostgreSQL_adding;
import com.example.mainApp.sql.JavaPostgreSQL_register;
import jakarta.persistence.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreHIB_deleting {

    private ObservableList<Integer> reservationList = FXCollections.observableArrayList();

    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");

    static String checkNameOfRoom;
    static String checkBeginOfHour;

    public static String getRoomName(int rId) {
        int roomId = rId;
        String gotName = null;

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String query = "SELECT pok FROM Pokoje pok WHERE pok.idP = :custIdP";

        TypedQuery<Pokoje> typedQuery = entityManager.createQuery(query, Pokoje.class);
        typedQuery.setParameter("custIdP", roomId);

        Pokoje pokoje;

        try {
            pokoje = typedQuery.getSingleResult();
            if (!pokoje.getNazwa().isEmpty()) {
                gotName = pokoje.getNazwa();
                //System.out.println("Oto numer id pokoju: " + gotName);
                return gotName;
            } else {
                //System.out.println("Nie ma pokoju o takim id!");
            }

        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
        return gotName;
    }

    public static String getHourName(int hId) {
        int hourId = hId;
        String gotHourName = null;

        //String query = "SELECT godzina_od FROM godziny WHERE id_h = ?";

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String query = "SELECT god FROM Godziny god WHERE god.idH = :custHour";

        TypedQuery<Godziny> typedQuery = entityManager.createQuery(query, Godziny.class);
        typedQuery.setParameter("custHour", hourId);

        Godziny godziny;

        try {
            godziny = typedQuery.getSingleResult();
            if (!String.valueOf(godziny.getGodzinaOd()).isEmpty()) {
                gotHourName = String.valueOf(godziny);
                //System.out.println("Oto numer id pokoju: " + gotHourName);
                return gotHourName;
            } else {
                //System.out.println("Nie ma pokoju o takim id!");
            }

        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
        return gotHourName;
    }


    public static void deleteReservFromDatabase(int reservation) throws SQLException {

        int idReservation = reservation;

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            Rezerwacje rezerwacje = entityManager.find(Rezerwacje.class, idReservation);
            //System.out.println(rezerwacje);
            entityManager.remove(rezerwacje);
            entityManager.getTransaction().commit();
            AlertBox.display("Uwaga!", "Twoja rezerwacja została usunięta!");
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            entityManagerFactory.close();
            entityManager.close();
        }
    }
}
