package druyaned.corejava.vol1.ch14.src.ball;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;

public class Ball extends JComponent {
    
    private static final Color C1 = new Color(128, 128, 255);
    private static final Color C2 = new Color(224, 192, 255);

    private final int diameter;
    private final Ellipse2D ellipse;
    private final int x = 0, y = 0;
    
    public Ball(int diameter) {
        this.diameter = diameter;
        ellipse = new Ellipse2D.Double(x, y, diameter, diameter);
    }
    
    public Ball() {
        this(64);
    }

    public int getDiameter() {
        return diameter;
    }

    @Override public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        float delta = diameter / 4F;
        float x1 = x + delta, y1 = y + delta;
        float x2 = x + diameter - delta, y2 = y + diameter - delta;
        GradientPaint gradient = new GradientPaint(x1, y1, C1, x2, y2, C2);
        Paint prevPaint = g2.getPaint();
        g2.setPaint(gradient);
        g2.fill(ellipse);
        g2.setPaint(prevPaint);
    }

    @Override public Dimension getPreferredSize() {
        return new Dimension(diameter, diameter);
    }
    
}
