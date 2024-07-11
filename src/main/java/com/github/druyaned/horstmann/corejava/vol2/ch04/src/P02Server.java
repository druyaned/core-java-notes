package com.github.druyaned.horstmann.corejava.vol2.ch04.src;

import com.github.druyaned.horstmann.corejava.vol2.ch04.src.p02.SocketTaskP02;
import static com.github.druyaned.ConsoleColors.*;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Part 2 of the chapter 4 to practice with ServerSocket;
 * 
 * <P><u>RUNNING</u>: run the app in the Terminal and in the other 2 tabs
 * enter "{@code telnet localhost 8189}".
 * 
 * @author druyaned
 */
public class P02Server implements Runnable {
    
    @Override public void run() {
        System.out.println("\n" + bold("Running P02 ServerSocket"));
        System.out.println(
                "Open 2 new tabs in the terminal and enter " +
                "\"telnet localhost 8189\""
        );
        try (ServerSocket server = new ServerSocket(8189)) {
            int threadCount = 0;
            while (threadCount < 2) {
                Socket socket = server.accept();
                Runnable socketTask = new SocketTaskP02(socket);
                threadCount++;
                new Thread(socketTask).start();
            }
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }
    
}
