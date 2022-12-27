package com.github.druyaned.learn_java.vol1.chapter12;

import static com.github.druyaned.ConsoleColors.bold;
import java.awt.*;

import javax.swing.*;

public class TestCalculator {
    public static void run() {
        System.out.println("\n" + bold("Testing calculator"));

        EventQueue.invokeLater(() -> {
            JFrame calcFrame = new JFrame("Calculator");
            calcFrame.setTitle("Calculator");
            calcFrame.setResizable(false);
            calcFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            calcFrame.add(new CalcPanel());
            calcFrame.pack();
            calcFrame.setLocation(CalcPanel.X, CalcPanel.Y);
            calcFrame.setVisible(true);
        });
    }
}
