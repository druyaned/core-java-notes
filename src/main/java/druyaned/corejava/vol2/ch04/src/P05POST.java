package druyaned.corejava.vol2.ch04.src;

import static druyaned.ConsoleColors.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Part 5 of the chapter 4 to practice in forming a query by the POST command.
 * @author druyaned
 */
public class P05POST implements Runnable {
    
    private final Path dataDirPath;
    private final Path responseFilePath;
    
    public P05POST(Path chapterDataDir) {
        dataDirPath = chapterDataDir.resolve("p05");
        responseFilePath = dataDirPath.resolve("index.html");
        try {
            if (!Files.exists(dataDirPath)) {
                Files.createDirectories(dataDirPath);
                System.out.println("Directory " + blueBold(dataDirPath.toString())
                        + " was " + greenBold("successfully") + " created!");
            }
            if (!Files.exists(responseFilePath)) {
                Files.createFile(responseFilePath);
                System.out.println("File " + blueBold(responseFilePath.toString())
                        + " was " + greenBold("successfully") + " created!");
            }
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
    }
    
    @Override public void run() {
        // https://www.youtube.com/results?search_query=Yo - GET command example
        System.out.println("\n" + bold("Running Part 05 POST"));
        try {
            URL url = new URI("https://tools.usps.com/go/ZipLookupAction.action").toURL();
            String userAgent = "HTTPie/0.9.2";
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
            try (BufferedWriter writer = Files.newBufferedWriter(responseFilePath)) {
                writer.write("<!DOCTYPE html>\n");
                for (String line : response)
                    writer.write(line + "\n");
            }
            System.out.println("Respone was written into "
                    + blueBold(responseFilePath.toString()));
        } catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    // if redirectCount == -1 => auto-redirect
    // @param redirectCount - at the first invoke it's an limiting amount of redirections
    private static List<String> post(
            URL url,
            String userAgent,
            int redirectCount,
            Map<String, String> properties
    ) throws IOException, URISyntaxException
    {
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        if (userAgent != null)
            connection.setRequestProperty("User-Agent", userAgent);
        if (redirectCount >= 0)
            connection.setInstanceFollowRedirects(false);
        try (PrintWriter writer = new PrintWriter(connection.getOutputStream())) {
            if (!properties.isEmpty()) {
                boolean first = true;
                for (Map.Entry<String, String> entry : properties.entrySet()) {
                    if (first)
                        first = false;
                    else
                        writer.print('&');
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
            if (
                    responseCode == HttpURLConnection.HTTP_MOVED_PERM ||
                    responseCode == HttpURLConnection.HTTP_MOVED_TEMP ||
                    responseCode == HttpURLConnection.HTTP_SEE_OTHER
            ) {
                String location = connection.getHeaderField("Location");
                if (location != null) {
                    connection.disconnect();
                    return post(
                            new URI(location).toURL(),
                            userAgent,
                            redirectCount - 1,
                            properties
                    );
                }
            } // else redirectCount != 0 and there will be no more recursion
        } else if (redirectCount == 0) // if more than initial redirectCount
            throw new IOException("too many redirections");
        String encoding = connection.getContentEncoding();
        if (encoding == null)
            encoding = "UTF-8";
        List<String> response = new ArrayList<>();
        try (Scanner scanner = new Scanner(connection.getInputStream(), encoding)) {
            while (scanner.hasNext())
                response.add(scanner.nextLine());
        } catch (IOException exc) {
            InputStream errIn = connection.getErrorStream();
            if (errIn == null)
                throw exc;
            try (Scanner scanner = new Scanner(errIn, encoding)) {
                while (scanner.hasNext())
                    response.add(scanner.nextLine());
            }
        }
        return response;
    }
    
}
