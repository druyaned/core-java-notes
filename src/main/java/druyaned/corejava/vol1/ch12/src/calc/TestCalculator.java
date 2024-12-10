package druyaned.corejava.vol1.ch12.src.calc;

import static druyaned.ConsoleColors.bold;
import java.awt.*;
import javax.swing.*;

public class TestCalculator implements Runnable {
    
    @Override public void run() {
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
