package com.github.druyaned.learn_java.vol2.chapter05;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Random;

/**
 * Provides a simple transaction (sale or purchase)
 * at the special {@code time}, of several {@code units}.
 * 
 * @author druyaned
 * @see P02Insertion
 */
public class Transaction implements Comparable<Transaction> {
    
    /**
     * Returns transaction as a row of values to show.
     * 
     * @param values column values.
     * @return transaction as a row of values to show.
     * @see Sale
     * @see Purchase
     * @see getValues
     */
    public static String valuesToShow(String[] values) {
        
        // row format
        final int S = 13;
        StringBuilder formatBuilder = new StringBuilder(values.length);
        String columnFormat = "%" + S + "s";
        formatBuilder.append(columnFormat);
        for (int i = 1; i < values.length; ++i) {
            columnFormat = "  %" + S + "s";
            formatBuilder.append(columnFormat);
        }
        String rowFormat = formatBuilder.toString();
        
        return String.format(rowFormat, (Object[]) values);
    }
    
    /**
     * Shows the transaction as a row of values.
     * 
     * @param values column values.
     * @see Sale
     * @see Purchase
     * @see getValues
     */
    public static void showRow(String[] values) {
        
        // row format
        final int S = 13;
        StringBuilder formatBuilder = new StringBuilder(values.length + 1);
        String columnFormat = "%" + S + "s";
        formatBuilder.append(columnFormat);
        for (int i = 1; i < values.length; ++i) {
            columnFormat = "  %" + S + "s";
            formatBuilder.append(columnFormat);
        }
        formatBuilder.append("\n");
        String rowFormat = formatBuilder.toString();
        
        // showing
        System.out.printf(rowFormat, (Object[]) values);
    }
    
    /**
     * Shows the table of transactions.
     * 
     * @param rows array of rows of values to be shown as a table.
     * @param columns names of columns of the table.
     * @param tableName name of the table to be shown.
     * @see Sale
     * @see Purchase
     * @see getValues
     */
    public static void showTable(String tableName, String[] columns, String[][] rows) {
        
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
        System.out.println(tableName + ":");
        System.out.printf(rowFormat, (Object[]) columns);
        for (int i = 0; i < rows.length; ++i) {
            System.out.printf(rowFormat, (Object[]) rows[i]);
        }
    }
    
    /**
     * Generates a special number ({@code N}) of transactions (sales or purchases).
     * 
     * @param N special number of generated transactions.
     * @param year the year of the transactions.
     * @param month the month of the transactions.
     * @return generated transactions.
     * @see Sale
     * @see Purchase
     */
    public static Transaction[] generate(final int N, int year, int month) {
        Random r = new Random();
        Transaction[] transactions = new Transaction[N];
        for (int i = 0; i < N; ++i) {
            LocalDate date = LocalDate.of(year, month, r.nextInt(24) + 1);
            LocalTime time = LocalTime.of(r.nextInt(24), r.nextInt(60), r.nextInt(60));
            int units = r.nextInt(10000);
            int value = (r.nextInt(128) + 128) * units;
            transactions[i] = new Transaction(date, time, units, value);
        }
        Arrays.sort(transactions);
        return transactions;
    }
    
//-Non-static---------------------------------------------------------------------------------------
    
    private final LocalDate date;
    private final LocalTime time;
    private final int units;
    private final int value;
    
    /**
     * Constructs a transaction at the special {@code date}, {@code time},
     * from several {@code units} and with some {@code value} of the {@code units}.
     * 
     * @param date the date of the transaction.
     * @param time the time of the transaction.
     * @param units amount of units.
     * @param value the transaction value (income or expense) of the {@code units}.
     * @see Sale
     * @see Purchase
     */
    public Transaction(LocalDate date, LocalTime time, int units, int value) {
        this.date = date;
        this.time = time;
        this.units = units;
        this.value = value;
    }

//-Getters------------------------------------------------------------------------------------------
    
    /**
     * The date of the transaction.
     * 
     * @return The time of the transaction.
     */
    public LocalDate getDate() { return date; }
    
    /**
     * The time of the transaction.
     * 
     * @return the time of the transaction.
     */
    public LocalTime getTime() { return time; }
    
    /**
     * Amount of units.
     * 
     * @return amount of units.
     */
    public int getUnits() { return units; }

    /**
     * The transaction value (income or expense) of the {@code units}.
     * 
     * @return the transaction value (income or expense) of the {@code units}.
     * @see Sale
     * @see Purchase
     */
    public int getValue() { return value; }
    
//-Methods------------------------------------------------------------------------------------------
    
    /**
     * Returns a string of values for the SQL INSERT-command; <u>USAGE</u>:
     * <pre>String.format("INSERT INTO %s VALUES (%s);", table, tr.getSqlValues());</pre>
     * 
     * @return a string of values for the SQL INSERT-command.
     */
    public String getSqlValues() {
        int year = date.getYear();
        int month = date.getMonth().getValue();
        int day = date.getDayOfMonth();
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
        String aDate = String.format("%04d-%02d-%02d", year, month, day);
        String aTime = String.format("%02d:%02d:%02d", hour, minute, second);
        return String.format("'%s', '%s', '%d', '%d'", aDate, aTime, units, value);
    }
    
    /**
     * Returns all fields of the transaction like an array of string values.
     * 
     * @return all fields of the transaction like an array of string values.
     */
    public String[] getValues() {
        int y = date.getYear();
        int m = date.getMonth().getValue();
        int d = date.getDayOfMonth();
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
        String aDate = String.format("%04d-%02d-%02d", y, m, d);
        String aTime = String.format("%02d:%02d:%02d", hour, minute, second);
        String aUnits = Integer.toString(units);
        String aValue = Integer.toString(value);
        return new String[] { aDate, aTime, aUnits, aValue };
    }
    
    /**
     * Compares by {@code date} and then {@code time}.
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Transaction o) {
        int dateCompare = date.compareTo(o.date);
        if (dateCompare != 0) {
            return dateCompare;
        }
        return time.compareTo(o.time);
    }

    @Override
    public String toString() {
        return String.format("%4d-%02d-%02d %02d:%02d:%02d %d %d",
                             date.getYear(), date.getMonth().getValue(), date.getDayOfMonth(),
                             time.getHour(), time.getMinute(), time.getSecond(),
                             units, value);
    }
}
