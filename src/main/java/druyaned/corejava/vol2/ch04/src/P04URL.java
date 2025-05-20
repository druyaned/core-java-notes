package druyaned.corejava.vol2.ch04.src;

import static druyaned.ConsoleColors.*;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

/**
 * Part 4 of the chapter 4 to practice with URI, URL and URLConnection.
 * @author druyaned
 */
public class P04URL implements Runnable {
    
    @Override public void run() {
        System.out.println("\n" + bold("Running Part 04 URL"));
        final ZoneOffset zoneOffset = OffsetDateTime.now().getOffset();
        System.out.println("zoneOffset: " + zoneOffset);
        String[] urlNames = {
            "https://horstmann.com/",
            "https://yandex.ru/pogoda/"
        };
        try {
            for (String urlName : urlNames) {
                actions(zoneOffset, urlName);
            }
        } catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    private void actions(ZoneOffset zoneOffset, String urlName)
            throws URISyntaxException, IOException
    {
        // making a connection
        URI uri = new URI(urlName);
        System.out.println("URI: " + uri);
        URL url = uri.toURL();
        System.out.println("URL: " + url);
        URLConnection connection = url.openConnection();
        connection.connect();
        System.out.println("Connected to " + blueBold(url.toString()));
        // getHeaderFields
        connection.getHeaderFields().forEach((key, values) -> {
            System.out.println("  " + key + ":");
            for (String value : values)
                System.out.println("    " + value);
        });
        // times: creation, lastModified
        long creationMillis = connection.getDate();
        long lastModifiedMillis = connection.getLastModified();
        long creationSec = creationMillis / 1000L;
        long lastModifiedSec = lastModifiedMillis / 1000L;
        LocalDateTime creationTime = LocalDateTime.ofEpochSecond(
                creationSec, 0, zoneOffset
        );
        LocalDateTime lastModifiedTime = LocalDateTime.ofEpochSecond(
                lastModifiedSec, 0, zoneOffset
        );
        System.out.println("  creationTime: " + creationTime);
        System.out.println("  lastModifiedTime: " + lastModifiedTime);
        // content: type, lenght
        String contentType = connection.getContentType();
        int contentLength = connection.getContentLength();
        System.out.println("  contentType=" + contentType);
        System.out.println("  contentLength=" + contentLength);
        // first LINE_COUNT lines
        final int LINE_COUNT = 10;
        final int MAX_LEN = 92;
        System.out.println("  First " + LINE_COUNT + " lines (or less):");
        try (Scanner cin = new Scanner(connection.getInputStream())) {
            for (int i = 1; cin.hasNextLine() && i <= LINE_COUNT; ++i) {
                String line = cin.nextLine();
                if (line.length() > MAX_LEN)
                    System.out.printf("%s%2d: %s...\n", "    ", i, line.substring(0, 92));
                else
                    System.out.printf("%s%2d: %s\n", "    ", i, line);
            }
        }
    }
    
}
