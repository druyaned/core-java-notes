package druyaned.corejava.vol2.ch04network;

import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

public class T07HttpGET extends Topic {
    
    public T07HttpGET(Chapter chapter) {
        super(chapter, 7);
    }
    
    @Override public String title() {
        return "Topic 07 HttpGET";
    }
    
    @Override public void run() {
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .proxy(ProxySelector.getDefault())
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(2L))
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://example.com"))
                .GET()
                .build();
        HttpResponse<String> response;
        try {
            response = client.send(request, BodyHandlers.ofString());
        } catch (IOException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        System.out.println("status code:");
        System.out.println(response.statusCode());
        System.out.println("body:");
        System.out.println(response.body());
    }
    
}
