package com.github.druyaned.learn_java.vol2.chapter04;

import static com.github.druyaned.ConsoleColors.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Part 5 of the chapter 4 to practice in forming a query by the POST command.
 * 
 * @author druyaned
 */
public class P05POST {
    
    public static void run() {
        // https://www.youtube.com/results?search_query=Yo - GET command example
        System.out.println("\n" + bold("Running Part 05 POST"));
        
        try {
            URL url = new URL("https://tools.usps.com/go/ZipLookupAction.action");
            String userAgent = "HTTPie/0.9.2"; // User-Agent
            final int redirectCount = 10;
            Map<String, String> properties = new HashMap<>();
            properties.put("tCompany", "");
            properties.put("tAddress", "1 Market Street");
            properties.put("tApt", "");
            properties.put("tCity", "San Francisco");
            properties.put("sState", "CA");
            
            CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
            List<String> response = post(url, userAgent, redirectCount, properties);
            System.out.println("url: " + url);
            System.out.println(blueBold("Response") + ":");
            int digits = digitCount(response.size());
            for (int i = 0; i < response.size(); ++i) {
                String lineIndex = String.format("%" + digits + "d", i + 1);
                System.out.println(purpleBold(lineIndex) + ": " + response.get(i));
            }
            
        } catch (IOException exc) {
            Logger.getLogger(P05POST.class.getName()).log(Level.SEVERE, null, exc);
        }
    }
    
    // if redirectCount == -1 => auto-redirect
    // @param redirectCount - at the first invoke it's an limiting amount of redirections.
    private static List<String> post(URL url,
                            String userAgent,
                            int redirectCount,
                            Map<String, String> properties) throws IOException {
        
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        
        if (userAgent != null) {
            connection.setRequestProperty("User-Agent", userAgent);
        }
        if (redirectCount >= 0) {
            connection.setInstanceFollowRedirects(false);
        }
        
        try (PrintWriter writer = new PrintWriter(connection.getOutputStream())) {
            if (!properties.isEmpty()) {
                boolean first = true;
                for (Map.Entry<String, String> entry : properties.entrySet()) {
                    if (first) {
                        first = false;
                    } else {
                        writer.print('&');
                    }
                    String key = entry.getKey();
                    String value = entry.getValue();
                    writer.print(URLEncoder.encode(key, StandardCharsets.UTF_8));
                    writer.print('=');
                    writer.print(URLEncoder.encode(value, StandardCharsets.UTF_8));
                }
            }
        }
        
        if (redirectCount > 0) {
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_MOVED_PERM ||
                responseCode == HttpURLConnection.HTTP_MOVED_TEMP ||
                responseCode == HttpURLConnection.HTTP_SEE_OTHER) {
                
                String location = connection.getHeaderField("Location");
                if (location != null) {
                    connection.disconnect();
                    return post(new URL(location), userAgent, redirectCount - 1, properties);
                }
            } // else redirectCount != 0 and there will be no morerecursion
        } else if (redirectCount == 0) { // if more than initial redirectCount
            throw new IOException("too many redirections");
        }
        
        String encoding = connection.getContentEncoding();
        if (encoding == null) {
            encoding = "UTF-8";
        }
        List<String> response = new ArrayList<>();
        try (Scanner scanner = new Scanner(connection.getInputStream(), encoding)) {
            while (scanner.hasNext()) {
                response.add(scanner.nextLine());
            }
        } catch (IOException exc) {
            InputStream errIn = connection.getErrorStream();
            if (errIn == null) {
                throw exc;
            }
            try (Scanner scanner = new Scanner(errIn, encoding)) {
                while (scanner.hasNext()) {
                    response.add(scanner.nextLine());
                }
            }
        }
        
        return response;
    }
    
    private static int digitCount(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("the requirement must be met: number >= 0");
        }
        int digits = 1;
        while ((number /= 10) > 0) {
            ++digits;
        }
        return digits;
    }

}
