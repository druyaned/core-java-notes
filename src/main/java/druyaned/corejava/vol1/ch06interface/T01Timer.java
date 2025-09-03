package druyaned.corejava.vol1.ch06interface;

import static druyaned.ConsoleColors.bold;
import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import java.util.Date;
import javax.swing.Timer;
import javax.swing.JOptionPane;

public class T01Timer extends Topic {
    
    public T01Timer(Chapter chapter) {
        super(chapter, 1);
    }
    
    @Override public String title() {
        return "Topic 01 Timer";
    }
    
    @Override public void run() {
        ActionListener listener = way1(); // or way2, the result is the same
        int delay = 100;
        Timer timer = new Timer(delay, listener);
        long endTime = System.currentTimeMillis() + 2 * delay;
        timer.start();
        while (System.currentTimeMillis() < endTime) {}
        JOptionPane.showMessageDialog(null, "Quit programm?");
        timer.stop();
    }
    
    private ActionListener way1() {
        return new TimePrinter();
    }
    
    private ActionListener way2() {
        return (event) -> {
            System.out.println("At the tone, the time is "
                    + bold(new Date().toString()));
            Toolkit.getDefaultToolkit().beep();
        };
    }
    
}
