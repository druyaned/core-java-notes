package com.github.druyaned.learn_java.vol2.chapter07;

import static com.github.druyaned.ConsoleColors.bold;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

/**
 * Part 5 of the chapter 7 to practice with
 * {@code internationalization} by writing a simple app.
 * 
 * @author druyaned
 */
public class P05App {
    
    public static void run() {
        System.out.println("\n" + bold("Running P05 App"));
        
        EventQueue.invokeLater(() -> {
            P05Frame frame = new P05Frame();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension frameSize = frame.getPreferredSize();
            int x = (int)(screenSize.getWidth() / 2 - frameSize.getWidth() / 2);
            int y = (int)(screenSize.getHeight() / 2 - frameSize.getHeight() / 2);
            frame.setLocation(x, y);
            frame.setVisible(true);
        });
    }

}
