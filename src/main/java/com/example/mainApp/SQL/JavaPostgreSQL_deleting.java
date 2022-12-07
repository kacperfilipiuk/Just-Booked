package com.example.mainApp.SQL;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSQL_deleting {

    public static final String url = "jdbc:postgresql://localhost/";
    public static final String user = "***";
    public static final String password = "***";



    static String checkNameOfRoom;
    static String checkBeginOfHour;

    public static String getRoomName(int rId) {
        int roomId = rId;
        String query = "SELECT nazwa FROM pokoje WHERE id_p = ?";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, roomId);

            ResultSet resultSet = pst.executeQuery(); //executeQuery zwaraca nam wartosc podana przez selecta

            while (resultSet.next()){
                checkNameOfRoom = resultSet.getString(1);
                System.out.println(checkNameOfRoom);
            }

            //System.out.println("Nazwa pokoju to: " + checkNameOfRoom);
            pst.close();
            resultSet.close();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgreSQL_adding.class.getName());
            lgr.log(Level.SEVERE,ex.getMessage(),ex);
        }
        return checkNameOfRoom;
    }

    public static String getHourName(int hId) {
        int hourId = hId;
        String query = "SELECT godzina_od FROM godziny WHERE id_h = ?";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, hourId);

            ResultSet resultSet = pst.executeQuery(); //executeQuery zwaraca nam wartosc podana przez selecta

            while (resultSet.next()){
                checkBeginOfHour = resultSet.getString(1);
                //System.out.println(checkBeginOfHour);
            }

            //System.out.println("Poczatek przedzialu to: " + checkBeginOfHour);
            pst.close();
            resultSet.close();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgreSQL_adding.class.getName());
            lgr.log(Level.SEVERE,ex.getMessage(),ex);
        }
        return checkBeginOfHour;
    }



    public static void deleteReservFromDatabase(Integer reservation) throws SQLException {

        Integer idReservation = reservation;

        /* Tutaj trzeba zmienić w tym  wzorze */
        String query = "DELETE FROM rezerwacje WHERE id_rez = ?";


        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, idReservation);

            pst.executeUpdate(); //zwraca boolena (true/false)
            //System.out.println("Sucessfully deleted!");

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgreSQL_adding.class.getName());
            lgr.log(Level.SEVERE,ex.getMessage(),ex);
        }
    }


}
