package com.github.druyaned.learn_java.vol1.chapter14;

import static com.github.druyaned.ConsoleColors.bold;
import java.awt.EventQueue;

public class TestBalls {
    public static void run() {
        System.out.println("\n" + bold("Testing balls (concurrency)"));
        EventQueue.invokeLater(() -> new BallsFrame().setVisible(true));
    }
}
