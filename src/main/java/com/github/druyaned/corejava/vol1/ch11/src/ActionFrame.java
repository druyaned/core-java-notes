package com.github.druyaned.corejava.vol1.ch11.src;

import static com.github.druyaned.corejava.vol1.ch11.src.Images.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ActionFrame extends JFrame {
    
    public static final int FRAME_W;
    public static final int FRAME_H;
    public static final int FRAME_X;
    public static final int FRAME_Y;
    public static final Color DEF_COLOR = new Color(224, 224, 255);
    public static final int FONT_SIZE = 18;
    
    static {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        FRAME_W = (int) Math.round(dimension.getWidth() / 2);
        FRAME_H = (int) Math.round(dimension.getHeight() / 2);
        FRAME_X = FRAME_W - FRAME_W / 2;
        FRAME_Y = FRAME_H - FRAME_H / 2;
    }

    public ActionFrame() {
        setTitle("ActionFrame");
        setBounds(FRAME_X, FRAME_Y, FRAME_W, FRAME_H);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setBackground(DEF_COLOR);
        int hints = Image.SCALE_SMOOTH;
        int fs = FONT_SIZE;
        ImageIcon erasing = new ImageIcon(makeErasing().getScaledInstance(fs, fs, hints));
        ImageIcon heart = new ImageIcon(makeHeart().getScaledInstance(fs, fs, hints));
        ImageIcon spot = new ImageIcon(makeSpot().getScaledInstance(fs, fs, hints));
        Action rAction = new ColorAction(panel, "Red", heart, Color.RED);
        Action gAction = new ColorAction(panel, "Green", erasing, Color.GREEN);
        Action dAction = new ColorAction(panel, "Default", spot, DEF_COLOR);
        panel.add(new JButton(rAction));
        panel.add(new JButton(gAction));
        panel.add(new JButton(dAction));
        InputMap iMap = panel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        iMap.put(KeyStroke.getKeyStroke("meta R"), "panel.red");
        iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.SHIFT_DOWN_MASK), "panel.green");
        iMap.put(KeyStroke.getKeyStroke("ctrl D"), "panel.default");
        ActionMap aMap = panel.getActionMap();
        aMap.put("panel.red", rAction);
        aMap.put("panel.green", gAction);
        aMap.put("panel.default", dAction);
        getContentPane().add(panel);
        setVisible(true);
    }
    
}
