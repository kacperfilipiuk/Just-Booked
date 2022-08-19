package com.example.projekt_z_javy;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSQL_register {

    public static final String url = "jdbc:postgresql://ec2-54-228-218-84.eu-west-1.compute.amazonaws.com:5432/de710thmop4rit";
    public static final String user = "dpbwovovhjsruv";
    public static final String password = "20482d0224e13b90ddcba4fd4e828746739cadef005e44a9bbad4acb6a7b64cf";

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
            System.out.println("Sucessfully created!");

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
                System.out.println(checkUser);

                if (!checkUser.isEmpty()) {
                    loginisko = true;
                    System.out.println("Jest");
                    break;
                }
            //pst.executeUpdate();
        }

            System.out.println("okkk");
            resultSet.close();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgreSQL_register.class.getName());
            lgr.log(Level.SEVERE,ex.getMessage(),ex);
        }

        return loginisko;
    }
}
