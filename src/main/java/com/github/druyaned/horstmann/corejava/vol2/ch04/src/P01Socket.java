package com.github.druyaned.horstmann.corejava.vol2.ch04.src;

import static com.github.druyaned.ConsoleColors.*;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Part 1 of the chapter 4 to practice with Socket and InetAddress.
 * @author druyaned
 */
public class P01Socket implements Runnable {
    
    @Override public void run() {
        System.out.println("\n" + bold("Running P01 Socket"));
        final int timeOut = (1 << 12); // 4 sec
        final String hostName = "www.horstmann.com";
        try (Socket socket = new Socket(hostName, 80)) {
            socket.setSoTimeout(timeOut);
            if (socket.isConnected()) {
                System.out.println(blueBold("socket is connected"));
            }
            InetAddress inetAddress = InetAddress.getByName(hostName);
            InetAddress[] addresses = InetAddress.getAllByName(hostName);
            System.out.println("inetAddress=" + inetAddress);
            System.out.print("addresses:\n ");
            for (InetAddress address : addresses) {
                System.out.print(" " + address);
            }
            System.out.println();
            System.out.println("localHost=" + InetAddress.getLocalHost());
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }
    
}
