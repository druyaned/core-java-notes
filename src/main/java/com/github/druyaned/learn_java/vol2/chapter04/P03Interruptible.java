package com.github.druyaned.learn_java.vol2.chapter04;

import static com.github.druyaned.ConsoleColors.*;
import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Part 3 of the chapter 4 to practice with SocketChannel which uses InterruptibleChannel.
 * 
 * @author druyaned
 */
public class P03Interruptible {
    
    public static void run() {
        System.out.println("\n" + bold("Running Part 03 Interruptible"));
        
        EventQueue.invokeLater(() -> {
            JFrame frame = new AppFrameP03();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
