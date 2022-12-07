package com.example.mainApp.SQL;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSQL_adding {

    public static final String url = "jdbc:postgresql://localhost/";
    public static final String user = "***";
    public static final String password = "***";

    static Integer checkNumberOfUser;
    static Integer checkNumberOfRoom;
    static Integer checkNumberOfHour;

    //Funkcja odpowiedzialna z azwrócenie id uzytkownika z bazy danych
    public static int getUserId(String uN) {
        String userName = uN;

        String query = "SELECT id_u FROM uzytkownicy WHERE login = ?";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, userName);

            ResultSet resultSet = pst.executeQuery(); //executeQuery zwaraca nam wartosc podana przez selecta

            while (resultSet.next()){
                checkNumberOfUser = resultSet.getInt(1);
                //System.out.println(checkNumberOfUser);
            }

            //System.out.println("Numer uzytkownika to: " + checkNumberOfUser);
            pst.close();
            resultSet.close();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgreSQL_adding.class.getName());
            lgr.log(Level.SEVERE,ex.getMessage(),ex);
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

            while (resultSet.next()){
                checkNumberOfRoom = resultSet.getInt(1);
                //System.out.println(checkNumberOfRoom);
            }

            //System.out.println("Numer id pokoju to: " + checkNumberOfRoom);
            pst.close();
            resultSet.close();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgreSQL_adding.class.getName());
            lgr.log(Level.SEVERE,ex.getMessage(),ex);
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

            while (resultSet.next()){
                checkNumberOfHour = resultSet.getInt(1);
                //System.out.println(checkNumberOfHour);
            }

            //System.out.println("Numer id godziny to: " + checkNumberOfHour);
            pst.close();
            resultSet.close();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgreSQL_adding.class.getName());
            lgr.log(Level.SEVERE,ex.getMessage(),ex);
        }

        return checkNumberOfHour;
    }

    public static void writeReservToDatabase(Date pD, Integer room, Integer hour, Integer userNum) throws SQLException {

        Date pickedDate = pD;
        Integer roomName = room;
        Integer pickedHour = hour;
        Integer userNumber= userNum;

                        /* Tutaj trzeba zmienić w tym  wzorze */
        String query = "INSERT INTO rezerwacje(id_p, id_u, id_h,data) VALUES(?, ?, ?,?)";


        //ID_p - pokoje -> po naziwe sprawdzam id i odysłam tutaj
        //ID_u - pobieram nazwe tak jak wczesniej i sprawdzam w bazie danych
        //ID_h - na ten moment wystarczy sprawdzić godzine od jakie ma id
        //date - mozna wpisac odrau

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, roomName);
            pst.setInt(2, userNumber);
            pst.setInt(3, pickedHour);
            pst.setDate(4, pickedDate);

            pst.executeUpdate(); //zwraca boolena (true/false)
            //System.out.println("Sucessfully created!");

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgreSQL_adding.class.getName());
            lgr.log(Level.SEVERE,ex.getMessage(),ex);
        }
    }

    /**
     *Metoda odpowiedzialna za sprawdzenie powtórzenia loginu lub maila, rejestrującego sie uzytkownika
     *
     */

    public static boolean checkDatabase(Date pickDate, Integer idPok, Integer idGodz) throws SQLException {

        boolean loginisko = false;
        Date date = pickDate;
        Integer numerPokoju = idPok;
        Integer numerGodziny = idGodz;


        String query = "SELECT id_rez FROM rezerwacje WHERE (id_p = ? AND id_h = ? AND data = ?)";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, numerPokoju);
            pst.setInt(2, numerGodziny);
            pst.setDate(3, date);

            ResultSet resultSet = pst.executeQuery(); //executeQuery zwaraca nam wartosc podana przez selecta

            while (resultSet.next()){
                Integer checkReserv = resultSet.getInt(1);
                //System.out.println(checkReserv);

                if (checkReserv!=null) {
                    loginisko = true;
                    //System.out.println("Jest taka rezerwacja");
                    break;
                }
            //pst.executeUpdate();
        }
            pst.close();
            resultSet.close();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgreSQL_adding.class.getName());
            lgr.log(Level.SEVERE,ex.getMessage(),ex);
        }

        return loginisko;
    }
}
