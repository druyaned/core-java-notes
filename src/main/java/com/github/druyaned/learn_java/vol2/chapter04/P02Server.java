package com.github.druyaned.learn_java.vol2.chapter04;

import static com.github.druyaned.ConsoleColors.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Part 2 of the chapter 4 to practice with ServerSocket;
 * <p><u>RUNNING</u>: run the app in the Terminal and in the other 2 tabs
 * enter "{@code telnet localhost 8189}".
 * 
 * @author druyaned
 */
public class P02Server {
    
    public static void run() {
        System.out.println("\n" + bold("Running P02 ServerSocket"));
        System.out.println("Open 2 new tabs in the terminal and enter \"telnet localhost 8189\"");
        try (ServerSocket server = new ServerSocket(8189)) {
            int threadCount = 0;
            while (threadCount < 2) {
                Socket socket = server.accept();
                Runnable socketTask = new SocketTaskP02(socket);
                ++threadCount;
                new Thread(socketTask).start();
            }
        } catch (IOException ex) {
            Logger.getLogger(P02Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
