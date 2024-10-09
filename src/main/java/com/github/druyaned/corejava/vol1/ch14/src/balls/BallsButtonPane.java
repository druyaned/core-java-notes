package com.github.druyaned.corejava.vol1.ch14.src.balls;

import com.github.druyaned.corejava.vol1.ch14.src.ball.BallButtonPane;
import com.github.druyaned.corejava.vol1.ch14.src.ball.BallFrame;
import java.awt.*;
import javax.swing.*;

public class BallsButtonPane extends JPanel {
    
    public BallsButtonPane(
            JButton addButton, JButton pauseButton,
            JSlider stepXSlider, JSlider stepYSlider
    ) {
        setBackground(BallButtonPane.BACK_COLOR);
        setPreferredSize(new Dimension(BallFrame.W, BallButtonPane.H));
        add(addButton);
        add(pauseButton);
        JLabel stepXLabel = new JLabel("StepX");
        JLabel stepYLabel = new JLabel("StepY");
        stepXLabel.setHorizontalAlignment(JLabel.RIGHT);
        stepYLabel.setHorizontalAlignment(JLabel.RIGHT);
        add(stepXLabel);
        add(stepXSlider);
        add(stepYLabel);
        add(stepYSlider);
    }
    
}
