package druyaned.corejava.vol2.ch05db;

import static druyaned.ConsoleColors.blueBold;
import static druyaned.ConsoleColors.purpleBold;
import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <a href="https://www.h2database.com/html/main.html">H2-DataBase main page</a>
 * @author druyaned
 */
public class T01DB extends Topic {
    
    private final String url, user, pass;
    
    public T01DB(Chapter chapter, String url, String user, String pass) {
        super(chapter, 1);
        this.url = url;
        this.user = user;
        this.pass = pass;
    }
    
    @Override public String title() {
        return "Topic 01 Database";
    }
    
    @Override public void run() {
        try (
                Connection connection = DriverManager.getConnection(url, user, pass);
                Statement statement = connection.createStatement();
        ) {
            System.out.println("Max statement length: "
                    + connection.getMetaData().getMaxStatements());
            // Operators
            final String[] updates = {
                "CREATE TABLE Digits (Sign INTEGER, Note CHAR(16));",
                "INSERT INTO Digits (Sign, Note) VALUES (1, 'One');",
                "INSERT INTO Digits (Sign, Note) VALUES (2, 'Two');",
                "INSERT INTO Digits (Sign, Note) VALUES (3, 'Three');"
            };
            final String query = "SELECT * FROM Digits;";
            final String drop = "DROP TABLE Digits;";
            // Print operators
            System.out.println(purpleBold("Updates:"));
            for (String update : updates) {
                System.out.println("  \"" + update + "\"");
            }
            System.out.println(purpleBold("Query") + ": \"" + query + "\"");
            System.out.println(purpleBold("Drop") + ":  \"" + drop + "\"");
            // Updates
            for (String update : updates) {
                statement.executeUpdate(update);
            }
            // Query
            StringBuilder builder = new StringBuilder();
            final int W = 5;
            builder.append(blueBold("Digits"))
                    .append('\n')
                    .append(String.format("  %" + W + "s | %-" + W + "s\n",
                            "Sign", "Note"))
                    .append(String.format("--%" + W + "s-+-%" + W + "s\n",
                            "-".repeat(W), "-".repeat(W)));
            try (ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    int sign = resultSet.getInt("Sign");
                    String note = resultSet.getString(2);
                    builder.append(String.format("  %" + W + "d | %" + W + "s\n",
                            sign, note));
                }
            }
            System.out.println(purpleBold("Result set") + ":");
            System.out.print(builder.toString());
            // Drop
            statement.executeUpdate(drop);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
