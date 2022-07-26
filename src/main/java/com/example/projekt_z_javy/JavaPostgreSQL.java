package com.example.projekt_z_javy;

import java.sql.*;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSQL {

    public static void writeToDatabase(String userName, String userEmail, String userPassword) throws SQLException {


        String url = "jdbc:postgresql://ec2-54-228-218-84.eu-west-1.compute.amazonaws.com:5432/de710thmop4rit";
        String user = "dpbwovovhjsruv";
        String password = "20482d0224e13b90ddcba4fd4e828746739cadef005e44a9bbad4acb6a7b64cf";

        String name = userName;
        String email = userEmail;
        String pass = userPassword;

                        /* Tutaj trzeba zmienić w tym  wzorze */
        String query = "INSERT INTO worker(name, email, password) VALUES(?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, name);
            pst.setString(2, email);
            pst.setString(3, pass);

            pst.executeUpdate();
            System.out.println("Sucessfully created!");

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgreSQL.class.getName());
            lgr.log(Level.SEVERE,ex.getMessage(),ex);
        }
    }

    /*public static void readFromDatabase(String userName, String userPassword) throws SQLException {


        String url = "jdbc:postgresql://ec2-99-80-170-190.eu-west-1.compute.amazonaws.com/dfsnc2g98asedu";
        String user = "ksfgfwspmjmggz";
        String password = "e5149346b02bfa404c2e5b1c21824d980a5c6008fdcf11d4796fe47ed9de6776";
        String zap;

        if(userName.contains("@")){ //To jest przypadek gdy podajemy maila
            String name = userName; //Mozna to zorbic lepiej poniewaz uzytkownik moze podac nazwe np. M@rek
            String pass = userPassword;
            zap = "name";
        } else {
            String email = userName;
            String pass = userPassword;
            zap = "email";
        }

        String query = "SELECT " + zap + ", password FROM worker";

        try (Connection con = DriverManager.getConnection(url, user, password);
             CallableStatement properCase = con.prepareCall(query)) {

            properCase.registerOutParameter(1,Types.VARCHAR);
            properCase.setString(2,s);
            System.out.println("Sucessfully created!");
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgreSQL.class.getName());
            lgr.log(Level.SEVERE,ex.getMessage(),ex);
        }
    }*/

}
