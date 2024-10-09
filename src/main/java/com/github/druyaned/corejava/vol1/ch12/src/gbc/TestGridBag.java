package com.github.druyaned.corejava.vol1.ch12.src.gbc;

import java.awt.*;
import javax.swing.*;
import static com.github.druyaned.ConsoleColors.bold;

public class TestGridBag implements Runnable {
    
    public static final int WIDTH;
    public static final int HEIGHT;
    public static final int X;
    public static final int Y;
    private static final double SCREEN_PART = 1.5;
    
    static {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension d = toolkit.getScreenSize();
        WIDTH = (int)(d.getWidth() / SCREEN_PART);
        HEIGHT = (int)(d.getHeight() / SCREEN_PART);
        X = (int)(d.getWidth() / 2) - (int)(WIDTH / 2);
        Y = (int)(d.getHeight() / 2) - (int)(HEIGHT / 2);
    }

    @Override public void run() {
        System.out.println("\n" + bold("Testing grid bag"));
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("Testing grid bag");
            // frame.setBounds(X, Y, WIDTH, HEIGHT);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // Creating panel and setting GridBagLayout
            JPanel panel = new JPanel();
            GridBagLayout layout = new GridBagLayout();
            panel.setLayout(layout);
            // Creating labels and button
            JLabel faceLabel = new JLabel("Face", JLabel.RIGHT);
            JLabel sizeLabel = new JLabel("Size", JLabel.RIGHT);
            JButton frameSizeButton = new JButton("Default frame size");
            // Creating combo boxes
            JComboBox<String> faceBox = new JComboBox<>(new String[] {
                "SansSerif", "Serif", "Monospaced"
            });
            JComboBox<Integer> sizeBox = new JComboBox<>(new Integer[] {
                8, 10, 12, 14, 16, 18, 20, 22, 24
            });
            faceBox.setSelectedIndex(0);
            sizeBox.setSelectedIndex(3);
            // Creating text area
            String defaultText =
                    """
                    It's a short text about something not very interesting.
                    And this is a continuation of the text you've read more recently.""";
            int defaultFontSize = 14;
            Font defaultFont = new Font(Font.SANS_SERIF, Font.PLAIN, defaultFontSize);
            double k = 1D / 4;
            JTextArea area1 = makeArea(defaultText, defaultFont, k);
            JTextArea area2 = makeArea(defaultText, defaultFont, k);
            JTextArea area3 = makeArea(defaultText, defaultFont, k);
            JTextArea area4 = makeArea(defaultText, defaultFont, k);
            // Listeners adding
            frameSizeButton.addActionListener((event) -> frame.pack());
            faceBox.addActionListener((event) -> {
                String fontName = (String)faceBox.getSelectedItem();
                int fontStyle = area1.getFont().getStyle();
                int fontSize = area1.getFont().getSize();
                area1.setFont(new Font(fontName, fontStyle, fontSize));
            });
            sizeBox.addActionListener((event) -> {
                String fontName = area1.getFont().getName();
                int fontStyle = area1.getFont().getStyle();
                int fontSize = ((Integer)sizeBox.getSelectedItem());
                area1.setFont(new Font(fontName, fontStyle, fontSize));
            });
            // Creating grid bag constraints for every component
            GBC faceLabelGBC = new GBC(0, 0, 1, 1)
                    .setAnchor(GBC.EAST)
                    .setFill(GBC.HORIZONTAL);
            GBC sizeLabelGBC = new GBC(0, 1, 1, 1)
                    .setAnchor(GBC.EAST)
                    .setFill(GBC.HORIZONTAL);
            GBC frameSizeButtonGBC = new GBC(1, 2, 1, 1)
                    .setAnchor(GBC.NORTHEAST)
                    .setFill(GBC.HORIZONTAL);
            GBC faceBoxGBC = new GBC(1, 0, 1, 1)
                    .setAnchor(GBC.WEST)
                    .setFill(GBC.BOTH);
            GBC sizeBoxGBC = new GBC(1, 1, 1, 1)
                    .setAnchor(GBC.WEST)
                    .setFill(GBC.BOTH);
            GBC area1GBC = new GBC(2, 0, 1, 2)
                    .setWeight(100, 100)
                    .setFill(GBC.BOTH);
            GBC area2GBC = new GBC(2, 2, 1, 2)
                    .setWeight(20, 20)
                    .setInsets(8, 0, 0, 0);
            GBC area3GBC = new GBC(3, 0, 1, 2)
                    .setWeight(20, 20)
                    .setInsets(8, 0, 0, 16);
            GBC area4GBC = new GBC(3, 2, 1, 2)
                    .setWeight(20, 20)
                    .setInsets(24);
            // Adding all components to the panel
            panel.add(faceLabel, faceLabelGBC);
            panel.add(sizeLabel, sizeLabelGBC);
            panel.add(frameSizeButton, frameSizeButtonGBC);
            panel.add(faceBox, faceBoxGBC);
            panel.add(sizeBox, sizeBoxGBC);
            panel.add(area1, area1GBC);
            panel.add(area2, area2GBC);
            panel.add(area3, area3GBC);
            panel.add(area4, area4GBC);
            // Adding panel to the frame
            frame.add(panel);
            frame.pack();
            frame.setLocation(X, Y);
            frame.setVisible(true);
        });
    }

    // k - coefficient to multiply {@link TestGridBag#WIDTH} and {@link TestGridBag#HEIGHT}
    public static JTextArea makeArea(String defaultText, Font defaultFont, double k) {
        JTextArea area = new JTextArea(defaultText);
        area.setFont(defaultFont);
        area.setLineWrap(true);
        area.setPreferredSize(new Dimension((int)(WIDTH * k), (int)(HEIGHT * k)));
        area.setEditable(false);
        area.setFocusable(false);
        return area;
    }
    
}
