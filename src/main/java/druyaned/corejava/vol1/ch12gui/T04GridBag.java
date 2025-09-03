package druyaned.corejava.vol1.ch12gui;

import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import druyaned.corejava.util.ScreenSize;
import druyaned.corejava.vol1.ch12gui.t04gbc.GBC;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class T04GridBag extends Topic {
    
    public T04GridBag(Chapter chapter) {
        super(chapter, 4);
    }
    
    @Override public String title() {
        return "Topic 04 GridBag";
    }
    
    @Override public void run() {
        Rectangle bounds = ScreenSize.get(0.5);
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("Testing grid bag");
            frame.setBounds(bounds);
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
            double k = 1d / 4d;
            JTextArea area1 = makeArea(defaultText, defaultFont, k, bounds);
            JTextArea area2 = makeArea(defaultText, defaultFont, k, bounds);
            JTextArea area3 = makeArea(defaultText, defaultFont, k, bounds);
            JTextArea area4 = makeArea(defaultText, defaultFont, k, bounds);
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
            frame.setVisible(true);
        });
    }
    
    // k - coefficient to multiply {@link T04GridBag#WIDTH} and {@link T04GridBag#HEIGHT}
    public static JTextArea makeArea(
            String defaultText,
            Font defaultFont,
            double k,
            Rectangle bounds
    ) {
        JTextArea area = new JTextArea(defaultText);
        area.setFont(defaultFont);
        area.setLineWrap(true);
        area.setPreferredSize(new Dimension((int)(bounds.width * k), (int)(bounds.height * k)));
        area.setEditable(false);
        area.setFocusable(false);
        return area;
    }
    
}
