package com.github.druyaned.learn_java.vol1.chapter11;

import static com.github.druyaned.ConsoleColors.*;
import java.awt.*;

import javax.swing.*;

public class TestAction {
    public static void run() {
        System.out.println("\n" + bold("Testing actions."));

        EventQueue.invokeLater(() -> {
            ActionFrame actionFrame = new ActionFrame();
            setFrameStyle(actionFrame);
            actionFrame.setVisible(true);
        });
    }

    public static void setFrameStyle(JFrame f) {
        UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
        try {
            UIManager.setLookAndFeel(infos[0].getClassName());
            SwingUtilities.updateComponentTreeUI(f);
        } catch (ClassNotFoundException |
                IllegalAccessException |
                InstantiationException |
                UnsupportedLookAndFeelException exc) {
            
            throw new RuntimeException(exc);
        }
    }
}
