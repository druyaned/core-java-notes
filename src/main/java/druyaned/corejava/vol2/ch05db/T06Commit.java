package druyaned.corejava.vol2.ch05db;

import static druyaned.ConsoleColors.blueBold;
import static druyaned.ConsoleColors.purpleBold;
import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import static druyaned.corejava.vol2.ch05db.DB.SALES_NAME;
import druyaned.corejava.vol2.ch05db.utils.SaleUtils;
import druyaned.corejava.vol2.ch05db.utils.TransactionUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class T06Commit extends Topic {
    
    private final String url, user, pass;
    
    public T06Commit(Chapter chapter, String url, String user, String pass) {
        super(chapter, 6);
        this.url = url;
        this.user = user;
        this.pass = pass;
    }
    
    @Override public String title() {
        return "Topic 06 Commit";
    }
    
    @Override public void run() {
        String select = String.format("SELECT * FROM %s;", SALES_NAME);
        int id1 = 2;
        int id2 = 5;
        String unsetRow1 = String.format("UPDATE %s SET units=0, income=0 WHERE id=%d;",
                SALES_NAME, id1);
        String unsetRow2 = String.format("UPDATE %s SET units=0, income=0 WHERE id=%d;",
                SALES_NAME, id2);
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            connection.setAutoCommit(false);
            try (
                    Statement statement = connection.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY
                    );
            ) {
                printSales(statement, select);
                statement.executeUpdate(unsetRow1);
                statement.executeUpdate(unsetRow2);
                connection.commit();
                System.out.printf("After %s lines with IDs %d and %d:\n",
                        purpleBold("unsetting"), id1, id2);
                printSales(statement, select);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    private void printSales(Statement statement, String select) throws SQLException {
        try (ResultSet rs = statement.executeQuery(select)) {
            ResultSetMetaData meta = rs.getMetaData();
            int columnsN = meta.getColumnCount();
            System.out.printf("%s:\n##: ", blueBold(SALES_NAME));
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
