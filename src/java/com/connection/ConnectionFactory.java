package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {

    public static final String URL = "jdbc:postgresql://localhost/db";

    public static final String USER = "my_user";

    public static final String PASS = "my_password";

    public static Connection getConnection() {

        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASS);

        } catch (SQLException ex) {

            throw new RuntimeException("Error connecting to the database", ex);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {

        Connection connection = ConnectionFactory.getConnection();

    }
}
