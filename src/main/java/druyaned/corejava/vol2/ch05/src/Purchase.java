package druyaned.corejava.vol2.ch05.src;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Provides a simple purchase with an additional state: {@code bolt supplier}.
 * @author druyaned
 */
public class Purchase extends Transaction {
    
    private final String boltSupplier;
    
    /**
     * Constructs a new purchase.
     * @param date the date of the purchase
     * @param time the time of the purchase
     * @param units amount of units
     * @param value expense of the {@code units}
     * @param boltSupplier name of the bolt supplier company
     */
    public Purchase(LocalDate date, LocalTime time, int units, int value, String boltSupplier) {
        super(date, time, units, value);
        this.boltSupplier = boltSupplier;
    }
    
    /**
     * Returns name of the bolt supplier company.
     * @return name of the bolt supplier company
     */
    public String getBoltSupplier() { return boltSupplier; }
    
    @Override public String getSqlValues() {
        String dateStr = String.format(
                "%04d-%02d-%02d",
                date.getYear(), date.getMonth().getValue(), date.getDayOfMonth()
        );
        String timeStr = String.format(
                "%02d:%02d:%02d",
                time.getHour(), time.getMinute(), time.getSecond()
        );
        return String.format(
                "'%s', '%s', '%d', '%d', '%s'",
                dateStr, timeStr, getUnits(), getValue(), boltSupplier
        );
    }
    
    @Override public String[] getValues() {
        String dateStr = String.format(
                "%04d-%02d-%02d",
                date.getYear(), date.getMonth().getValue(), date.getDayOfMonth()
        );
        String timeStr = String.format(
                "%02d:%02d:%02d",
                time.getHour(), time.getMinute(), time.getSecond()
        );
        String unitsStr = Integer.toString(getUnits());
        String valueStr = Integer.toString(getValue());
        return new String[] { dateStr, timeStr, unitsStr, valueStr, boltSupplier };
    }
    
}
