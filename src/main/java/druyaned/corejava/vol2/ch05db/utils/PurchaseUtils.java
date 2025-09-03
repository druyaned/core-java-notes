package druyaned.corejava.vol2.ch05db.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Random;

public class PurchaseUtils {
    
    /** The parameters of a purchase in a SQL view. */
    public static final String PARAMETERS =
            "Date DATE, Time TIME, Units INT, Expense INT, Bolt_Supplier VARCHAR(127)";
    
    /**
     * Returns names of purchase columns.
     * @return names of purchase columns
     */
    public static String[] getColumns() {
        return new String[] { "Date", "Time", "Units", "Expense", "Bolt_Supplier" };
    }
    
    /**
     * Generates a special number ({@code N}) of purchases.
     * @param N special number of generated purchases
     * @param year the year of the purchase
     * @param month the month of the purchase
     * @return generated purchases
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
    
}
