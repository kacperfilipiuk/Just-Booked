package com.example.mainApp;

import com.example.mainApp.projekt_z_javy.entity.Uzytkownicy;
import jakarta.persistence.*;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreHIB_register {
    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");

    static void writeToDatabase(String userName, String userPassword, String userEmail) {

        String name = userName;
        String email2 = userEmail;
        email2.toLowerCase();
        String pass = userPassword;

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = null;

        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            Uzytkownicy uzytkownicy = new Uzytkownicy();
            uzytkownicy.setLogin(name);
            uzytkownicy.setHaslo(pass);
            uzytkownicy.setEmail(email2);
            entityManager.persist(uzytkownicy);
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }


    /*COŚ TU NIE DO KONCA DZIAŁA - wyspuje błedy ale działa tak jak ma*/
    public static boolean checkDatabase(String userName, String userEmail) {

        boolean loginisko = false;
        String name = userName;
        String email2 = userEmail;
        email2.toLowerCase();

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String query = "SELECT uzyt FROM Uzytkownicy uzyt WHERE (uzyt.login = :custLogin OR uzyt.email = :custEmail)";

        TypedQuery<Uzytkownicy> typedQuery = entityManager.createQuery(query, Uzytkownicy.class);
        typedQuery.setParameter("custLogin", name);
        typedQuery.setParameter("custEmail", email2);
        Uzytkownicy uzytkownicy;

        try {
            uzytkownicy = typedQuery.getSingleResult();
            if (uzytkownicy.getIdU() > 0) {
                loginisko = true;
                System.out.println("Mamy takie delikwanta");
            } else {
                System.out.println("Nie ma goscia. Zapraszamy!");
            }

        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
        return loginisko;
    }
}
