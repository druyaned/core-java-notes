package com.github.druyaned.learn_java.vol2.chapter05;

import static com.github.druyaned.ConsoleColors.*;
import static com.github.druyaned.learn_java.App.APP_IN;
import static com.github.druyaned.learn_java.vol2.chapter05.P02Insertion.*;
import static com.github.druyaned.learn_java.vol2.chapter05.Transaction.valuesToShow;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

/**
 * Part 4 of the chapter 5 to practice with CachedRowSet, scrollable result set of JDBC;
 * <u>NOTE</u>: {@link P02Insertion#run} must be executed before the {@link run} method call.
 * 
 * @author druyaned
 * @see P02Insertion
 */
public class P04Scroll {
    
    public static void run() {
        System.out.println("\n" + bold("Running P04 Scroll"));
        
        // connection
        final String pass;
        try {
            pass = Chapter05.getPass();
        } catch (IOException | GeneralSecurityException ex) {
            Logger.getLogger(P04Scroll.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        String url = "jdbc:postgresql://localhost:5432/" + DB_NAME +
                     "?user=druyaned&password=" + pass;
        String select = String.format("SELECT * FROM %s;", SALES_NAME);
        try (CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet()) {
            int type = ResultSet.TYPE_SCROLL_INSENSITIVE;
            int concur = ResultSet.CONCUR_READ_ONLY;
            int pageSize = 5;
            crs.setType(type);
            crs.setConcurrency(concur);
            crs.setUrl(url); // or populate from ResultSet
            crs.setCommand(select);
            crs.setPageSize(pageSize);
            crs.execute();
            
            String message = "(" + bold("n") + "\\" + bold("p") + "): ";
            String entered;
            String[] columns = Sale.getColumns();
            String[][] rows = new String[pageSize][columns.length];
            String[] values = new String[columns.length];
            System.out.printf("##: ");
            Transaction.showRow(columns);
            int i = 1, prevRead = 0;
            while (crs.next()) {
                for (int k = 0; k < columns.length; ++k) {
                    values[k] = crs.getString(k + 1);
                }
                System.out.printf("%2d: %s\n", i++, valuesToShow(values));
                ++prevRead;
            }
            System.out.print(message);
            while (!(entered = APP_IN.nextLine()).isEmpty()) {
                if (entered.equals("n") && crs.nextPage()) {
                    prevRead = 0;
                    while (crs.next()) {
                        for (int j = 0; j < columns.length; ++j) {
                            values[j] = crs.getString(j + 1);
                        }
                        System.out.printf("%2d: %s\n", i++, valuesToShow(values));
                        ++prevRead;
                    }
                } else if (entered.equals("p") && crs.previousPage()) {
                    int read;
                    for (read = 0; crs.next(); ++read) {
                        for (int j = 0; j < columns.length; ++j) {
                            rows[read][j] = crs.getString(j + 1);
                        }
                    }
                    i -= (read + prevRead);
                    prevRead = read;
                    for (int x = 0; x < read; ++x) {
                        System.out.printf("%2d: %s\n", i++, valuesToShow(rows[x]));
                    }
                } else {
                    break;
                }
                System.out.print(message);
            }
            
            crs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(P03Prepared.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
