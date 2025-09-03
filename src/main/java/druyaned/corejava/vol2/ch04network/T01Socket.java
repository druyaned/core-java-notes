package druyaned.corejava.vol2.ch04network;

import static druyaned.ConsoleColors.blueBold;
import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.InetAddress;
import java.net.Socket;

public class T01Socket extends Topic {
    
    public T01Socket(Chapter chapter) {
        super(chapter, 1);
    }
    
    @Override public String title() {
        return "Topic 01 Socket";
    }
    
    @Override public void run() {
        final int timeOut = (1 << 12); // 4 sec
        final String hostName = "www.horstmann.com";
        try (Socket socket = new Socket(hostName, 80)) {
            socket.setSoTimeout(timeOut);
            if (socket.isConnected())
                System.out.println(blueBold("Socket is connected"));
            else
                System.out.println(blueBold("Socket is NOT connected"));
            InetAddress inetAddress = InetAddress.getByName(hostName);
            InetAddress[] addresses = InetAddress.getAllByName(hostName);
            System.out.println("inetAddress=" + inetAddress);
            System.out.print("addresses:");
            for (InetAddress address : addresses)
                System.out.print(" " + address);
            System.out.println();
            System.out.println("localHost=" + InetAddress.getLocalHost());
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }
    
}
