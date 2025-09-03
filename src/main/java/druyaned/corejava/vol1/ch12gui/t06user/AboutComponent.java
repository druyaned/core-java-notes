package druyaned.corejava.vol1.ch12gui.t06user;

import druyaned.corejava.vol1.ch10graphics.StringCenterer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import javax.swing.JComponent;

public class AboutComponent extends JComponent {
    
    private static final Font FONT = new Font(Font.MONOSPACED, Font.ITALIC + Font.BOLD, 24);
    private final int centerX;
    private final int centerY;
    
    public AboutComponent(int width, int height) {
        centerX = width / 2;
        centerY = height / 2;
    }
    
    @Override public void paintComponent(Graphics g) {
        // Finding string bounds
        Graphics2D g2 = (Graphics2D) g;
        String str = "Testing dialog";
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
