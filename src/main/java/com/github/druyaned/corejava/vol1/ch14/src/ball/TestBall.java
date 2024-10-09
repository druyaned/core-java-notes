package com.github.druyaned.corejava.vol1.ch14.src.ball;

import static com.github.druyaned.ConsoleColors.bold;
import java.awt.EventQueue;

public class TestBall implements Runnable {
    
    @Override public void run() {
        System.out.println("\n" + bold("Testing ball"));
        EventQueue.invokeLater(() -> new BallFrame().setVisible(true));
    }
    
}
