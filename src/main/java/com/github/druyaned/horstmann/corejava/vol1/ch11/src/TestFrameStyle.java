package com.github.druyaned.horstmann.corejava.vol1.ch11.src;

import static com.github.druyaned.ConsoleColors.bold;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TestFrameStyle implements Runnable {
    
    @Override public void run() {
        System.out.println("\n" + bold("Testing frame styles."));
        UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
        int offset = 80;
        EventQueue.invokeLater(() -> {
            for (int i = 0; i < infos.length; ++i) {
                ButtonsFrame buttonsFrame = new ButtonsFrame(
                        infos[i].getName(), offset * i, offset * i
                );
                ButtonsPanel buttonsPanel = new ButtonsPanel();
                buttonsFrame.getContentPane().add(buttonsPanel);
                try {
                    UIManager.setLookAndFeel(infos[i].getClassName());
                    SwingUtilities.updateComponentTreeUI(buttonsFrame);
                } catch (
                        ClassNotFoundException |
                        IllegalAccessException |
                        InstantiationException |
                        UnsupportedLookAndFeelException exc
                ) {
                    throw new RuntimeException(exc);
                }
                buttonsFrame.setVisible(true);
            }
        });
    }
    
}

class Closer extends WindowAdapter {
    
    private final ButtonsFrame buttonsFrame;
    private final int x, y, w, h;

    public Closer(ButtonsFrame buttonsFrame, int x, int y, int w, int h) {
        this.buttonsFrame = buttonsFrame;
        this.x = x + w / 4;
        this.y = y + h / 4;
        this.w = w / 2;
        this.h = h / 2;
    }

    @Override public void windowClosing(WindowEvent event) {
        JFrame askFrame = new JFrame();
        askFrame.setBounds(x, y, w, h);
        JPanel p = new JPanel();
        JButton closeButton = new JButton("Close");
        JButton reconsiderButton = new JButton("I reconsidered");
        p.add(closeButton);
        p.add(reconsiderButton);
        closeButton.addActionListener(e -> {
            buttonsFrame.dispose();
            askFrame.dispose();
        });
        reconsiderButton.addActionListener(e -> askFrame.dispose());
        p.setBounds(0, 0, w, h / 2);
        askFrame.getContentPane().add(p);
        askFrame.setVisible(true);
    }

    public int getW() {
        return w;
    }
    
    public int getH() {
        return h;
    }
    
}

class ButtonsFrame extends JFrame {
    
    public static final Dimension DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();

    public ButtonsFrame(String title, int x, int y) {
        int w = (int)Math.round(DIMENSION.getWidth() / 2);
        int h = (int)Math.round(DIMENSION.getHeight() / 2);
        setTitle(title);
        setBounds(x, y, w, h);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        Closer closer = new Closer(this, x, y, w, h);
        addWindowListener(closer);
    }
    
}

class ButtonsPanel extends JPanel {
    
    final static Color DEF = new Color(224, 224, 255);

    public ButtonsPanel() {
        makeButton("Red", Color.RED);
        makeButton("Green", Color.GREEN);
        makeButton("Default", DEF);
        setBackground(DEF);
    }

    private void makeButton(String name, Color bg) {
        JButton b = new JButton(name);
        add(b);
        b.addActionListener(event -> setBackground(bg));
    }
    
}
