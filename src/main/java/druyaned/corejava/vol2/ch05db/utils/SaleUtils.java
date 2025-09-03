package druyaned.corejava.vol2.ch05db.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Random;

public class SaleUtils {
    
    /** The parameters of a purchase in a SQL view. */
    public static final String PARAMETERS =
            "Date DATE, Time TIME, Units INT, Income INT, ID INT PRIMARY KEY";
    
    /**
     * Returns names of a sale columns.
     * @return names of a sale columns
     */
    public static String[] getColumns() {
        return new String[] { "Date", "Time", "Units", "Income", "ID" };
    }
    
    /**
     * Generates a special number ({@code N}) of sales.
     * @param N special number of generated sales
     * @param year the year of the sales
     * @param month the month of the sales
     * @return generated sales
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
    
}
