package druyaned.corejava.vol2.ch07international;

import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import java.time.DayOfWeek;
import java.time.Month;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class T02DateTimeFormatter extends Topic {
    
    public T02DateTimeFormatter(Chapter chapter) {
        super(chapter, 2);
    }
    
    @Override public String title() {
        return "Topic 02 DateTimeFormatter";
    }
    
    @Override public void run() {
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
