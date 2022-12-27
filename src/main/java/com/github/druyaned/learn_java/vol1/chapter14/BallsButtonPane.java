package com.github.druyaned.learn_java.vol1.chapter14;

import java.awt.*;

import javax.swing.*;

public class BallsButtonPane extends JPanel {
    public BallsButtonPane(final JButton addButton,
                            final JButton pauseButton,
                            final JSlider stepXSlider,
                            final JSlider stepYSlider) {
        
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
