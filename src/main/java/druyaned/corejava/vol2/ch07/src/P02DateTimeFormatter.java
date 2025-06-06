package druyaned.corejava.vol2.ch07.src;

import static druyaned.ConsoleColors.bold;
import java.time.DayOfWeek;
import java.time.Month;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.Locale;

/**
 * Part 2 of the chapter 7 to practice with {@code DateTimeFormatter}.
 * @author druyaned
 */
public class P02DateTimeFormatter implements Runnable {
    
    @Override public void run() {
        System.out.println("\n" + bold("Running P02 DateTimeFormatter"));
        ZonedDateTime t = ZonedDateTime.now();
        Locale enGb = Locale.forLanguageTag("en-GB");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.FULL)
                .withLocale(enGb);
        DateTimeFormatter dateFormatter = DateTimeFormatter
                .ofLocalizedDate(FormatStyle.LONG)
                .withLocale(enGb);
        DateTimeFormatter timeFormatter = DateTimeFormatter
                .ofLocalizedTime(FormatStyle.SHORT)
                .withLocale(enGb);
        System.out.println("Locale: " + enGb + " [" + enGb.getDisplayName() + "]");
        String dateTimeText = dateTimeFormatter.format(t);
        System.out.println("DateTime: " + dateTimeText);
        System.out.println("Date: " + dateFormatter.format(t));
        System.out.println("Time: " + timeFormatter.format(t));
        ZonedDateTime parsed = ZonedDateTime.parse(dateTimeText, dateTimeFormatter);
        System.out.println("Parsed: " + parsed);
        DayOfWeek firstDayOfWeek = WeekFields.of(enGb).getFirstDayOfWeek();
        System.out.println("First day of week: " + firstDayOfWeek);
        System.out.print("Months:");
        for (Month m : Month.values()) {
            System.out.print(" " + m.getDisplayName(TextStyle.SHORT, enGb));
        }
        System.out.println();
    }
    
}
