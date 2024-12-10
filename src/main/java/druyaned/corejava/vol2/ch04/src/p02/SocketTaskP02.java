package druyaned.corejava.vol2.ch04.src.p02;

import static druyaned.ConsoleColors.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.net.Socket;

/**
 * Provides a primitive task for a thread of a server.
 * @author druyaned
 * @see P02Server
 */
public class SocketTaskP02 implements Runnable {
    
    private final Socket socket;
    
    /**
     * Constructs a primitive task for a thread of a server.
     * @param socket a socket to handle
     */
    public SocketTaskP02(Socket socket) {
        this.socket = socket;
    }
    
    @Override public void run() {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        socket.getInputStream(), "UTF-8"
                ));
                PrintWriter writer = new PrintWriter(
                        new OutputStreamWriter(socket.getOutputStream(), "UTF-8"),
                        true
                );
        ) {
            String message = "Enter an " + bold("empty line") + " to quit\n: ";
            writer.printf(message); // print-method is not auto-flushed
            for (
                    String line = reader.readLine();
                    !line.isEmpty();
                    line = reader.readLine()
            ) {
                writer.println(blueBold("To upper case") + ": " + line.toUpperCase());
                writer.printf(message); // print-method is not auto-flushed
            }
            socket.shutdownInput(); // half-closed socket
            writer.println(blueBold("An input of the socket is closed") + ".");
       } catch (IOException ex) {
            throw new UncheckedIOException(ex);
       }
    }

}
