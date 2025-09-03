package druyaned.corejava.vol1.ch14concurrency.t01bounce;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import javax.swing.JPanel;

public class BallPanel extends JPanel {
    
    private final Dimension dimension;
    private final Ball[] balls;
    private final Paint ballPaint;
    
    public BallPanel(int width, int height, int ballCount, double ballDiameter) {
        dimension = new Dimension(width, height);
        balls = new Ball[ballCount];
        for (int i = 0; i < ballCount; i++)
            balls[i] = new Ball(ballDiameter);
        Color color1 = new Color(0x5A28C8);
        Color color2 = new Color(0xAD94E4);
        ballPaint = new GradientPaint(0f, 0f, color1, width, height, color2);
    }
    
    public Ball ballAt(int i) {
        return balls[i];
    }
    
    public int ballCount() {
        return balls.length;
    }
    
    @Override public void paintComponent(Graphics g) {
        super.paintComponent(g); // clear background
        Graphics2D g2 = (Graphics2D)g;
        g2.setPaint(ballPaint);
        for (Ball ball : balls)
            g2.fill(ball.shape()); // print each ball
    }
    
    @Override public Dimension getPreferredSize() {
        return dimension;
    }
    
}
