package se.chalmers.dm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectionHelper {


    public static Connection createPostgresConnection() {

        String DRIVER_CLASS = "org.postgresql.Driver";
        String DB_USER = "postgres";
        String DB_PASSWORD = "";
        String DB_URL = "jdbc:postgresql://localhost:5432/websitedb";

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName(DRIVER_CLASS);
            c = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

        } catch (Exception e) {
            System.err.println("Error" + e.getMessage());
            System.exit(0);
        }
        return c;
    }
}

