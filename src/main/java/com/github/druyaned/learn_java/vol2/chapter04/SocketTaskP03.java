package com.github.druyaned.learn_java.vol2.chapter04;

import java.awt.EventQueue;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import javax.swing.JTextArea;

/**
 * Writes letters as bytes ({@link #LETTER_SIZE}) in the socket.
 * 
 * @author druyaned
 */
public class SocketTaskP03 implements Runnable {
    
    private static final int A = (int)'а';
    private static final int YA = (int)'я';
    private final Socket socket;
    private final JTextArea area;
    
    /** The delay between writing each letter. */
    public static final int DELAY = (1 << 9); // 512 millis
    
    /** Size of a letter in <u>bytes</u>. */
    public static final int LETTER_SIZE = 4;
    
    /** An amount of written letters. */
    public static final int LETTERS = YA - A + 1;
    
    /**
     * Constructs a task to write letters as bytes in the socket.
     * 
     * @param socket a socket to write in.
     * @param area an area to print an error message.
     * @see #LETTER_SIZE
     * @see #LETTERS
     */
    public SocketTaskP03(Socket socket, JTextArea area) {
        this.socket = socket;
        this.area = area;
    }

    @Override
    public void run() {
        try {
            OutputStream out = socket.getOutputStream();
            for (int letter = A; !socket.isClosed() && letter <= YA; ++letter) {
                byte[] bytes = ByteBuffer.allocate(LETTER_SIZE).putInt(letter).array();
                Thread.sleep(DELAY);
                out.write(bytes, 0, 4);
                out.flush();
            }
        } catch (InterruptedException | IOException exc) {
            EventQueue.invokeLater(() -> area.append(exc + "\n"));
        }
    }

}
