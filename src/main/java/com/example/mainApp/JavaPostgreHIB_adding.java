package com.example.mainApp;

import com.example.mainApp.projekt_z_javy.entity.Rezerwacje;
import com.example.mainApp.projekt_z_javy.entity.Uzytkownicy;
import jakarta.persistence.*;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreHIB_adding {

    public static final String url = "jdbc:postgresql://ec2-54-228-218-84.eu-west-1.compute.amazonaws.com:5432/de710thmop4rit";
    public static final String user = "dpbwovovhjsruv";
    public static final String password = "20482d0224e13b90ddcba4fd4e828746739cadef005e44a9bbad4acb6a7b64cf";

    static Integer checkNumberOfUser;
    static Integer checkNumberOfRoom;
    static Integer checkNumberOfHour;

    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");

    //Funkcja odpowiedzialna z azwrócenie id uzytkownika z bazy danych
    public static int getUserId(String uN) {
        String userName = uN;

        String query = "SELECT id_u FROM uzytkownicy WHERE login = ?";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, userName);

            ResultSet resultSet = pst.executeQuery(); //executeQuery zwaraca nam wartosc podana przez selecta

            while (resultSet.next()) {
                checkNumberOfUser = resultSet.getInt(1);
                System.out.println(checkNumberOfUser);
            }

            System.out.println("Numer uzytkownika to: " + checkNumberOfUser);
            pst.close();
            resultSet.close();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgreHIB_adding.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return checkNumberOfUser;
    }

    public static int getRoomId(String rN) {
        String roomName = rN;
        String query = "SELECT id_p FROM pokoje WHERE nazwa = ?";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, roomName);

            ResultSet resultSet = pst.executeQuery(); //executeQuery zwaraca nam wartosc podana przez selecta

            while (resultSet.next()) {
                checkNumberOfRoom = resultSet.getInt(1);
                System.out.println(checkNumberOfRoom);
            }

            System.out.println("Numer id pokoju to: " + checkNumberOfRoom);
            pst.close();
            resultSet.close();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgreHIB_adding.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return checkNumberOfRoom;
    }

    public static int getHourId(Integer pH) {
        Integer pickedHour = pH;
        String query = "SELECT id_h FROM godziny WHERE godzina_od = ?";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, pickedHour);

            ResultSet resultSet = pst.executeQuery(); //executeQuery zwaraca nam wartosc podana przez selecta

            while (resultSet.next()) {
                checkNumberOfHour = resultSet.getInt(1);
                System.out.println(checkNumberOfHour);
            }

            System.out.println("Numer id godziny to: " + checkNumberOfHour);
            pst.close();
            resultSet.close();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgreHIB_adding.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return checkNumberOfHour;
    }


    public static void writeReservToDatabase(Date pD, Integer room, Integer hour, Integer userNum) {
        //public static void writeReservToDatabase(Date pD, Integer room, Integer hour, Integer userNum) throws SQLException {

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

    public static boolean checkDatabase(Date pickDate, Integer idPok, Integer idGodz) throws SQLException {

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

        try {
            rezerwacje = typedQuery.getSingleResult();
            if (rezerwacje.getIdRez() > 0) {
                loginisko = true;
                System.out.println("Jest Rezerwacja!");
            } else {
                System.out.println("Nie ma rezerwacji. Zapraszamy!");
            }

        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
        return loginisko;
    }
}
