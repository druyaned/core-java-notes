package druyaned.corejava.vol2.ch05db.utils;

import static druyaned.ConsoleColors.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Random;

public class TransactionUtils {
    
    private static final int W = 13; // width for the value to print
    
    /**
     * Returns transaction as a row of values to show.
     * @param values some values
     * @return transaction as a row of values to show
     */
    public static String rowToPrint(String[] values) {
        // row format
        StringBuilder formatBuilder = new StringBuilder(values.length);
        String columnFormat = "%" + W + "s";
        formatBuilder.append(columnFormat);
        for (int i = 1; i < values.length; ++i) {
            columnFormat = "  %" + W + "s";
            formatBuilder.append(columnFormat);
        }
        String rowFormat = formatBuilder.toString();
        return String.format(rowFormat, (Object[])values);
    }
    
    /**
     * Prints the transaction as a row of values
     * into the {@link System#out output stream}.
     * 
     * @param values some values
     */
    public static void printRow(String[] values) {
        // row format
        StringBuilder formatBuilder = new StringBuilder(values.length + 1);
        String columnFormat = "%" + W + "s";
        formatBuilder.append(columnFormat);
        for (int i = 1; i < values.length; ++i) {
            columnFormat = "  %" + W + "s";
            formatBuilder.append(columnFormat);
        }
        formatBuilder.append("\n");
        String rowFormat = formatBuilder.toString();
        // showing
        System.out.printf(rowFormat, (Object[]) values);
    }
    
    /**
     * Prints the table of transactions
     * into the {@link System#out output stream}.
     * 
     * @param tableName name of the table to be shown
     * @param columns names of columns of the table
     * @param rows array of rows of values to be shown as a table
     */
    public static void printTable(String tableName, String[] columns, String[][] rows) {
        // row format
        final int S = 13;
        StringBuilder formatBuilder = new StringBuilder(columns.length + 1);
        String columnFormat = "%" + S + "s";
        formatBuilder.append(columnFormat);
        for (int i = 1; i < columns.length; ++i) {
            columnFormat = "  %" + S + "s";
            formatBuilder.append(columnFormat);
        }
        formatBuilder.append("\n");
        String rowFormat = formatBuilder.toString();
        // showing
        System.out.println(purpleBold(tableName) + ":");
        System.out.printf(rowFormat, (Object[]) columns);
        for (int i = 0; i < rows.length; ++i) {
            System.out.printf(rowFormat, (Object[]) rows[i]);
        }
    }
    
    /**
     * Generates a special number ({@code N}) of transactions.
     * @param N special number of generated transactions
     * @param year the year of the transactions
     * @param month the month of the transactions
     * @return generated transactions
     */
    public static Transaction[] generate(final int N, int year, int month) {
        Random gen = new Random();
        Transaction[] transactions = new Transaction[N];
        for (int i = 0; i < N; ++i) {
            LocalDate date = LocalDate.of(
                    year, month, gen.nextInt(24) + 1
            );
            LocalTime time = LocalTime.of(
                    gen.nextInt(24), gen.nextInt(60), gen.nextInt(60)
            );
            int units = gen.nextInt(10000);
            int value = (gen.nextInt(128) + 128) * units;
            transactions[i] = new Transaction(date, time, units, value);
        }
        Arrays.sort(transactions);
        return transactions;
    }
    
}
