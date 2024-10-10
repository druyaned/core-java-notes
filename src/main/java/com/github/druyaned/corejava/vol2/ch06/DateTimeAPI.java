package com.github.druyaned.corejava.vol2.ch06;

import static com.github.druyaned.ConsoleColors.*;
import com.github.druyaned.corejava.Chapter;
import java.nio.file.Path;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.TreeSet;

/**
 * Practical implementation of the Chapter#06: Date-Time API.
 * @author druyaned
 */
public class DateTimeAPI extends Chapter {
    
    /**
     * Creates the Chapter#06: Date-Time API.
     * @param volDataDir the path to the volume's data-directory
     */
    public DateTimeAPI(Path volDataDir) {
        super(volDataDir, 6);
    }
    
    @Override public String getTitle() {
        return "Date-Time API";
    }
    
    @Override public void run() {
        printAvailableZoneIds();
        final Clock clock = Clock.systemDefaultZone();
        final Instant instant = Instant.now();
        final ZoneId zoneId = ZoneId.systemDefault();
        System.out.println(
                blueBold("clock.instant") + ": " + clock.instant()
        );
        System.out.println(
                blueBold("Instant.now") + ":   " + instant
        );
        System.out.println(
                blueBold("LocalDateTime.ofInstant") + ": " +
                LocalDateTime.ofInstant(instant, zoneId)
        );
        final ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, zoneId);
        System.out.println(
                blueBold("ZonedDateTime.ofInstant") + ": " +
                zonedDateTime
        );
        System.out.println(
                blueBold("Duration.between") + ": " +
                Duration.between(instant, Instant.now())
        );
        final DateTimeFormatter formatter = DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.FULL);
        System.out.println(
                blueBold("zonedDateTime.format") + ": " +
                zonedDateTime.format(formatter)
        );
    }
    
    private void printAvailableZoneIds() {
        System.out.println(purpleBold("Available Zone IDs:"));
        int i = 1;
        for (String availableZoneId : new TreeSet<>(ZoneId.getAvailableZoneIds())) {
            System.out.printf(
                    "  %d. %s\n",
                    i++, availableZoneId
            );
        }
    }
    
}
