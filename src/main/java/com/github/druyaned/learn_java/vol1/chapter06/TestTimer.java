package com.github.druyaned.learn_java.vol1.chapter06;

import static com.github.druyaned.ConsoleColors.bold;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import java.util.Date;
import javax.swing.Timer;
import javax.swing.JOptionPane;

public class TestTimer {
    public static void run() {
        // way1
        ActionListener listener = new TimePrinter();
        // way2
        listener = (event) -> {
            System.out.println("At the tone, the time is " + bold(new Date().toString()));
            Toolkit.getDefaultToolkit().beep();
        };
        int delay = 200;
        Timer t = new Timer(delay, listener);

        long tEnd = System.currentTimeMillis() + 2 * delay;
        t.start();
        while (System.currentTimeMillis() < tEnd) {}
        JOptionPane.showMessageDialog(null, "Quit programm?");

        t.stop();
    }
}

class TimePrinter implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("At the tone, the time is " + bold(new Date().toString()));
        Toolkit.getDefaultToolkit().beep();
    }
}