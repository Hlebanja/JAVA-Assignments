package se.chalmers.dm;

import java.sql.*;

public class JDBCTestDriver {
    // DB connection configuration
    private static String DRIVER_CLASS = "org.postgresql.Driver";
    private static String DB_USER = "postgres";
    private static String DB_PASSWORD = "";
    private static String DB_URL = "jdbc:postgresql://localhost:5432/websitedb";
    private static int EXIT_FAILURE = 1;

    public static void main(String[] args) {
        // TODO: implement JDBC1

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName(DRIVER_CLASS);
            c = DriverManager.getConnection(DB_URL,
                    DB_USER, DB_PASSWORD);

            stmt = c.createStatement();

            //Example of normal statement:
            // ResultSet rs = stmt.executeQuery("CREATE TABLE Test (name VARCHAR);" );

            PreparedStatement pstmt = c.prepareStatement("SELECT 15 AS retval;");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String retVal = rs.getString("retval");
                System.out.println(retVal);
            }

/*
            -->Example
            String name = “Leitner”;
            PreparedStatement stmt = conn.prepareStatement(“SELECT * FROM EMPLOYEE WHERE Lname = ?”);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
*/

            rs.close();
            stmt.close();
            pstmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println("Error" + e.getMessage());
            System.exit(0);
        }
    }
}
