package com.github.druyaned.learn_java.vol2.chapter05;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Random;

/**
 * Provides a simple sale at the special {@code time},
 * of several {@code units} and with some {@code income}.
 * 
 * @author druyaned
 * @see P02Insertion
 * @see Transaction
 */
public class Sale extends Transaction {
    
    /** The parameters of a purchase in a SQL view. */
    public static final String PARAMETERS = "Date DATE, Time TIME, Units INT, Income INT, " +
                                            "ID INT PRIMARY KEY";
    
    /**
     * Names of a sale columns.
     * 
     * @return names of a sale columns.
     */
    public static String[] getColumns() {
        return new String[] { "Date", "Time", "Units", "Income", "ID" };
    }
    
    /**
     * Generates a special number ({@code N}) of sales.
     * 
     * @param N special number of generated sales.
     * @param year the year of the sales.
     * @param month the month of the sales.
     * @return generated sales.
     * @see Sale
     * @see Purchase
     */
    public static Sale[] generate(final int N, int year, int month) {
        Random r = new Random();
        Sale[] sales = new Sale[N];
        for (int i = 0; i < N; ++i) {
            LocalDate date = LocalDate.of(year, month, r.nextInt(24) + 1);
            LocalTime time = LocalTime.of(r.nextInt(24), r.nextInt(60), r.nextInt(60));
            int units = r.nextInt(10000);
            int value = (r.nextInt(128) + 128) * units;
            sales[i] = new Sale(date, time, units, value, i + 1);
        }
        Arrays.sort(sales);
        return sales;
    }
    
//-Non-static---------------------------------------------------------------------------------------
    
    private final int id;
    
    /**
     * Constructs a sale at the special {@code date}, {@code time},
     * from several {@code units} and with some {@code value} of the {@code units}.
     * 
     * @param date the date of the sale.
     * @param time the time of the sale.
     * @param units amount of units.
     * @param value the sale value (income or expense) of the {@code units}.
     * @param id and ID of this sale.
     * @see Sale
     * @see Purchase
     */
    public Sale(LocalDate date, LocalTime time, int units, int value, int id) {
        super(date, time, units, value);
        this.id = id;
    }
    
//-Methods------------------------------------------------------------------------------------------
    
    /** {@inheritDoc} */
    @Override
    public String getSqlValues() {
        int year = getDate().getYear();
        int month = getDate().getMonth().getValue();
        int day = getDate().getDayOfMonth();
        int hour = getTime().getHour();
        int minute = getTime().getMinute();
        int second = getTime().getSecond();
        String aDate = String.format("%04d-%02d-%02d", year, month, day);
        String aTime = String.format("%02d:%02d:%02d", hour, minute, second);
        return String.format("'%s', '%s', '%d', '%d', '%d'",
                             aDate, aTime, getUnits(), getValue(), id);
    }
    
    /** {@inheritDoc} */
    @Override
    public String[] getValues() {
        int y = getDate().getYear();
        int m = getDate().getMonth().getValue();
        int d = getDate().getDayOfMonth();
        int hour = getTime().getHour();
        int minute = getTime().getMinute();
        int second = getTime().getSecond();
        String aDate = String.format("%04d-%02d-%02d", y, m, d);
        String aTime = String.format("%02d:%02d:%02d", hour, minute, second);
        String aUnits = Integer.toString(getUnits());
        String aValue = Integer.toString(getValue());
        String anID = Integer.toString(id);
        return new String[] { aDate, aTime, aUnits, aValue, anID };
    }

}
