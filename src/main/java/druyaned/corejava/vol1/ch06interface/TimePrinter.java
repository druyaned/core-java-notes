package druyaned.corejava.vol1.ch06interface;

import static druyaned.ConsoleColors.bold;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class TimePrinter implements ActionListener {
    
    @Override public void actionPerformed(ActionEvent e) {
        System.out.println("At the tone, the time is " + bold(new Date().toString()));
        Toolkit.getDefaultToolkit().beep();
    }
    
}
