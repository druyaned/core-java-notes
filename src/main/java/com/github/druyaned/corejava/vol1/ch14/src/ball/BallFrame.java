package com.github.druyaned.corejava.vol1.ch14.src.ball;

import java.awt.*;
import javax.swing.*;

public class BallFrame extends JFrame {
    
    public static final int W = 640;
    public static final int H = 512;
    public static final int X;
    public static final int Y;
    public static final Color BACK_COLOR = new Color(224, 224, 255);
    
    static {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        X = (int)(dimension.getWidth() / 2) - W / 2;
        Y = (int)(dimension.getHeight() / 2) - H / 2;
    }

    public BallFrame() {
        super("Testing ball");
        setLocation(X, Y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        JPanel contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);
        contentPane.setPreferredSize(new Dimension(W, H));
        BallPane ballPane = new BallPane(new BallImage());
        BallButtonPane buttonPane = new BallButtonPane(ballPane);
        contentPane.add(buttonPane, BorderLayout.NORTH);
        contentPane.add(ballPane, BorderLayout.CENTER);
        pack();
    }
    
}
