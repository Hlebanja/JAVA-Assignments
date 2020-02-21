package se.chalmers.dm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Queries {

    public List<User> findInactiveUsers(Connection c) {
       /* (a) List<User> activeUsers = queries.findInactiveUsers(connection); returns the
        list of users which are not active from the database. Apply changes to User.java if necessary.*/

        List<User> userList = new ArrayList<>();

        try {

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(QueryHelper.sqlQuery("find_inactive_users.sql"));

            while (rs.next()) {
                int ID = rs.getInt("id");
                String ssn = rs.getString("ssn");
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String email = rs.getString("email");
                Boolean isActive = rs.getBoolean("isactive");

                userList.add(new User(ID, ssn, fname, lname, email, isActive));
            }
        } catch (Exception e) {
            System.err.println("Error" + e.getMessage());
            System.exit(0);
        }

    return userList;
    }


    public List<String> findSpecialQuotes(Connection c) {
        /*(b) List<String> quotes = queries.findSpecialQuotes(connection); returns a list of
        strings with the content of all webpages that contain the word when using case insensitive
        search.*/

        List<String> webPageContent = new ArrayList<>();

        try {
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(QueryHelper.sqlQuery("find_special_quotes.sql"));

            while (rs.next()) {
                String contentString = rs.getString("content");
                webPageContent.add(contentString);
            }
        } catch (Exception e) {
            System.err.println("Error" + e.getMessage());
            System.exit(0);
        }

        return webPageContent;
    }

}
