package com.github.druyaned.learn_java.vol2.chapter05;

import static com.github.druyaned.ConsoleColors.*;
import static com.github.druyaned.learn_java.vol2.chapter05.P02Insertion.*;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Part 6 of the chapter 5 to practice with JDBC commits;
 * <u>NOTE</u>: {@link P02Insertion#run} must be executed before the {@link run} method call.
 * 
 * @author druyaned
 * @see P02Insertion
 */
public class P06Commit {
    public static void run() {
        System.out.println("\n" + bold("Running P06 Commit")); // TODO
        
        final String pass;
        try {
            pass = Chapter05.getPass();
        } catch (IOException | GeneralSecurityException ex) {
            Logger.getLogger(P06Commit.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        String url = "jdbc:postgresql://localhost:5432/" + DB_NAME +
                     "?user=druyaned&password=" + pass;
        int type = ResultSet.TYPE_SCROLL_INSENSITIVE;
        int concur = ResultSet.CONCUR_READ_ONLY;
        String select = String.format("SELECT * FROM %s;", SALES_NAME);
        int id1 = 2;
        int id2 = 5;
        String unsetRow1 = String.format("UPDATE %s SET units=0, income=0 WHERE id=%d;",
                                         SALES_NAME, id1);
        String unsetRow2 = String.format("UPDATE %s SET units=0, income=0 WHERE id=%d;",
                                         SALES_NAME, id2);
        try (Connection connection = DriverManager.getConnection(url)) {
            connection.setAutoCommit(false);
            try (Statement statement = connection.createStatement(type, concur)) {
                showSales(statement, select);
                statement.executeUpdate(unsetRow1);
                statement.executeUpdate(unsetRow2);
                connection.commit();
                System.out.printf("After %s lines with IDs %d and %d:\n",
                                  purpleBold("unsetting"), id1, id2);
                showSales(statement, select);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(P06Commit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void showSales(Statement statement, String select) throws SQLException {
        try (ResultSet rs = statement.executeQuery(select)) {
            ResultSetMetaData meta = rs.getMetaData();
            int columnsN = meta.getColumnCount();
            System.out.printf("%s:\n##: ", blueBold(SALES_NAME));
            Transaction.showRow(Sale.getColumns());
            for (int num = 1; rs.next(); ++num) {
                String[] values = new String[columnsN];
                for (int i = 0; i < columnsN; ++i) {
                    values[i] = rs.getString(i + 1);
                }
                System.out.printf("%2d: ", num);
                Transaction.showRow(values);
            }
        }
    }
}
