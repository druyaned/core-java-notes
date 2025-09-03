package druyaned.corejava.vol1.ch11events.t04mouse;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JComponent;

public class MouseComponent extends JComponent {
    
    private final MouseFrame frame;
    private final ArrayList<Rectangle> rects;
    private final ArrayList<Rectangle> spaces;
    
    public MouseComponent(MouseFrame frame, Rectangle bounds, Cursors cursors) {
        this.frame = frame;
        rects = new ArrayList<>();
        spaces = new ArrayList<>();
        setCursor(cursors.hand());
        setPreferredSize(new Dimension(bounds.width, bounds.height));
        addMouseListener(new MouseHandler(this, cursors));
        addMouseMotionListener(new MouseMotionHandler(this, cursors));
    }
    
    public Rectangle getRect(int i) {
        return rects.get(i);
    }
    
    public MouseFrame getMouseFrame() {
        return frame;
    }
    
    /**
     * Searching for is there rectangle in this point.
     * @param p point to check.
     * @return {@code index} of found rectangle or {@code -1} if there is no rectangle.
     */
    public int findSpace(Point p) {
        for (int i = 0; i < spaces.size(); ++i) {
            int x1 = (int)spaces.get(i).getX();
            int x2 = (int)spaces.get(i).getWidth() + x1;
            int y1 = (int)spaces.get(i).getY();
            int y2 = (int)spaces.get(i).getHeight() + y1;
            int px = (int)p.getX();
            int py = (int)p.getY();
            if (px >= x1 && px <= x2 && py >= y1 && py <= y2)
                return i;
        }
        return -1;
    }
    
    /**
     * Adds new rectangle to the point.
     * @param p point to add a rectangle.
     */
    public void addRect(Point p) {
        int x = (int)p.getX();
        int y = (int)p.getY();
        int spaceX = x - RECT_L, spaceY = y - RECT_L;
        Rectangle newSpace = new Rectangle(spaceX, spaceY, SPACE_L, SPACE_L);
        Rectangle newRect = new Rectangle(x - HALF_L, y - HALF_L, RECT_L, RECT_L);
        spaces.add(newSpace);
        rects.add(newRect);
        frame.setBits(spaceX, spaceX + SPACE_L, spaceY, spaceY + SPACE_L);
        repaint();
    }
    
    public void removeRect(int i) {
        if (i >= spaces.size()) {return;}
        spaces.remove(i);
        rects.remove(i);
        frame.unsetBits();
        for (Rectangle space : spaces) {
            int x = (int)space.getX();
            int y = (int)space.getY();
            frame.setBits(x, x + SPACE_L, y, y + SPACE_L);
        }
        repaint();
    }
    
    @Override public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for (Rectangle2D r : rects)
            g2.draw(r);
    }
    
    private static final int SPACE_L = 32;
    private static final int RECT_L = SPACE_L / 2;
    private static final int HALF_L = RECT_L / 2;
    
}
