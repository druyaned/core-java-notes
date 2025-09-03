package druyaned.corejava.vol1.ch10graphics;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;

public class StringCenterer {
    
    private final Rectangle2D rect;
    private final LineMetrics metrics;
    private final int w, h;
    private int x, y;
    private int strY;
    private int centerX, centerY;
    
    public StringCenterer(Graphics2D g2, Font font, String str, int centerX, int centerY) {
        FontRenderContext frc = g2.getFontRenderContext();
        rect = font.getStringBounds(str, frc);
        metrics = font.getLineMetrics(str, frc);
        w = (int)Math.round(rect.getWidth());
        h = (int)Math.round(rect.getHeight());
        x = centerX - w / 2;
        y = centerY - h / 2;
        strY = (int)Math.round(y + metrics.getAscent());
        this.centerX = centerX;
        this.centerY = centerY;
        rect.setRect(x, y, w, h);
    }
    
    public Rectangle2D getRect() {
        return rect;
    }
    
    public LineMetrics getMetrics() {
        return metrics;
    }
    
    public int getW() {
        return w;
    }
    
    public int getH() {
        return h;
    }
    
    public int getX() {
        return x;
    }
    
    /**
     * Bounding rectangle's y-coordinate.
     * @return y-coordinate of bounding rectangle.
     */
    public int getY() {
        return y;
    }
    
    /**
     * Lowered basic line's y-coordinate to be in a bounding rectangle.
     * @return string's y-coordinate to be in a bounding rectangle.
     */
    public int getStrY() {
        return strY;
    }
    
    public int getCenterX() {
        return centerX;
    }
    
    public int getCenterY() {
        return centerY;
    }
    
    public void setXY(int x, int y) {
        centerX += x - this.x;
        centerY += y - this.y;
        this.x = x;
        this.y = y;
        strY = (int) Math.round(y + metrics.getAscent());
    }
    
}
