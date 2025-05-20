package druyaned.corejava.vol2.ch04.src;

import druyaned.corejava.vol2.ch04.src.p03.AppFrame;
import static druyaned.ConsoleColors.*;
import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Part 3 of the chapter 4 to practice with SocketChannel which uses InterruptibleChannel.
 * @author druyaned
 */
public class P03Interruptible implements Runnable {
    
    @Override public void run() {
        System.out.println("\n" + bold("Running Part 03 Interruptible"));
        EventQueue.invokeLater(() -> {
            JFrame frame = new AppFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
    
}
