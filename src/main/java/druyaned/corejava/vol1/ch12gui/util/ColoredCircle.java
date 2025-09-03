package druyaned.corejava.vol1.ch12gui.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;

public class ColoredCircle extends JComponent {
    
    private int diameter;
    private Ellipse2D ellipse;
    private GradientPaint gradient;
    private GradientPaint[] gradients;
    private int xMin;
    private int yMin;
    private int yMax;
    private int step;
    
    public ColoredCircle(int diameter, int xMin, int yMin, int yMax, int step) {
        this.diameter = diameter;
        this.xMin = xMin;
        this.yMin = yMin;
        this.yMax = yMax;
        this.step = step;
        ellipse = new Ellipse2D.Double(xMin, yMin, diameter, diameter);
        int amount = (yMax - yMin) / step;
        gradients = new GradientPaint[amount + 1];
        float delta = diameter / 4F;
        float x = (float) xMin, y = (float) yMin;
        for (int i = 0; i <= amount; ++i) {
            float x1 = x + delta, y1 = y + delta + step * i;
            float x2 = x + diameter - delta, y2 = y + diameter - delta + step * i;
            gradients[i] = new GradientPaint(x1, y1, C1, x2, y2, C2);
        }
        gradient = gradients[0];
        setPreferredSize(new Dimension(diameter, diameter));
    }
    
    public ColoredCircle(int xMin, int yMin, int yMax, int step) {
        this(DEFAULT_DIAMETER, xMin, yMin, yMax, step);
    }
    
    public int diameter() {
        return this.diameter;
    }
    
    public int xMin() {
        return this.xMin;
    }
    
    public int yMin() {
        return this.yMin;
    }
        
    public int yMax() {
        return this.yMax;
    }
    
    public int step() {
        return this.step;
    }
    
    public int steps() {
        return gradients.length;
    }
    
    public void setXY(int x, int y) {
        ellipse.setFrame(x, y, diameter, diameter);
        int i = (y - yMin) / step;
        this.gradient = gradients[i];
    }
    
    @Override public void paintComponent(Graphics g)  {
        Graphics2D g2 = (Graphics2D) g;
        Paint prevPaint = g2.getPaint();
        g2.setPaint(gradient);
        g2.fill(ellipse);
        g2.setPaint(prevPaint);
    }
    
    public static final int DEFAULT_DIAMETER = 32;
    private static final Color C1 = new Color(128, 128, 255);
    private static final Color C2 = new Color(224, 192, 255);
    
}
