package com.github.druyaned.corejava.vol2.ch05.src;

import static com.github.druyaned.ConsoleColors.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Part 6 of the chapter 5 to practice with JDBC commits.
 * 
 * <P><i>NOTE</i><br>
 * {@link P02Insertion#run} must be executed before the run method call.
 * 
 * @author druyaned
 * @see P02Insertion
 */
public class P06Commit implements Runnable {
    
    private final String url, user, pass;
    
    public P06Commit(String url, String user, String pass) {
        this.url = url;
        this.user = user;
        this.pass = pass;
    }
    
    @Override public void run() {
        System.out.println("\n" + bold("Running P06 Commit")); // TODO
        String select = String.format("SELECT * FROM %s;", P02Insertion.SALES_NAME);
        int id1 = 2;
        int id2 = 5;
        String unsetRow1 = String.format(
                "UPDATE %s SET units=0, income=0 WHERE id=%d;",
                P02Insertion.SALES_NAME, id1
        );
        String unsetRow2 = String.format(
                "UPDATE %s SET units=0, income=0 WHERE id=%d;",
                P02Insertion.SALES_NAME, id2
        );
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            connection.setAutoCommit(false);
            try (Statement statement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY
            )) {
                printSales(statement, select);
                statement.executeUpdate(unsetRow1);
                statement.executeUpdate(unsetRow2);
                connection.commit();
                System.out.printf(
                        "After %s lines with IDs %d and %d:\n",
                        purpleBold("unsetting"), id1, id2
                );
                printSales(statement, select);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    private static void printSales(Statement statement, String select) throws SQLException {
        try (ResultSet rs = statement.executeQuery(select)) {
            ResultSetMetaData meta = rs.getMetaData();
            int columnsN = meta.getColumnCount();
            System.out.printf("%s:\n##: ", blueBold(P02Insertion.SALES_NAME));
            TransactionUtils.printRow(SaleUtils.getColumns());
            for (int num = 1; rs.next(); num++) {
                String[] values = new String[columnsN];
                for (int i = 0; i < columnsN; i++) {
                    values[i] = rs.getString(i + 1);
                }
                System.out.printf("%2d: ", num);
                TransactionUtils.printRow(values);
            }
        }
    }
    
}
