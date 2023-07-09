package com.github.druyaned.learn_java.vol2.chapter05;

import static com.github.druyaned.ConsoleColors.*;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Part 2 of the chapter 5 to practice with JDBC of PostgreSQL and SQL insertion.
 * 
 * @author druyaned
 * @see P03Prepared
 * @see P04Scroll
 * @see P05MetaData
 */
public class P02Insertion {
    
    public static final int YEAR = 2016;
    public static final int MONTH = 8; // transactions_2016_8
    public final static String DB_NAME = "transactions_" + YEAR + "_" + MONTH;
    public final static String SALES_NAME = "sales";
    public final static String PURCHASES_NAME = "purchases";
    
    public static void run() {
        System.out.println("\n" + bold("Running P02 Insertion"));
        
        // data
        final int N = 16;
        
        Sale[] sales = Sale.generate(N, YEAR, MONTH);
        Purchase[] purchases = Purchase.generate(N, YEAR, MONTH);
        
        // commands
        final String[] dropCommands = generateDrop(DB_NAME, SALES_NAME, PURCHASES_NAME);
        final String[] createCommands = generateCreate(SALES_NAME, PURCHASES_NAME);
        final String[] insertCommands = generateInsert(N,
                                                       sales, SALES_NAME,
                                                       purchases, PURCHASES_NAME);
        String selectSales = String.format("SELECT * FROM %s;", SALES_NAME);
        String selectPurchases = String.format("SELECT * FROM %s;", PURCHASES_NAME);
        
        // connect to postgres to execute drop commands
        final String pass;
        try {
            pass = Chapter05.getPass();
        } catch (IOException | GeneralSecurityException ex) {
            Logger.getLogger(P01TestDB.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        String url = "jdbc:postgresql://localhost:5432/postgres?" +
                "user=druyaned&password=" + pass;
        
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {
            
            // executing drop-commands
            System.out.println(purpleBold("Executing drop-commands") + "...");
            for (int i = 0; i < dropCommands.length; ++i) {
                try {
                    statement.executeUpdate(dropCommands[i]);
                } catch (SQLException exc) {
                    System.out.println(redBold("Can't execute") + ": " + dropCommands[i]);
                    Iterator<Throwable> excIter = exc.iterator();
                    for (int j = 1; excIter.hasNext(); ++j) {
                        System.out.printf("#%d: exception={%s}\n", j, excIter.next());
                    }
                }
            }
            
        } catch (SQLException exc) {
            Logger.getLogger(P02Insertion.class.getName()).log(Level.SEVERE, null, exc);
            return;
        }
        
        // connect to transactions to execute remaining commands
        url = "jdbc:postgresql://localhost:5432/" + DB_NAME + "?user=druyaned&password=" + pass;
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {
            
            // executing create-, insert-, and select-commands
            System.out.println(purpleBold("Executing create-commands") + "...");
            for (int i = 0; i < createCommands.length; ++i) {
                statement.executeUpdate(createCommands[i]);
            }
            System.out.println(purpleBold("Executing insert-commands") + "...");
            for (int i = 0; i < insertCommands.length; ++i) {
                statement.executeUpdate(insertCommands[i]);
            }
            System.out.println(purpleBold("Executing select-commands") + "...");
            
            // select-commands and showing tables
            String[] saleColumns = Sale.getColumns();
            String[] purchaseColumns = Purchase.getColumns();
            String[][] rowsOfSales = new String[N][saleColumns.length];
            String[][] rowsOfPurchases = new String[N][purchaseColumns.length];
            if (statement.execute(selectSales)) {
                // reason of [execute instead of executeQuer]: just to practice
                try (ResultSet resultSet = statement.getResultSet()) {
                    for (int i = 0; resultSet.next() && i < N; ++i) {
                        for (int j = 0; j < saleColumns.length; ++j) {
                            rowsOfSales[i][j] = resultSet.getString(j + 1);
                        }
                    }
                }                
            }
            Transaction.showTable(SALES_NAME, Sale.getColumns(), rowsOfSales);
            if (statement.execute(selectPurchases)) {
                // reason of [execute instead of executeQuer]: just to practice
                try (ResultSet resultSet = statement.getResultSet()) {
                    for (int i = 0; resultSet.next() && i < N; ++i) {
                        for (int j = 0; j < purchaseColumns.length; ++j) {
                            rowsOfPurchases[i][j] = resultSet.getString(j + 1);
                        }
                    }
                }                
            }
            Transaction.showTable(PURCHASES_NAME, Purchase.getColumns(), rowsOfPurchases);
            
        } catch (SQLException exc) {
            Logger.getLogger(P02Insertion.class.getName()).log(Level.SEVERE, null, exc);
        }
    }
    
    private static String[] generateDrop(String DBName, String salesName, String purchasesName) {
        return new String[] { // executing in "postgres" DB
            String.format("DROP DATABASE %s;", DBName),
            String.format("DROP TABLE %s;", salesName), // like a mistake by inattention
            String.format("DROP TABLE %s;", purchasesName), // like a mistake by inattention
            String.format("CREATE DATABASE %s;", DBName) // and this is intentional
        };
    }
    
    private static String[] generateCreate(String salesName, String purchasesName) {
        return new String[] { // other commands will be executed in "cashflows" DB
            String.format("CREATE TABLE %s (%s);", salesName, Sale.PARAMETERS),
            String.format("CREATE TABLE %s (%s);", purchasesName, Purchase.PARAMETERS)
        };
    }
    
    private static String [] generateInsert(int N,
                                            Transaction[] sales, String salesName,
                                            Transaction[] purchases, String purchasesName) {
        String [] insertCommands = new String[N * 2]; // insertion into 2 tables
        for (int i = 0; i < N; ++i) { // sales
            insertCommands[i] = String.format("INSERT INTO %s VALUES (%s);",
                                              salesName, sales[i].getSqlValues());
        }
        for (int i = N; i < N * 2; ++i) { // purchases
            insertCommands[i] = String.format("INSERT INTO %s VALUES (%s);",
                                              purchasesName, purchases[i - N].getSqlValues());
        }
        return insertCommands;
    }

}
