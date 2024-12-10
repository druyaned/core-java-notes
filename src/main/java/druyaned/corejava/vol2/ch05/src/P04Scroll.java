package druyaned.corejava.vol2.ch05.src;

import static druyaned.ConsoleColors.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import static druyaned.corejava.App.sin;

/**
 * Part 4 of the chapter 5 to practice with CachedRowSet, scrollable result set of JDBC.
 * 
 * <P><i>NOTE</i><br>
 * {@link P02Insertion#run} must be executed before the run method call.
 * 
 * @author druyaned
 * @see P02Insertion
 */
public class P04Scroll implements Runnable {
    
    private final String url, user, pass;
    
    public P04Scroll(String url, String user, String pass) {
        this.url = url;
        this.user = user;
        this.pass = pass;
    }
    
    @Override public void run() {
        System.out.println("\n" + bold("Running P04 Scroll"));
        String select = String.format(
                "SELECT * FROM %s;",
                P02Insertion.SALES_NAME
        );
        try (CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet()) {
            int pageSize = 5;
            crs.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
            crs.setConcurrency(ResultSet.CONCUR_READ_ONLY);
            crs.setUrl(url); // or populate from ResultSet
            crs.setUsername(user);
            crs.setPassword(pass);
            crs.setCommand(select);
            crs.setPageSize(pageSize);
            crs.execute();
            final String inputPrompt = "(" + bold("n") + "\\" + bold("p") + "): ";
            String entered;
            String[] columns = SaleUtils.getColumns();
            String[][] rows = new String[pageSize][columns.length];
            String[] values = new String[columns.length];
            System.out.printf("##: ");
            TransactionUtils.printRow(columns);
            int i = 1, prevRead = 0;
            while (crs.next()) {
                for (int k = 0; k < columns.length; ++k) {
                    values[k] = crs.getString(k + 1);
                }
                System.out.printf(
                        "%2d: %s\n",
                        i++, TransactionUtils.rowToPrint(values)
                );
                ++prevRead;
            }
            System.out.print(inputPrompt);
            while (!(entered = sin.nextLine()).isEmpty()) {
                if (entered.equals("n") && crs.nextPage()) {
                    prevRead = 0;
                    while (crs.next()) {
                        for (int j = 0; j < columns.length; ++j) {
                            values[j] = crs.getString(j + 1);
                        }
                        System.out.printf(
                                "%2d: %s\n",
                                i++, TransactionUtils.rowToPrint(values)
                        );
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
                        System.out.printf(
                                "%2d: %s\n",
                                i++, TransactionUtils.rowToPrint(rows[x])
                        );
                    }
                } else {
                    break;
                }
                System.out.print(inputPrompt);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
