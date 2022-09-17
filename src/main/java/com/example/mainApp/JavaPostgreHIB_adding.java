package com.example.mainApp;

import com.example.mainApp.projekt_z_javy.entity.Godziny;
import com.example.mainApp.projekt_z_javy.entity.Pokoje;
import com.example.mainApp.projekt_z_javy.entity.Rezerwacje;
import com.example.mainApp.projekt_z_javy.entity.Uzytkownicy;
import jakarta.persistence.*;

import java.sql.*;
import java.util.List;

public class JavaPostgreHIB_adding {

    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");

    //Funkcja odpowiedzialna z azwrócenie id uzytkownika z bazy danych
    public static int getUserId(String uN) {
        String userName = uN;
        int userNumber = 0;

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String query = "SELECT uzyt FROM Uzytkownicy uzyt WHERE uzyt.login = :custLogin";

        TypedQuery<Uzytkownicy> typedQuery = entityManager.createQuery(query, Uzytkownicy.class);
        typedQuery.setParameter("custLogin", userName);

        Uzytkownicy uzytkownicy;

        try {
            uzytkownicy = typedQuery.getSingleResult();
            if (uzytkownicy.getIdU() > 0) {
                userNumber = uzytkownicy.getIdU();
                System.out.println("Oto id uzytkownia: " + userNumber);
            } else {
                System.out.println("Nie ma numery id uzytkowniaka.");

            }

        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
        return userNumber;
    }

    public static int getRoomId(String rN) {
        String roomName = rN;
        int roomNumber = 0;

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String query = "SELECT pok FROM Pokoje pok WHERE pok.nazwa = :custNazwa";

        TypedQuery<Pokoje> typedQuery = entityManager.createQuery(query, Pokoje.class);
        typedQuery.setParameter("custNazwa", roomName);

        Pokoje pokoje;

        try {
            pokoje = typedQuery.getSingleResult();
            if (pokoje.getIdP() > 0) {
                roomNumber = pokoje.getIdP();
                System.out.println("Oto numer id pokoju: " + roomNumber);
            } else {
                System.out.println("Nie ma pokoju o takim id!");

            }

        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
        return roomNumber;
    }

    public static int getHourId(Integer pH) {
        int pickedHour = pH;
        int hourNumber = 0;

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String query = "SELECT godz FROM Godziny godz WHERE godz.godzinaOd = :custHour";

        TypedQuery<Godziny> typedQuery = entityManager.createQuery(query, Godziny.class);
        typedQuery.setParameter("custHour", pickedHour);

        Godziny godziny;

        try {
            godziny = typedQuery.getSingleResult();
            if (godziny.getIdH() > 0) {
                hourNumber = godziny.getIdH();
                System.out.println("Oto numer id godziny: " + hourNumber);
            } else {
                System.out.println("Nie ma godziny o takim id!");

            }

        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
        return hourNumber;
    }


    public static void writeReservToDatabase(Date pD, Integer room, Integer hour, Integer userNum) {

        Date pickedDate = pD;
        Integer roomName = room;
        Integer pickedHour = hour;
        Integer userNumber = userNum;

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = null;

        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            Rezerwacje rezerwacje = new Rezerwacje();
            rezerwacje.setIdU(userNumber); //Numer id uzytkownika
            rezerwacje.setIdH(pickedHour); //Numer id wybranej godziny
            rezerwacje.setIdP(roomName); //Numer id pokoju
            rezerwacje.setData(pickedDate); //Wybrana data

            entityManager.persist(rezerwacje);
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

    /**
     * Metoda odpowiedzialna za sprawdzenie powtórzenia loginu lub maila, rejestrującego sie uzytkownika
     */

    public static boolean checkDatabase(Date pickDate, Integer idPok, Integer idGodz) {

        boolean loginisko = false;
        Date date = pickDate;
        Integer numerPokoju = idPok;
        Integer numerGodziny = idGodz;

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String query = "SELECT rez FROM Rezerwacje rez WHERE (rez.idP = :custIdP AND rez.idH = :custIdH AND rez.data = :custData)";

        TypedQuery<Rezerwacje> typedQuery = entityManager.createQuery(query, Rezerwacje.class);
        typedQuery.setParameter("custIdP", numerPokoju);
        typedQuery.setParameter("custIdH", numerGodziny);
        typedQuery.setParameter("custData", date);
        Rezerwacje rezerwacje;
        List result;

        try {
            result = typedQuery.getResultList();
            if (result.isEmpty()) {
                loginisko = false;
            } else if (result.size() == 1) {
                rezerwacje = (Rezerwacje) result.get(0);
                System.out.println(rezerwacje);
                if (rezerwacje.getIdRez() > 0) {
                    loginisko = true;
                    System.out.println("Jest juz Rezerwacja!");
                } else {
                    System.out.println("Nie ma rezerwacji. Zapraszamy!");
                }
            }
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
        return loginisko;
    }
}