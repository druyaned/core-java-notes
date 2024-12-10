package druyaned.corejava.vol1.ch14.src.balls;

import druyaned.corejava.vol1.ch14.src.ball.BallPane;
import druyaned.corejava.vol1.ch14.src.ball.BallImage;
import druyaned.corejava.vol1.ch14.src.ball.BallPosition;
import druyaned.corejava.vol1.ch14.src.ball.BallFrame;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.*;

public class BallsPane extends JPanel {
    
    private static final int MAX_BALLS_AMOUNT = 8;
    private static volatile boolean started = false;
    
    private static void setStarted(boolean c) {
        started = c;
    }
    
    private static boolean isStarted() {
        return started;
    }
    
    public BallsPane(
            JButton addButton, JButton pauseButton,
            JSlider stepXSlider, JSlider stepYSlider
    ) {
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
                b.getPosition().setSteps(stepXSlider.getValue(), stepYSlider.getValue())
        ));
        stepYSlider.addChangeListener(event -> balls.forEach(b ->
                b.getPosition().setSteps(stepXSlider.getValue(), stepYSlider.getValue())
        ));
        timer.start();
    }
    
}
