package com.example.projekt_z_javy;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSQL_lobby {

    public static void checkUserCor() throws SQLException {

        String url = "jdbc:postgresql://ec2-54-228-218-84.eu-west-1.compute.amazonaws.com:5432/de710thmop4rit";
        String user = "dpbwovovhjsruv";
        String password = "20482d0224e13b90ddcba4fd4e828746739cadef005e44a9bbad4acb6a7b64cf";

        /* Tutaj trzeba zmienić w tym  wzorze */
        String query = "SELECT nazwa FROM pokoje";


        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            ResultSet resultSet = pst.executeQuery();

            while(resultSet.next()){

            }

            pst.executeUpdate();
            System.out.println("Sucessfully created!");

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgreSQL.class.getName());
            lgr.log(Level.SEVERE,ex.getMessage(),ex);
        }
    }
}
