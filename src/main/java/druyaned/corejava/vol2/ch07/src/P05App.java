package druyaned.corejava.vol2.ch07.src;

import druyaned.corejava.vol2.ch07.src.p05.AppFrame;
import static druyaned.ConsoleColors.bold;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

/**
 * Part 5 of the chapter 7 to practice with
 * {@code internationalization} by writing a simple app.
 * 
 * @author druyaned
 */
public class P05App implements Runnable {
    
    @Override public void run() {
        System.out.println("\n" + bold("Running P05 App"));
        EventQueue.invokeLater(() -> {
            AppFrame frame = new AppFrame();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension frameSize = frame.getPreferredSize();
            int x = (int)(screenSize.getWidth() / 2 - frameSize.getWidth() / 2);
            int y = (int)(screenSize.getHeight() / 2 - frameSize.getHeight() / 2);
            frame.setLocation(x, y);
            frame.setVisible(true);
        });
    }
    
}
