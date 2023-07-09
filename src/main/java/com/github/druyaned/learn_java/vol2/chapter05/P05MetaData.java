package com.github.druyaned.learn_java.vol2.chapter05;

import static com.github.druyaned.ConsoleColors.*;
import static com.github.druyaned.learn_java.vol2.chapter05.P02Insertion.*;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Part 5 of the chapter 5 to practice with meta data of JDBC;
 * <u>NOTE</u>: {@link P02Insertion#run} must be executed before the {@link run} method call.
 * 
 * @author druyaned
 * @see P02Insertion
 */
public class P05MetaData {
    
    public static void run() {
        System.out.println("\n" + bold("Running P05 MetaData"));
        
        final String pass;
        try {
            pass = Chapter05.getPass();
        } catch (IOException | GeneralSecurityException ex) {
            Logger.getLogger(P05MetaData.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        String url = "jdbc:postgresql://localhost:5432/" + DB_NAME +
                     "?user=druyaned&password=" + pass;
        
        try (Connection connection = DriverManager.getConnection(url)) {
            
            // DatabaseMetaData
            DatabaseMetaData meta = connection.getMetaData();
            try (ResultSet tables = meta.getTables(null, null, null, new String[] { "TABLE" })) {
                System.out.printf("Tables in \"%s\":", blueBold(meta.getDatabaseProductName()));
                while (tables.next()) {
                    System.out.print(" " + tables.getString(3)); // watch getTables doc
                }
                System.out.println("\n  maxConnections=" + meta.getMaxConnections());
                System.out.println("  maxStatements=" + meta.getMaxStatements());
            }
            
            // ResultSetMetaData
            String select = String.format("SELECT * FROM %s;", PURCHASES_NAME);
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(select)) {
                
                ResultSetMetaData resMeta = resultSet.getMetaData();
                System.out.println("Command: " + blueBold(select));
                int columnCount = resMeta.getColumnCount();
                System.out.println("  columnCount=" + columnCount);
                System.out.print("  columnLabels:");
                for (int i = 1; i <= columnCount; ++i) {
                    System.out.print(" " + resMeta.getColumnLabel(i));
                }
                System.out.print("\n  columnNames:");
                for (int i = 1; i <= columnCount; ++i) {
                    System.out.print(" " + resMeta.getColumnName(i));
                }
                System.out.println();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(P05MetaData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
