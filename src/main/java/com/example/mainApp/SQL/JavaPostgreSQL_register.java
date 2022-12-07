package com.example.mainApp.SQL;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSQL_register {

    public static final String url = "jdbc:postgresql://localhost/";
    public static final String user = "***";
    public static final String password = "***";


    /**
     * @param userEmail mail, który podaje uzytkownik do założenia konta
     */

    public static void writeToDatabase(String userName, String userPassword,  String userEmail) throws SQLException {

        String name = userName;
        String email2 = userEmail;
        email2.toLowerCase();
        String pass = userPassword;

                        /* Tutaj trzeba zmienić w tym  wzorze */
        String query = "INSERT INTO uzytkownicy(login, haslo, email) VALUES(?, ?, ?)";


        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, name);
            pst.setString(2, pass);
            pst.setString(3, email2);

            pst.executeUpdate(); //zwraca boolena (true/false)
            //System.out.println("Sucessfully created!");

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgreSQL_register.class.getName());
            lgr.log(Level.SEVERE,ex.getMessage(),ex);
        }
    }

    /**
     *Metoda odpowiedzialna za sprawdzenie powtórzenia loginu lub maila, rejestrującego sie uzytkownika
     *
     */

    public static boolean checkDatabase(String userName, String userEmail) throws SQLException {

        boolean loginisko = false;
        String name = userName;
        String email2 = userEmail;


        String query = "SELECT login FROM uzytkownicy WHERE (login = ? OR email = ?)";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            email2.toLowerCase();

            pst.setString(1, name);
            pst.setString(2, email2);

            ResultSet resultSet = pst.executeQuery(); //executeQuery zwaraca nam wartosc podana przez selecta

            while (resultSet.next()){
                String checkUser = resultSet.getString(1);
                //System.out.println(checkUser);

                if (!checkUser.isEmpty()) {
                    loginisko = true;
                    //System.out.println("Jest");
                    break;
                }
            //pst.executeUpdate();
        }

            //System.out.println("okkk");
            resultSet.close();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgreSQL_register.class.getName());
            lgr.log(Level.SEVERE,ex.getMessage(),ex);
        }

        return loginisko;
    }
}
