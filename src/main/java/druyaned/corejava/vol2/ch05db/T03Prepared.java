package druyaned.corejava.vol2.ch05db;

import static druyaned.ConsoleColors.bold;
import static druyaned.corejava.App.sin;
import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import static druyaned.corejava.vol2.ch05db.DB.PURCHASES_NAME;
import druyaned.corejava.vol2.ch05db.utils.PurchaseUtils;
import druyaned.corejava.vol2.ch05db.utils.TransactionUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class T03Prepared extends Topic {
    
    private final String url, user, pass;
    
    public T03Prepared(Chapter chapter, String url, String user, String pass) {
        super(chapter, 3);
        this.url = url;
        this.user = user;
        this.pass = pass;
    }
    
    @Override public String title() {
        return "Topic 03 Prepared";
    }
    
    @Override public void run() {
        String preparedSelect = String.format("SELECT * FROM %s WHERE date=?;", PURCHASES_NAME);
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
                    bold("date"),
                    bold("empty line")
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
