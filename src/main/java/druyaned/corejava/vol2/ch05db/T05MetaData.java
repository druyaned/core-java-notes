package druyaned.corejava.vol2.ch05db;

import static druyaned.ConsoleColors.blueBold;
import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import static druyaned.corejava.vol2.ch05db.DB.PURCHASES_NAME;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class T05MetaData extends Topic {
    
    private final String url, user, pass;
    
    public T05MetaData(Chapter chapter, String url, String user, String pass) {
        super(chapter, 5);
        this.url = url;
        this.user = user;
        this.pass = pass;
    }
    
    @Override public String title() {
        return "Topic 05 MetaData";
    }
    
    @Override public void run() {
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            // DatabaseMetaData
            DatabaseMetaData meta = connection.getMetaData();
            try (ResultSet tables = meta.getTables(null, null, null, new String[] {"TABLE"})) {
                System.out.printf("Tables in \"%s\":", blueBold(meta.getDatabaseProductName()));
                while (tables.next()) {
                    System.out.print(" " + tables.getString(3)); // watch getTables doc
                }
                System.out.println();
                System.out.println("  maxConnections: " + meta.getMaxConnections());
                System.out.println("  maxStatements:  " + meta.getMaxStatements());
            }
            // ResultSetMetaData
            String select = String.format("SELECT * FROM %s;", PURCHASES_NAME);
            try (
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(select);
            ) {
                ResultSetMetaData resMeta = resultSet.getMetaData();
                System.out.println("Command: " + blueBold(select));
                int columnCount = resMeta.getColumnCount();
                System.out.println("  columnCount=" + columnCount);
                System.out.print("  columnLabels:");
                for (int i = 1; i <= columnCount; ++i) {
                    System.out.print(" " + resMeta.getColumnLabel(i));
                }
                System.out.println();
                System.out.print("  columnNames: ");
                for (int i = 1; i <= columnCount; ++i) {
                    System.out.print(" " + resMeta.getColumnName(i));
                }
                System.out.println();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
