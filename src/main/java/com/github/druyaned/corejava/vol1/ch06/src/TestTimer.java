package com.github.druyaned.corejava.vol1.ch06.src;

import static com.github.druyaned.ConsoleColors.bold;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import java.util.Date;
import javax.swing.Timer;
import javax.swing.JOptionPane;

public class TestTimer {
    
    public static void run() {
        System.out.println("\n" + bold("TestTimer"));
        ActionListener listener = way1(); // or way2; the result is the same
        int delay = 100;
        Timer timer = new Timer(delay, listener);
        long endTime = System.currentTimeMillis() + 2 * delay;
        timer.start();
        while (System.currentTimeMillis() < endTime) {}
        JOptionPane.showMessageDialog(null, "Quit programm?");
        timer.stop();
    }
    
    public static ActionListener way1() {
        return new TimePrinter();
    }
    
    public static ActionListener way2() {
        return (event) -> {
            System.out.println("At the tone, the time is " + bold(new Date().toString()));
            Toolkit.getDefaultToolkit().beep();
        };
    }
    
}

class TimePrinter implements ActionListener {
    
    @Override public void actionPerformed(ActionEvent e) {
        System.out.println("At the tone, the time is " + bold(new Date().toString()));
        Toolkit.getDefaultToolkit().beep();
    }
    
}
