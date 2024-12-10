package druyaned.corejava.vol2.ch05.src;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Provides a simple sale with an additional state: {@code ID}.
 * @author druyaned
 */
public class Sale extends Transaction {
    
    private final int id;
    
    /**
     * Constructs a new sale.
     * @param date the date of the sale
     * @param time the time of the sale
     * @param units amount of units
     * @param value income of the {@code units}
     * @param id a unique ID of this sale
     */
    public Sale(LocalDate date, LocalTime time, int units, int value, int id) {
        super(date, time, units, value);
        this.id = id;
    }
    
    /**
     * Returns ID of the sale.
     * @return ID of the sale
     */
    public int getId() {
        return id;
    }
    
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
                "'%s', '%s', '%d', '%d', '%d'",
                dateStr, timeStr, units, value, id
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
        String unitsStr = Integer.toString(units);
        String valueStr = Integer.toString(value);
        String idStr = Integer.toString(id);
        return new String[] { dateStr, timeStr, unitsStr, valueStr, idStr };
    }
    
}
