package ru.clevertec.check.util.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class DatabaseConnection {

    public static Connection initializeDatabase(Map<String, String> properties) {
        String dbDriver = "org.postgresql.Driver";
        String dbURL = properties.get("url");
        String dbUsername = properties.get("username");
        String dbPassword = properties.get("password");
        try {
            Class.forName(dbDriver);
            return DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}