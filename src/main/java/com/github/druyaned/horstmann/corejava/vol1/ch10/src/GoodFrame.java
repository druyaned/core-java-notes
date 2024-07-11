package com.github.druyaned.horstmann.corejava.vol1.ch10.src;

import java.awt.*;
import javax.swing.JFrame;

public class GoodFrame extends JFrame {
    
    public GoodFrame(String title) {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension dimension = kit.getScreenSize();
        int deductibleW = Math.round(dimension.width / 4);
        int deductibleH = Math.round(dimension.height / 4);
        setSize(dimension.width - deductibleW, dimension.height - deductibleH);
        setLocation(Math.round(deductibleW / 2), Math.round(deductibleH / 2));
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        getContentPane().setBackground(new Color(224, 224, 255));
    }
    
}
