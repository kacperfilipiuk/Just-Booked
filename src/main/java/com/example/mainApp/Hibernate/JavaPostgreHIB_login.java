package com.example.mainApp.Hibernate;

import com.example.mainApp.Entity.Uzytkownicy;
import jakarta.persistence.*;

public class JavaPostgreHIB_login {

    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");

    public static boolean checkUserCord(String userName, String userPassword) {
        boolean wynik = false;

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String query = "SELECT uzyt FROM Uzytkownicy uzyt WHERE uzyt.login = :custLogin";

        TypedQuery<Uzytkownicy> typedQuery = entityManager.createQuery(query, Uzytkownicy.class);
        typedQuery.setParameter("custLogin", userName);
        Uzytkownicy uzytkownicy;

        try {
            uzytkownicy = typedQuery.getSingleResult();
            if (uzytkownicy.getHaslo().equals(userPassword)) {
                wynik = true;
                //System.out.println("Działa!");
            } else {
                //System.out.println("Nie dziala!");
            }
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
        return wynik;
    }
}