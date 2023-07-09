package com.github.druyaned.learn_java.vol2.chapter04;

import static com.github.druyaned.ConsoleColors.*;
import static com.github.druyaned.learn_java.App.APP_IN;
import static java.time.LocalDateTime.ofEpochSecond;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Part 4 of the chapter 4 to practice with URI, URL and URLConnection.
 * 
 * @author druyaned
 */
public class P04URL {
    
    public static void run() {
        System.out.println("\n" + bold("Running Part 04 URL"));
        
        try {
            final ZoneOffset zoneOffset = OffsetDateTime.now().getOffset();
            System.out.println("zoneOffset: " + zoneOffset);
            
            System.out.println("URL to read (or " + bold("empty line") + "): ");
            String entered = APP_IN.nextLine();
            final String urlName;
            if (entered.isEmpty()) {
                urlName = "https://horstmann.com/";
            } else {
                urlName = entered;
            }
            
            // making a connection
            URL url = new URL(urlName);
            URLConnection connection = url.openConnection();
            connection.connect();
            System.out.println("Connected to " + blueBold(urlName));
            
            // getHeaderFields
            Map<String, List<String>> headers = connection.getHeaderFields();
            for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                String key = entry.getKey();
                List<String> values = entry.getValue();
                System.out.println(" ".repeat(2) + key + ":");
                for (String value : values) {
                    System.out.println(" ".repeat(4) + value);
                }
            }
            
            // times: creation, lastModified
            long creationMillis = connection.getDate();
            long lastModifiedMillis = connection.getLastModified();
            long creationSec = creationMillis / 1000L;
            long lastModifiedSec = lastModifiedMillis / 1000L;
            LocalDateTime creationTime = ofEpochSecond(creationSec, 0, zoneOffset);
            LocalDateTime lastModifiedTime = ofEpochSecond(lastModifiedSec, 0, zoneOffset);
            System.out.println(" ".repeat(2) + "creationTime: " + creationTime);
            System.out.println(" ".repeat(2) + "lastModifiedTime: " + lastModifiedTime);
            
            // content: type, lenght
            String contentType = connection.getContentType();
            int contentLength = connection.getContentLength();
            System.out.println(" ".repeat(2) + "contentType=" + contentType);
            System.out.println(" ".repeat(2) + "contentLength=" + contentLength);
            
            // first N lines
            final int N = 10;
            System.out.println(" ".repeat(2) + "First " + N + " lines (or less):");
            try (Scanner in = new Scanner(connection.getInputStream())) {
                for (int i = 1; in.hasNext() && i <= N; ++i) {
                    String line = in.nextLine();
                    System.out.printf("%s%2d: %s\n", " ".repeat(4), i, line);
                }
            }
            
        } catch (IOException exc) {
            Logger.getLogger(P04URL.class.getName()).log(Level.SEVERE, null, exc);
        }
    }

}
