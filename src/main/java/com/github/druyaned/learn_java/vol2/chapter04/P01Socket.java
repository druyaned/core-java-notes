package com.github.druyaned.learn_java.vol2.chapter04;

import static com.github.druyaned.ConsoleColors.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Part 1 of the chapter 4 to practice with Socket and InetAddress.
 * 
 * @author druyaned
 */
public class P01Socket {
    
    public static void run() {
        System.out.println("\n" + bold("Running P01 Socket"));
        final int timeOut = (1 << 12); // 4 sec
        try (Socket socket = new Socket("www.horstmann.com", 80)) {
            socket.setSoTimeout(timeOut);
            if (socket.isConnected()) {
                System.out.println(blueBold("socket is connected"));
            }
            final String hostName = "www.horstmann.com";
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
            Logger.getLogger(P01Socket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
