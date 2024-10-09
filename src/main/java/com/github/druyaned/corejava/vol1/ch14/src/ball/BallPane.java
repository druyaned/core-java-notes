package com.github.druyaned.corejava.vol1.ch14.src.ball;

import static com.github.druyaned.corejava.vol1.ch14.src.ball.BallPosition.DEFAULT_STEP;
import java.awt.*;
import javax.swing.*;

public class BallPane extends JPanel {
    
    public static final int H = BallFrame.H - BallButtonPane.H;
    
    private final JButton start = new JButton("Start");
    private final JButton stop = new JButton("Stop");
    private final JSlider stepXSlider;
    private final JSlider stepYSlider;
    
    public BallPane(BallImage ball) {
        super();
        setPreferredSize(new Dimension(BallFrame.W, H));
        setBackground(BallFrame.BACK_COLOR);
        add(ball);
        BallPosition position = ball.getPosition();
        Timer timer = new Timer(BallPosition.DELAY, (event) -> {
            position.makeStep();
            ball.repaint();
        });
        start.addActionListener((event) -> timer.start());
        stop.addActionListener((event) -> timer.stop());
        int minStep = 1;
        int maxStep = BallPosition.MAX_STEP;
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
        stepXSlider.addChangeListener(event ->
                position.setSteps(stepXSlider.getValue(), stepYSlider.getValue())
        );
        stepYSlider.addChangeListener(event ->
                position.setSteps(stepXSlider.getValue(), stepYSlider.getValue())
        );
    }
    
    public JButton getStart() {
        return start;
    }
    
    public JButton getStop() {
        return stop;
    }
    
    public JSlider getStepXSlider() {
        return stepXSlider;
    }
    
    public JSlider getStepYSlider() {
        return stepYSlider;
    }
    
}
