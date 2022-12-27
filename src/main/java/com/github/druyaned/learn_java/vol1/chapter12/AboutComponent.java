package com.github.druyaned.learn_java.vol1.chapter12;

import com.github.druyaned.learn_java.vol1.chapter10.StringCenterer;

import java.awt.*;

import javax.swing.*;

public class AboutComponent extends JComponent {
    public static final Font FONT = new Font(Font.MONOSPACED, Font.ITALIC + Font.BOLD, 24);

    private final int width, height;

    public AboutComponent(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void paintComponent(Graphics g) {
        // Finding string bounds
        Graphics2D g2 = (Graphics2D) g;
        String str = "Testing dialog";
        int centerX = width / 2;
        int centerY = height / 2;
        StringCenterer centerer = new StringCenterer(g2, FONT, str, centerX, centerY);
        
        // Paint string
        Paint prevPaint = g2.getPaint();
        Font prevFont = g2.getFont();
        
        g2.setPaint(Color.MAGENTA);
        g2.setFont(FONT);
        g2.drawString(str, centerer.getX(), centerer.getY());
        g2.setPaint(prevPaint);
        g2.setFont(prevFont);
    }
}