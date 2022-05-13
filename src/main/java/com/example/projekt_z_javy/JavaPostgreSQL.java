package com.example.projekt_z_javy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSQL {

    public static void writeToDatabase(String userName, String userEmail, String userPassword) throws SQLException {


        String url = "jdbc:postgresql://ec2-99-80-170-190.eu-west-1.compute.amazonaws.com/dfsnc2g98asedu";
        String user = "ksfgfwspmjmggz";
        String password = "e5149346b02bfa404c2e5b1c21824d980a5c6008fdcf11d4796fe47ed9de6776";

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

}
