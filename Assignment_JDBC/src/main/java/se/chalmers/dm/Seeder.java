package se.chalmers.dm;

import com.github.javafaker.Faker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Random;

public class Seeder {
    public Faker faker;
    public Connection connection;
    public Random random;

    Seeder(Faker faker, Connection c, Random random) {
        this.faker = faker;
        this.connection = c;
        this.random = random;
    }

    public void createUserTable() {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(QueryHelper.sqlQuery("create_user_table.sql"));
            stmt.close();
        } catch (Exception e) {
            System.err.println("Error" + e.getMessage());
            System.exit(0);
        }
    }

    public void insertFakeUsers(int n) {
        /*    (c) seeder.insertFakeUsers(12); creates 12 fake users and stores them in the database. Use
    the java-faker library3 for generating valid SSNs, names, email addresses, and randomize the
    active flag.*/

        try {
            //<= n because n is the number of rows we need, provided in SeederTestDriver.java
            for (int i = 1; i <= n; i++) {
                //for each iteration, create fake attributes
                String Ssn = faker.idNumber().ssnValid();
                String Fname = faker.name().firstName();
                String Lname = faker.name().lastName();
                String email = faker.internet().emailAddress();
                Boolean isActive = faker.random().nextBoolean();

                /* //for testing purposes
                System.out.println("---");
                System.out.println(Ssn);
                System.out.println(Fname);
                System.out.println(Lname);
                System.out.println(email);
                System.out.println(isActive);
                System.out.println("---");
                */

                PreparedStatement stmt = connection.prepareStatement(
                        QueryHelper.sqlQuery("insert_user.sql"));

                //insert fake attributes into the prepared query.
                stmt.setString(1, Ssn);
                stmt.setString(2, Fname);
                stmt.setString(3, Lname);
                stmt.setString(4, email);
                stmt.setBoolean(5, isActive);

                stmt.executeUpdate();
                stmt.close();

            }
        } catch (Exception e) {
            System.err.println("Error" + e.getMessage());
            System.exit(0);
        }
    }

    public void createWebPageTable() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(QueryHelper.sqlQuery("create_webpage_table.sql"));
            statement.close();
        } catch (Exception e) {
            System.err.println("Error" + e.getMessage());
            System.exit(0);
        }
    }

    public void insertFakeUsersWithWebPage(int n) {
       /* (e) seeder.insertFakeUsersWithWebPage(200); creates 200 fake users where every user is
        stored in the database, a single webpage authored by this user is created and also stored in the
        database. Use the java-faker library again to generate URLs, content using the Chuck Norris
        facts, and randomize the popularity score in the interval [0,100].*/

        insertFakeUsers(n);

        try {
            for (int i = 1; i <= n; i++) {
                //for each iteration, create fake attributes
                String URL = faker.internet().url();
                String content = faker.chuckNorris().fact();
                int popularity = faker.number().numberBetween(0, 100);

                //PRIMARY KEY ID is generated automatically. Author again will follow i numeration.
                PreparedStatement stmt = connection.prepareStatement(
                        QueryHelper.sqlQuery("insert_webpage.sql"));

                stmt.setInt(1, i);
                stmt.setString(2, URL);
                stmt.setString(3, content);
                stmt.setInt(4, popularity);

                stmt.executeUpdate();
                stmt.close();
            }
        } catch (Exception e) {
            System.err.println("Error" + e.getMessage());
            System.exit(0);
        }
    }

}
