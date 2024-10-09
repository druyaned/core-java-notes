package com.github.druyaned.corejava.vol2.ch05.src;

import static com.github.druyaned.ConsoleColors.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Part 5 of the chapter 5 to practice with meta-data of JDBC.
 * 
 * <P><i>NOTE</i><br>
 * {@link P02Insertion#run} must be executed before the run method call.
 * 
 * @author druyaned
 * @see P02Insertion
 */
public class P05MetaData implements Runnable {
    
    private final String url, user, pass;
    
    public P05MetaData(String url, String user, String pass) {
        this.url = url;
        this.user = user;
        this.pass = pass;
    }
    
    @Override public void run() {
        System.out.println("\n" + bold("Running P05 MetaData"));
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            // DatabaseMetaData
            DatabaseMetaData meta = connection.getMetaData();
            try (ResultSet tables = meta.getTables(
                    null, null, null, new String[] { "TABLE" }
            )) {
                System.out.printf(
                        "Tables in \"%s\":",
                        blueBold(meta.getDatabaseProductName())
                );
                while (tables.next()) {
                    System.out.print(" " + tables.getString(3)); // watch getTables doc
                }
                System.out.println();
                System.out.println("  maxConnections: " + meta.getMaxConnections());
                System.out.println("  maxStatements:  " + meta.getMaxStatements());
            }
            // ResultSetMetaData
            String select = String.format(
                    "SELECT * FROM %s;",
                    P02Insertion.PURCHASES_NAME
            );
            try (
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(select);
            ) {
                ResultSetMetaData resMeta = resultSet.getMetaData();
                System.out.println("Command: " + blueBold(select));
                int columnCount = resMeta.getColumnCount();
                System.out.println("  columnCount=" + columnCount);
                System.out.print("  columnLabels:");
                for (int i = 1; i <= columnCount; ++i) {
                    System.out.print(" " + resMeta.getColumnLabel(i));
                }
                System.out.println();
                System.out.print("  columnNames: ");
                for (int i = 1; i <= columnCount; ++i) {
                    System.out.print(" " + resMeta.getColumnName(i));
                }
                System.out.println();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
