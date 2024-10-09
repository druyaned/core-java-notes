package com.github.druyaned.corejava.vol2.ch05.src;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Provides a simple transaction at a special {@code date} and {@code time},
 * of several {@code units} and of some {@code value}.
 * 
 * @author druyaned
 */
public class Transaction implements Comparable<Transaction> {
    
    protected final LocalDate date;
    protected final LocalTime time;
    protected final int units;
    protected final int value;
    
    /**
     * Constructs a new transaction.
     * @param date the date of the transaction
     * @param time the time of the transaction
     * @param units amount of units
     * @param value transaction value (income or expense) of the {@code units}
     */
    public Transaction(LocalDate date, LocalTime time, int units, int value) {
        this.date = date;
        this.time = time;
        this.units = units;
        this.value = value;
    }
    
    /**
     * Returns date of the transaction.
     * @return date of the transaction
     */
    public LocalDate getDate() {
        return date;
    }
    
    /**
     * Returns time of the transaction.
     * @return time of the transaction
     */
    public LocalTime getTime() {
        return time;
    }
    
    /**
     * Returns amount of units.
     * @return amount of units
     */
    public int getUnits() {
        return units;
    }
    
    /**
     * Returns transaction value (income or expense) of the {@code units}.
     * @return transaction value (income or expense) of the {@code units}
     */
    public int getValue() {
        return value;
    }
    
    /**
     * Returns a string of values for the SQL INSERT-command.
     * 
     * <P><i>USAGE</i>:<pre>
     *  String.format("INSERT INTO %s VALUES (%s);", table, tr.getSqlValues());
     * </pre>
     * 
     * @return a string of values for the SQL INSERT-command.
     */
    public String getSqlValues() {
        String dateStr = String.format(
                "%04d-%02d-%02d",
                date.getYear(), date.getMonth().getValue(), date.getDayOfMonth()
        );
        String timeStr = String.format(
                "%02d:%02d:%02d",
                time.getHour(), time.getMinute(), time.getSecond()
        );
        return String.format(
                "'%s', '%s', '%d', '%d'",
                dateStr, timeStr, units, value
        );
    }
    
    /**
     * Returns all fields of the transaction like an array of string values.
     * @return all fields of the transaction like an array of string values
     */
    public String[] getValues() {
        String dateStr = String.format(
                "%04d-%02d-%02d",
                date.getYear(), date.getMonth().getValue(), date.getDayOfMonth()
        );
        String timeStr = String.format(
                "%02d:%02d:%02d",
                time.getHour(), time.getMinute(), time.getSecond()
        );
        String unitsStr = Integer.toString(units);
        String valueStr = Integer.toString(value);
        return new String[] { dateStr, timeStr, unitsStr, valueStr };
    }
    
    /**
     * Compares by {@code date} and then {@code time}.
     * {@inheritDoc}
     */
    @Override public int compareTo(Transaction o) {
        int dateCompare = date.compareTo(o.date);
        if (dateCompare != 0) {
            return dateCompare;
        }
        return time.compareTo(o.time);
    }
    
    @Override public String toString() {
        return String.format(
                "%4d-%02d-%02d %02d:%02d:%02d %d %d",
                date.getYear(), date.getMonth().getValue(), date.getDayOfMonth(),
                time.getHour(), time.getMinute(), time.getSecond(),
                units, value
        );
    }
    
}
