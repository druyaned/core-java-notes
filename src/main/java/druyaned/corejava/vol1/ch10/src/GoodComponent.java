package druyaned.corejava.vol1.ch10.src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.SystemColor;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import javax.swing.JComponent;

public class GoodComponent extends JComponent {
    
    final static int FONT_SIZE = 24;
    final static Font FONT = new Font(Font.MONOSPACED, Font.PLAIN, FONT_SIZE);
    final static Font LABEL_FONT =
            new Font(Font.SANS_SERIF, Font.ITALIC + Font.BOLD, FONT_SIZE / 2);
    final static String STR = "I like to move it, move it";
    final static String LABEL = "Oh, yeah!";

    private final int frameW;
    private final int frameH;
    private final int centerX;
    private final int centerY;

    public GoodComponent(int frameW, int frameH) {
        this.frameW = frameW;
        this.frameH = frameH;
        centerX = frameW / 2;
        centerY = frameH / 2;
    }

    @Override protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        // Initializing string's bounds
        StringCenterer strCenterer = new StringCenterer(
                g2, FONT, STR, centerX, centerY
        );
        StringCenterer labelCenterer = new StringCenterer(
                g2, LABEL_FONT, LABEL, centerX, centerY
        );
        labelCenterer.setXY(labelCenterer.getX(), strCenterer.getY() + strCenterer.getH());
        // Initializing ellipse
        double elX = strCenterer.getX() - strCenterer.getH();
        double elY = strCenterer.getY() - strCenterer.getH();
        double elW = strCenterer.getW() + 2 * strCenterer.getH();
        double elH = strCenterer.getH() + 2 * strCenterer.getH();
        Ellipse2D ellipse = new Ellipse2D.Double(elX, elY, elW, elH);
        // Initializing lines
        final Point2D A = new Point2D.Double(elX, centerY);
        final Point2D B = new Point2D.Double(strCenterer.getX(), centerY);
        final Point2D C = new Point2D.Double(elX + elW, centerY);
        final Point2D D = new Point2D.Double(strCenterer.getX() +
                      strCenterer.getW(), centerY);
        Line2D line1 = new Line2D.Float(A, B);
        Line2D line2 = new Line2D.Float(C, D);
        // Initializing circle
        double radius = new Point2D.Double(centerX, centerY).distance(A);
        double cornerX = centerX + radius, cornerY = centerY + radius;
        Ellipse2D circle = new Ellipse2D.Double();
        circle.setFrameFromCenter(centerX, centerY, cornerX, cornerY);
        // Drawing circle, ellipse and lines
        g2.setPaint(Color.BLUE);
        g2.draw(circle);
        g2.setPaint(Color.BLACK);
        g2.draw(ellipse);
        g2.draw(line1);
        g2.draw(line2);
        // Drawing rectangle
        g2.setPaint(new Color(255, 0, 0));
        g2.fill(strCenterer.getRect());
        g2.setPaint(Color.GREEN);
        g2.draw(strCenterer.getRect());
        // Drawing str
        g2.setPaint(SystemColor.textText);
        g2.setFont(FONT);
        g2.drawString(STR, strCenterer.getX(), strCenterer.getStrY());
        // Drawing label
        g2.setPaint(Color.DARK_GRAY);
        g2.setFont(LABEL_FONT);
        g2.drawString(LABEL, labelCenterer.getX(), labelCenterer.getStrY());
    }

    /**
     * Usage (it isn't explicitly called):
     * <pre>
     *  frame.getContentPane().add(component);
     *  frame.pack();
     * </pre>
     * @return full screen dimension (explicit call isn't recommended).
     */
    @Override public Dimension getPreferredSize() {
        return new Dimension(frameW, frameH);
    }
    
}
