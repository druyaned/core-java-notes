package com.github.druyaned.learn_java.vol2.chapter04;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JTextArea;

/**
 * The server app to practice with an interruptible and a blocking work of sockets.
 * 
 * @author druyaned
 */
public class ServerP03 {
    private static final int TIMOUT = (1 << 13); // 8.192 sec
    
    /**
     * Launches the server app <u>in the new thread</u>
     * to practice with an interruptible and a blocking work of sockets.
     * 
     * @param interruptArea an area to print an error message for an interruptible thread.
     * @param blockArea an area to print an error message for an blocking thread.
     */
    public static void run(JTextArea interruptArea, JTextArea blockArea) {
        new Thread(() -> {
            try (ServerSocket server = new ServerSocket(8189)) {
                final Socket socket1 = server.accept();
                final Socket socket2 = server.accept();
                socket1.setSoTimeout(TIMOUT);
                socket2.setSoTimeout(TIMOUT);
                new Thread(new SocketTaskP03(socket1, interruptArea)).start();
                new Thread(new SocketTaskP03(socket2, blockArea)).start();
            } catch (IOException exc) {
                throw new UncheckedIOException(exc);
            }
        }).start();
    }
}
