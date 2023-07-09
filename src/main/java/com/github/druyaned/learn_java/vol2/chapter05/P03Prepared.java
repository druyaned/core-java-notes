package com.github.druyaned.learn_java.vol2.chapter05;

import static com.github.druyaned.ConsoleColors.*;
import static com.github.druyaned.learn_java.vol2.chapter05.P02Insertion.*;
import static com.github.druyaned.learn_java.App.APP_IN;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Part 3 of the chapter 5 to practice with SQL prepared queries;
 * <u>NOTE</u>: {@link P02Insertion#run} must be executed before the {@link run} method call.
 * 
 * @author druyaned
 * @see P02Insertion
 */
public class P03Prepared {
    
    /**
     * Part 3 of the chapter 5 to practice with SQL prepared queries;
     * <u>NOTE</u>: {@link P02Insertion#run} must be executed before the {@link run} method call.
     */
    public static void run() {
        System.out.println("\n" + bold("Running P03 Prepared"));
        
        // connection
        String pass;
        try {
            pass = Chapter05.getPass();
        } catch (IOException | GeneralSecurityException exc) {
            Logger.getLogger(P03Prepared.class.getName()).log(Level.SEVERE, null, exc);
            return;
        }
        String url = "jdbc:postgresql://localhost:5432/" + DB_NAME +
                     "?user=druyaned&password=" + pass;
        String preparedSelect = String.format("SELECT * FROM %s WHERE date=?;", PURCHASES_NAME);
        int k = Statement.RETURN_GENERATED_KEYS; // here isn't useful cause there is no INSERT
        int c = ResultSet.CONCUR_READ_ONLY;
        
        // prepared statement
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement prepStat = connection.prepareStatement(preparedSelect, k, c)) {
            
            String message = String.format("Date of purchase: [%s: YYYY-MM-DD] [%s: to exit]: ",
                                           bold("date"), bold("empty line"));
            System.out.print(message);
            String date;
            String[] columns = Purchase.getColumns();
            while (!(date = APP_IN.nextLine()).isEmpty()) {
                prepStat.setDate(1, Date.valueOf(date));
                try (ResultSet resultSet = prepStat.executeQuery()) {
                    Transaction.showRow(columns);
                    while (resultSet.next()) {
                        String[] values = new String[columns.length];
                        for (int i = 0; i < columns.length; ++i) {
                            values[i] = resultSet.getString(i + 1);
                        }
                        Transaction.showRow(values);
                    }
                }
                System.out.print(message);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(P03Prepared.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
