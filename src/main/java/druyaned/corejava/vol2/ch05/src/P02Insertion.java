package druyaned.corejava.vol2.ch05.src;

import static druyaned.ConsoleColors.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

/**
 * Part 2 of the chapter 5 to practice with JDBC of H2-DataBase and SQL insertion.
 * @author druyaned
 */
public class P02Insertion implements Runnable {
    
    public static final int YEAR = 2016;
    public static final int MONTH = 8; // transactions_2016_8
    public static final String DB_NAME = "transactions";
    public static final String SALES_NAME = "sales";
    public static final String PURCHASES_NAME = "purchases";
    
    private final String url, user, pass;
    
    public P02Insertion(String url, String user, String pass) {
        this.url = url;
        this.user = user;
        this.pass = pass;
    }
    
    @Override public void run() {
        System.out.println("\n" + bold("Running P02 Insertion"));
        // data
        final int N = 16;
        Sale[] sales = SaleUtils.generate(N, YEAR, MONTH);
        Purchase[] purchases = PurchaseUtils.generate(N, YEAR, MONTH);
        // commands
        final String[] dropCommands = generateDrop();
        final String[] createCommands = generateCreate();
        final String[] insertCommands = generateInsert(N, sales, purchases);
        String selectSales = String.format("SELECT * FROM %s;", SALES_NAME);
        String selectPurchases = String.format("SELECT * FROM %s;", PURCHASES_NAME);
        // connection
        try (
                Connection connection = DriverManager.getConnection(url, user, pass);
                Statement statement = connection.createStatement();
        ) {
            // executing drop-commands
            System.out.println("Executing drop-commands...");
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
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        // connect to transactions to execute remaining commands
        try (
                Connection connection = DriverManager.getConnection(url, user, pass);
                Statement statement = connection.createStatement();
        ) {
            // executing create-, insert-, and select-commands
            System.out.println("Executing create-commands...");
            for (int i = 0; i < createCommands.length; ++i) {
                statement.executeUpdate(createCommands[i]);
            }
            System.out.println("Executing insert-commands...");
            for (int i = 0; i < insertCommands.length; ++i) {
                statement.executeUpdate(insertCommands[i]);
            }
            System.out.println("Executing select-commands...");
            // select-commands and showing tables
            String[] saleColumns = SaleUtils.getColumns();
            String[] purchaseColumns = PurchaseUtils.getColumns();
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
            TransactionUtils.printTable(
                    SALES_NAME, SaleUtils.getColumns(), rowsOfSales
            );
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
            TransactionUtils.printTable(
                    PURCHASES_NAME, PurchaseUtils.getColumns(), rowsOfPurchases
            );
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    private static String[] generateDrop() {
        return new String[] {
            String.format("DROP DATABASE %s;", DB_NAME),
            String.format("DROP TABLE %s;", SALES_NAME), // like a mistake by inattention
            String.format("DROP TABLE %s;", PURCHASES_NAME), // like a mistake by inattention
            String.format("CREATE DATABASE %s;", DB_NAME) // and this is intentional
        };
    }
    
    private static String[] generateCreate() {
        return new String[] { // other commands will be executed in "cashflows" DB
            String.format(
                    "CREATE TABLE %s (%s);",
                    SALES_NAME, SaleUtils.PARAMETERS
            ),
            String.format(
                    "CREATE TABLE %s (%s);",
                    PURCHASES_NAME, PurchaseUtils.PARAMETERS
            )
        };
    }
    
    private static String [] generateInsert(
            int N, Transaction[] sales, Transaction[] purchases
    ) {
        String [] insertCommands = new String[N * 2]; // insertion into 2 tables
        for (int i = 0; i < N; ++i) { // sales
            insertCommands[i] = String.format(
                    "INSERT INTO %s VALUES (%s);",
                    SALES_NAME, sales[i].getSqlValues()
            );
        }
        for (int i = N; i < N * 2; ++i) { // purchases
            insertCommands[i] = String.format(
                    "INSERT INTO %s VALUES (%s);",
                    PURCHASES_NAME, purchases[i - N].getSqlValues()
            );
        }
        return insertCommands;
    }
    
}
