package druyaned.corejava.vol1.ch14concurrency.t01bounce;

import java.awt.Dimension;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Ball {
    
    public static final double MIN_DELTA = 0.25d;
    public static final double MAX_DELTA = 2;
    
    private final double diameter;
    private final Random random;
    private final Ellipse2D circle;
    private double x, y;
    private double dx, dy;
    
    public Ball(double diameter) {
        this.diameter = diameter;
        this.random = new Random();
        this.circle = new Ellipse2D.Double(0d, 0d, diameter, diameter);
        x = y = 0d;
        dx = +generatePositiveDelta();
        dy = +generatePositiveDelta();
    }
    
    private double generatePositiveDelta() {
        return MIN_DELTA + random.nextDouble(MAX_DELTA - MIN_DELTA + 1d);
    }
    
    public void move(Dimension boundary) {
        // update x
        double nextX = x + dx;
        if (nextX < 0d)
            x += (dx = +generatePositiveDelta());
        else if (nextX > boundary.width - diameter)
            x += (dx = -generatePositiveDelta());
        else
            x = nextX;
        // update y
        double nextY = y + dy;
        if (nextY < 0d)
            y += (dy = +generatePositiveDelta());
        else if (nextY > boundary.height - diameter)
            y += (dy = -generatePositiveDelta());
        else
            y = nextY;
    }
    
    public Ellipse2D shape() {
        circle.setFrame(x, y, diameter, diameter);
        return circle;
    }
    
    public double diameter() {
        return diameter;
    }
    
    public Random random() {
        return random;
    }
    
    public double x() {
        return x;
    }
    
    public double y() {
        return y;
    }
    
    public double dx() {
        return dx;
    }
    
    public double dy() {
        return dy;
    }
    
}
