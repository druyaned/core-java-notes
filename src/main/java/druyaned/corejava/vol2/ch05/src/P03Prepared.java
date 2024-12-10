package druyaned.corejava.vol2.ch05.src;

import static druyaned.ConsoleColors.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static druyaned.corejava.App.sin;

/**
 * Part 3 of the chapter 5 to practice with SQL prepared queries.
 * 
 * <P><i>NOTE</i><br>
 * {@link P02Insertion#run} must be executed before the run method call.
 * 
 * @author druyaned
 * @see P02Insertion
 */
public class P03Prepared implements Runnable {
    
    private final String url, user, pass;
    
    public P03Prepared(String url, String user, String pass) {
        this.url = url;
        this.user = user;
        this.pass = pass;
    }
    
    @Override public void run() {
        System.out.println("\n" + bold("Running P03 Prepared"));
        String preparedSelect = String.format(
                "SELECT * FROM %s WHERE date=?;",
                P02Insertion.PURCHASES_NAME
        );
        try (
                Connection connection = DriverManager.getConnection(url, user, pass);
                PreparedStatement prepStat = connection.prepareStatement(
                        preparedSelect,
                        ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_READ_ONLY
                );
        ) {
            String inputPrompt = String.format(
                    "Date of purchase: [%s: YYYY-MM-DD] [%s: to exit]: ",
                    bold("date"), bold("empty line")
            );
            String date;
            String[] columns = PurchaseUtils.getColumns();
            System.out.print(inputPrompt);
            while (!(date = sin.nextLine()).isEmpty()) {
                prepStat.setDate(1, Date.valueOf(date));
                try (ResultSet resultSet = prepStat.executeQuery()) {
                    TransactionUtils.printRow(columns);
                    while (resultSet.next()) {
                        String[] values = new String[columns.length];
                        for (int i = 0; i < columns.length; ++i) {
                            values[i] = resultSet.getString(i + 1);
                        }
                        TransactionUtils.printRow(values);
                    }
                }
                System.out.print(inputPrompt);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
