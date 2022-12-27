package com.github.druyaned.learn_java.vol1.chapter14;

import static com.github.druyaned.learn_java.vol1.chapter14.BallPosition.DEFAULT_STEP;
import java.awt.*;
import javax.swing.*;

public class BallsFrame extends JFrame {
    public static final int X = BallFrame.X + 8;
    public static final int Y = BallFrame.Y + 8;
    
    public BallsFrame() {
        super("Testing balls (concurrency)");
        setLocation(X, Y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        JPanel contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);
        contentPane.setPreferredSize(new Dimension(BallFrame.W, BallFrame.H));
        
        int minStep = 1;
        int maxStep = BallPosition.MAX_STEP;
        
        // buttons and sliders
        JButton addBall = new JButton("Add");
        JButton pause = new JButton("Stop");
        JSlider stepXSlider;
        JSlider stepYSlider;
        stepXSlider = new JSlider(JSlider.HORIZONTAL, minStep, maxStep, DEFAULT_STEP);
        stepXSlider.setMajorTickSpacing(maxStep / 2);
        stepXSlider.setMinorTickSpacing(1);
        stepXSlider.setPaintTicks(true);
        stepXSlider.setSnapToTicks(true);
        stepYSlider = new JSlider(JSlider.HORIZONTAL, minStep, maxStep, DEFAULT_STEP);
        stepYSlider.setMajorTickSpacing(maxStep / 2);
        stepYSlider.setMinorTickSpacing(1);
        stepYSlider.setPaintTicks(true);
        stepYSlider.setSnapToTicks(true);
        
        BallsPane ballsPane = new BallsPane(addBall, pause, stepXSlider, stepYSlider);
        BallsButtonPane buttonsPane =
                new BallsButtonPane(addBall, pause, stepXSlider, stepYSlider);
        
        contentPane.add(buttonsPane, BorderLayout.NORTH);
        contentPane.add(ballsPane, BorderLayout.CENTER);
        pack();
    }
}
