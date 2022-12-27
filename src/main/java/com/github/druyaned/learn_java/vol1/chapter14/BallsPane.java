package com.github.druyaned.learn_java.vol1.chapter14;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.function.BooleanSupplier;
import javax.swing.*;

public class BallsPane extends JPanel {
    private static final int MAX_BALLS_AMOUNT = 8;
    private static volatile boolean started = false;
    
//-Static-Methods-----------------------------------------------------------------------------------
    
    private static void setStarted(boolean c) { started = c; }
    
    private static boolean isStarted() { return started; }
    
//-Non-static---------------------------------------------------------------------------------------
    
    public BallsPane(final JButton addButton,
                     final JButton pauseButton,
                     final JSlider stepXSlider,
                     final JSlider stepYSlider) {
        
        super();
        setPreferredSize(new Dimension(BallFrame.W, BallPane.H));
        setBackground(BallFrame.BACK_COLOR);
        
        final ArrayList<BallImage> balls = new ArrayList<>(MAX_BALLS_AMOUNT);
        Timer timer = new Timer(BallPosition.DELAY, (event) -> {
            if (isStarted()) {
                balls.forEach(b -> b.repaint());
            }
        });
        addButton.addActionListener(event -> {
            if (balls.size() >= MAX_BALLS_AMOUNT) {
                if (!addButton.getText().equals("Start")) {
                    addButton.setText("Start");
                }
            } else {
                BallImage ball = new BallImage();
                final BallPosition position = ball.getPosition();
                Thread positionCalculator = new Thread(() -> {
                    try {
                        while (true) {
                            if (isStarted()) {
                                position.makeStep();
                            }
                            Thread.sleep(BallPosition.DELAY);
                        }
                    } catch (InterruptedException exc) {
                        throw new RuntimeException(exc);
                    }
                });
                balls.add(ball);
                positionCalculator.start();
                BallsPane.this.add(ball);
                BallsPane.this.revalidate();
            }
            setStarted(true);
        });
        pauseButton.addActionListener(event -> setStarted(false));
        stepXSlider.addChangeListener(event -> balls.forEach(b ->
                b.getPosition().setSteps(stepXSlider.getValue(), stepYSlider.getValue())));
        stepYSlider.addChangeListener(event -> balls.forEach(b ->
                b.getPosition().setSteps(stepXSlider.getValue(), stepYSlider.getValue())));
        
        timer.start();
    }
}
