package com.github.druyaned.learn_java.vol2.chapter05;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Random;

/**
 * Provides a simple purchase at the special {@code time},
 * of several {@code units} and with some {@code expense}.
 * 
 * @author druyaned
 * @see P02Insertion
 * @see Transaction
 */
public class Purchase extends Transaction {
    
    /** The parameters of a purchase in a SQL view. */
    public static final String PARAMETERS = "Date DATE, Time TIME, Units INT, Expense INT, " +
                                            "Bolt_Supplier VARCHAR(127)";
    
    /**
     * Names of a purchase columns.
     * 
     * @return names of a purchase columns.
     */
    public static String[] getColumns() {
        return new String[] { "Date", "Time", "Units", "Expense", "Bolt_Supplier" };
    }
    
    /**
     * Generates a special number ({@code N}) of purchases.
     * 
     * @param N special number of generated purchases.
     * @param year the year of the purchase.
     * @param month the month of the purchase.
     * @return generated purchase.
     * @see Transaction#generate
     * @see Sale
     */
    public static Purchase[] generate(final int N, int year, int month) {
        Random r = new Random();
        Purchase[] purchases = new Purchase[N];
        String[] boltSuppliers = { "ЮгПромМетиз", "ПЗКИ", "Профкрепеж", "УМЗ" };
        for (int i = 0; i < N; ++i) {
            LocalDate date = LocalDate.of(year, month, r.nextInt(24) + 1);
            LocalTime time = LocalTime.of(r.nextInt(24), r.nextInt(60), r.nextInt(60));
            int units = r.nextInt(10000);
            int value = (r.nextInt(128) + 128) * units;
            String boltSupplier = boltSuppliers[r.nextInt(boltSuppliers.length)];
            purchases[i] = new Purchase(date, time, units, value, boltSupplier);
        }
        Arrays.sort(purchases);
        return purchases;
    }
    
//-Non-static---------------------------------------------------------------------------------------
    
    private final String boltSupplier;
    
    /**
     * Constructs a purchase at the special {@code date}, {@code time},
     * from several {@code units} and with some {@code value} of the {@code units}.
     * 
     * @param date the date of the purchase.
     * @param time the time of the purchase.
     * @param units amount of units.
     * @param value the purchase value (income or expense) of the {@code units}.
     * @param boltSupplier name of the bolt supplier company.
     * @see Sale
     * @see Purchase
     */
    public Purchase(LocalDate date, LocalTime time, int units, int value, String boltSupplier) {
        super(date, time, units, value);
        this.boltSupplier = boltSupplier;
    }
    
//-Getters------------------------------------------------------------------------------------------
    
    /**
     * Returns the name of the bolt supplier company.
     * 
     * @return the name of the bolt supplier company.
     */
    public String getBoltSupplier() { return boltSupplier; }
    
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
        return String.format("'%s', '%s', '%d', '%d', '%s'",
                             aDate, aTime, getUnits(), getValue(), boltSupplier);
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
        return new String[] { aDate, aTime, aUnits, aValue, boltSupplier };
    }
}
