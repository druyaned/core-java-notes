package com.github.druyaned.learn_java.vol2.chapter05;

import static com.github.druyaned.ConsoleColors.*;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Part 1 of the chapter 5 to practice with PostgreSQL Data Base.
 * 
 * @author druyaned
 * @see <a href="https://jdbc.postgresql.org/documentation/use/">jdbc.postgresql.org usage</a>
 */
public class P01TestDB {
    
    public static void run() {
        System.out.println("\n" + bold("Running P01 TestDB"));
        // properties
        final String url;
        try {
            url = "jdbc:postgresql://localhost:5432/postgres?" +
                    "user=druyaned&password=" + Chapter05.getPass();
        } catch (IOException | GeneralSecurityException ex) {
            Logger.getLogger(P01TestDB.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        // connecting
        try {
            try (Connection connection = DriverManager.getConnection(url);
                 Statement statement = connection.createStatement()) {
                // operators
                final String[] updates = {
                    "CREATE TABLE Digits (Sign INTEGER, Note CHAR(16));",
                    "INSERT INTO Digits (Sign, Note) VALUES (1, 'One');",
                    "INSERT INTO Digits (Sign, Note) VALUES (2, 'Two');"
                };
                final String query = "SELECT * FROM Digits";
                final String drop = "DROP TABLE Digits";
                // executing
                for (String update : updates) {
                    statement.executeUpdate(update);
                }
                System.out.println("Digits:\nSign\tNote");
                try (ResultSet resultSet = statement.executeQuery(query)) {
                    while (resultSet.next()) {
                        int sign = resultSet.getInt("Sign");
                        String note = resultSet.getString(2);
                        System.out.printf("%4d\t%s\n", sign, note);
                    }
                }
                statement.executeUpdate(drop);
            }
        } catch (SQLException ex) {
            Logger.getLogger(P01TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
