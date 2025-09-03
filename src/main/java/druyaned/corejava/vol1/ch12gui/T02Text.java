package druyaned.corejava.vol1.ch12gui;

import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import druyaned.corejava.util.ScreenSize;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeListener;

public class T02Text extends Topic {
    
    public T02Text(Chapter chapter) {
        super(chapter, 2);
    }
    
    @Override public String title() {
        return "Topic 02 Text";
    }
    
    @Override public void run() {
        Rectangle bounds = ScreenSize.get(0.5);
        final int L = (int)(bounds.width / 2) - 2;
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("Testing text fields");
            frame.setBounds(bounds);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            // North: user name and password
            JPanel northPane = new JPanel(new GridLayout(2, 2));
            JLabel userLabel = new JLabel("User name: ", JLabel.RIGHT);
            JLabel passLabel = new JLabel("Password: ", JLabel.RIGHT);
            JTextField userName = new JTextField("Lucas", L);
            JPasswordField pass = new JPasswordField("8e5T!pas5W0rD", L);
            pass.setEchoChar('*');
            userLabel.setFont(FONT);
            userName.setFont(FONT);
            passLabel.setFont(FONT);
            pass.setFont(FONT);
            northPane.add(userLabel);
            northPane.add(userName);
            northPane.add(passLabel);
            northPane.add(pass);
            final int ROWS = 4;
            final int COLUMNS = L * 2;
            // Center: text area, scroll pane and etched border
            JTextArea area = new JTextArea(ROWS, COLUMNS);
            area.setLineWrap(true);
            area.setEditable(false);
            area.setFont(FONT);
            JScrollPane scrollPane = new JScrollPane(area);
            Border etched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
            Border titledEtched = BorderFactory.createTitledBorder(
                    etched,
                    "Text area",
                    TitledBorder.RIGHT,
                    TitledBorder.DEFAULT_POSITION,
                    new Font(Font.SANS_SERIF, Font.PLAIN, (int)(FONT_SIZE * 0.75)),
                    Color.BLUE
            );
            area.setBorder(titledEtched);
            // South: buttons, button group, combo box, slider
            JPanel southPaneNorth = new JPanel();
            southPaneNorth.setBackground(new Color(R_VAL, G_VAL, B_VAL));
            JButton showButton = new JButton("Show");
            showButton.addActionListener((event) -> {
                area.setText("Displaying some private info:\n");
                area.append("username: " + userName.getText() + "\n");
                area.append("password: " + new String(pass.getPassword()));
            });
            showButton.setFont(FONT);
            southPaneNorth.add(showButton);
            JCheckBox boldFlag = new JCheckBox("Bold");
            JCheckBox italicFlag = new JCheckBox("Italic");
            ActionListener flagsListener = (event) -> {
                String fontName = area.getFont().getName();
                int mod = 0;
                int fontSize = area.getFont().getSize();
                if (boldFlag.isSelected())
                    mod += Font.BOLD;
                if (italicFlag.isSelected())
                    mod += Font.ITALIC;
                area.setFont(new Font(fontName, mod, fontSize));
            };
            boldFlag.addActionListener(flagsListener);
            italicFlag.addActionListener(flagsListener);
            boldFlag.setFont(FONT);
            italicFlag.setFont(FONT);
            southPaneNorth.add(boldFlag);
            southPaneNorth.add(italicFlag);
            ButtonGroup fontSizeSwitchers = new ButtonGroup();
            JRadioButton smallFont = new JRadioButton("Small", false);
            JRadioButton normalFont = new JRadioButton("Normal", false);
            JRadioButton bigFont = new JRadioButton("Big", false);
            fontSizeSwitchers.add(smallFont);
            fontSizeSwitchers.add(normalFont);
            fontSizeSwitchers.add(bigFont);
            int smallSize = (int)(FONT_SIZE * 0.75);
            int bigSize = (int)(FONT_SIZE * 1.25);
            ActionListener fontSizeListener = (event) -> {
                Font prevFont = area.getFont();
                String fontName = prevFont.getFontName();
                int mod = prevFont.getStyle();
                if (smallFont.isSelected())
                    area.setFont(new Font(fontName, mod, smallSize));
                else if (normalFont.isSelected())
                    area.setFont(new Font(fontName, mod, FONT_SIZE));
                else if (bigFont.isSelected())
                    area.setFont(new Font(fontName, mod, bigSize));
            };
            smallFont.addActionListener(fontSizeListener);
            normalFont.addActionListener(fontSizeListener);
            bigFont.addActionListener(fontSizeListener);
            southPaneNorth.add(smallFont);
            southPaneNorth.add(normalFont);
            southPaneNorth.add(bigFont);
            Border lineBorder = BorderFactory.createLineBorder(Color.BLUE, 2, true);
            String[] fontNames = new String[] {
                "SansSerif", "Serif", "Monospaced", "Dialog", "DialogInput"
            };
            JComboBox<String> fontBox = new JComboBox<>(fontNames);
            fontBox.addActionListener((event) -> {
                int mod = area.getFont().getStyle();
                int size = area.getFont().getSize();
                area.setFont(new Font(
                    fontBox.getItemAt(fontBox.getSelectedIndex()), mod, size
                ));
            });
            southPaneNorth.add(fontBox);
            JPanel southPaneSouth = new JPanel();
            JPanel southPane = new JPanel(new BorderLayout());
            southPane.setPreferredSize(new Dimension(bounds.width, bounds.height / 3));
            int th = 4; // thickness
            Border matteBorder = BorderFactory.createMatteBorder(th, th, th, th, Color.GREEN);
            Border compoundBorder = BorderFactory.createCompoundBorder(matteBorder, lineBorder);
            southPane.setBorder(compoundBorder);
            JSlider slider = new JSlider(JSlider.HORIZONTAL, 155, 255, G_VAL);
            slider.setMajorTickSpacing(20);
            slider.setMinorTickSpacing(5);
            slider.setPaintTicks(true);
            slider.setSnapToTicks(true);
            ChangeListener changeListener = (event) -> {
                // JSlider source = (JSlider)event.getSource(); // can be used
                Color color = new Color(R_VAL, slider.getValue(), B_VAL);
                southPaneNorth.setBackground(color);
            };
            slider.addChangeListener(changeListener);
            JLabel greenLabel = new JLabel("Green component: ");
            southPaneSouth.add(greenLabel);
            southPaneSouth.add(slider);
            southPane.add(southPaneNorth, BorderLayout.NORTH);
            southPane.add(southPaneSouth, BorderLayout.SOUTH);
            frame.add(northPane, BorderLayout.NORTH);
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.add(southPane, BorderLayout.SOUTH);
            frame.setVisible(true);
        });
    }
    
    private static final int R_VAL = 225;
    private static final int G_VAL = 225;
    private static final int B_VAL = 255;
    private static final int FONT_SIZE = 18;
    private static final Font FONT = new Font(Font.SANS_SERIF, Font.PLAIN, FONT_SIZE);
    
}
