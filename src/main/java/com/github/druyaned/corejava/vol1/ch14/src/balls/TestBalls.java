package com.github.druyaned.corejava.vol1.ch14.src.balls;

import static com.github.druyaned.ConsoleColors.bold;
import java.awt.EventQueue;

public class TestBalls implements Runnable {
    
    @Override public void run() {
        System.out.println("\n" + bold("Testing balls (concurrency)"));
        EventQueue.invokeLater(() -> new BallsFrame().setVisible(true));
    }
    
}
