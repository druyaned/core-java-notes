package druyaned.corejava.vol1.ch12.src.text;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import static druyaned.ConsoleColors.bold;

public class TestText implements Runnable {
    
    private static final int R_VAL = 225;
    private static final int G_VAL = 225;
    private static final int B_VAL = 255;
    public static final int WIDTH;
    public static final int HEIGHT;
    public static final int X;
    public static final int Y;
    private static final float HEIGHT_WIDTH_RATIO = 0.75F;
    private static final int SCREEN_PART = 2;
    
    static {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension d = toolkit.getScreenSize();
        WIDTH = (int)(d.getWidth() / SCREEN_PART);
        HEIGHT = (int)(WIDTH * HEIGHT_WIDTH_RATIO);
        X = (int)(d.getWidth() / 2) - WIDTH / 2;
        Y = (int)(d.getHeight() / 2) - HEIGHT / 2;
    }

    public static final int FONT_SIZE = 18;
    public static final Font FONT = new Font(Font.SANS_SERIF, Font.PLAIN, FONT_SIZE);
    public static final int L = (int)(WIDTH / 2) - 2;

    @Override public void run() {
        System.out.println("\n" + bold("Testing text fields"));
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("Testing text fields");
            frame.setBounds(X, Y, WIDTH, HEIGHT);
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
                if (boldFlag.isSelected()) {
                    mod += Font.BOLD;
                }
                if (italicFlag.isSelected()) {
                    mod += Font.ITALIC;
                }
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
                if (smallFont.isSelected()) {
                    area.setFont(new Font(fontName, mod, smallSize));
                } else if (normalFont.isSelected()) {
                    area.setFont(new Font(fontName, mod, FONT_SIZE));
                } else if (bigFont.isSelected()) {
                    area.setFont(new Font(fontName, mod, bigSize));
                }
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
            southPane.setPreferredSize(new Dimension(WIDTH, HEIGHT / 3));
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
    
}
