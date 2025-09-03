package druyaned.corejava.vol2.ch04network;

import druyaned.corejava.vol2.ch04network.t02server.SocketTask;
import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Run the app in the Terminal and in the other 2 tabs
 * <code>telnet localhost 8189</code>.
 * 
 * @author druyaned
 */
public class T02Server extends Topic {
    
    public T02Server(Chapter chapter) {
        super(chapter, 2);
    }
    
    @Override public String title() {
        return "Topic 02 Server";
    }
    
    @Override public void run() {
        System.out.println(
                "Open 2 new tabs in the terminal and enter " +
                "\"telnet localhost 8189\""
        );
        try (ServerSocket server = new ServerSocket(8189)) {
            for (int threadCount = 0; threadCount < 2; threadCount++) {
                Socket socket = server.accept();
                Runnable socketTask = new SocketTask(socket);
                new Thread(socketTask).start();
            }
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }
    
}
