package com.github.druyaned.learn_java.vol1.chapter14;

import static com.github.druyaned.ConsoleColors.bold;
import java.awt.EventQueue;

public class TestBall {
    public static void run() {
        System.out.println("\n" + bold("Testing ball"));
        EventQueue.invokeLater(() -> new BallFrame().setVisible(true));
    }
}
