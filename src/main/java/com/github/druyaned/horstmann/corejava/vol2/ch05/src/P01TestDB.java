package com.github.druyaned.horstmann.corejava.vol2.ch05.src;

import static com.github.druyaned.ConsoleColors.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Part 1 of the chapter 5 to practice with H2-DataBase.
 * @author druyaned
 * @see <a href="https://www.h2database.com/html/main.html">H2-DataBase main page</a>
 */
public class P01TestDB implements Runnable {
    
    private final String url, user, pass;
    
    public P01TestDB(String url, String user, String pass) {
        this.url = url;
        this.user = user;
        this.pass = pass;
    }
    
    @Override public void run() {
        System.out.println("\n" + bold("Running P01 TestDB"));
        try {
            try (
                    Connection connection = DriverManager.getConnection(url, user, pass);
                    Statement statement = connection.createStatement();
            ) {
                // operators
                final String[] updates = {
                    "CREATE TABLE Digits (Sign INTEGER, Note CHAR(16));",
                    "INSERT INTO Digits (Sign, Note) VALUES (1, 'One');",
                    "INSERT INTO Digits (Sign, Note) VALUES (2, 'Two');",
                    "INSERT INTO Digits (Sign, Note) VALUES (3, 'Three');"
                };
                final String query = "SELECT * FROM Digits;";
                final String drop = "DROP TABLE Digits;";
                // print operators
                System.out.println(purpleBold("Updates:"));
                for (String update : updates) {
                    System.out.println("  \"" + update + "\"");
                }
                System.out.println(purpleBold("Query") + ": \"" + query + "\"");
                System.out.println(purpleBold("Drop") + ":  \"" + drop + "\"");
                // updates
                for (String update : updates) {
                    statement.executeUpdate(update);
                }
                // query
                StringBuilder builder = new StringBuilder();
                final int W = 5;
                builder.append(blueBold("Digits"))
                        .append('\n')
                        .append(String.format(
                                "  %" + W + "s | %-" + W + "s\n",
                                "Sign", "Note"
                        )).append(String.format(
                                "--%" + W + "s-+-%" + W + "s\n",
                                "-".repeat(W), "-".repeat(W)
                        ));
                try (ResultSet resultSet = statement.executeQuery(query)) {
                    while (resultSet.next()) {
                        int sign = resultSet.getInt("Sign");
                        String note = resultSet.getString(2);
                        builder.append(String.format(
                                "  %" + W + "d | %" + W + "s\n",
                                sign, note
                        ));
                    }
                }
                System.out.println(purpleBold("Result set") + ":");
                System.out.print(builder.toString());
                // drop
                statement.executeUpdate(drop);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
