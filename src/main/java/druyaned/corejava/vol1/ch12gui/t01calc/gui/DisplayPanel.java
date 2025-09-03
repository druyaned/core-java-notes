package druyaned.corejava.vol1.ch12gui.t01calc.gui;

import druyaned.corejava.vol1.ch12gui.t01calc.calc.Calculator;
import java.awt.Font;
import static java.awt.Font.MONOSPACED;
import static java.awt.Font.PLAIN;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DisplayPanel extends JPanel {
    
    private final JLabel accumLabel;
    private final JLabel inputLabel;
    private final JTextField accumField;
    private final JTextField inputField;
    
    public DisplayPanel(Calculator calc) {
        super();
        setLayout(new GridBagLayout());
        accumLabel = new JLabel("Accumulator", JLabel.RIGHT);
        inputLabel = new JLabel("Input Field", JLabel.RIGHT);
        accumField = new JTextField(Double.toString(calc.accumulator()));
        inputField = new JTextField();
        // Set up labels
        accumLabel.setFont(LABEL_FONT);
        inputLabel.setFont(LABEL_FONT);
        // Set up text fields
        accumField.setEditable(false);
        accumField.setFocusable(false);
        accumField.setFont(FIELD_FONT);
        inputField.setEditable(false);
        inputField.setFocusable(false);
        inputField.setFont(FIELD_FONT);
        // GBC
        double labelWeight = 0.20;
        GBC accumLabelGbc = new GBC(0, 0, 1, 1)
                .setFill(GBC.BOTH)
                .setWeight(labelWeight, labelWeight)
                .setInsets(0, 8, 0, 0);
        GBC inputLabelGbc = new GBC(0, 1, 1, 1)
                .setFill(GBC.BOTH)
                .setWeight(labelWeight, labelWeight)
                .setInsets(0, 8, 0, 0);
        GBC accumFieldGbc = new GBC(1, 0, 1, 1)
                .setFill(GBC.BOTH)
                .setWeight(1d, 1d);
        GBC inputFieldGbc = new GBC(1, 1, 1, 1)
                .setFill(GBC.BOTH)
                .setWeight(1d, 1d);
        // Addition
        add(accumLabel, accumLabelGbc);
        add(inputLabel, inputLabelGbc);
        add(accumField, accumFieldGbc);
        add(inputField, inputFieldGbc);
    }
    
    public JLabel accumLabel() {
        return accumLabel;
    }
    
    public JLabel inputLabel() {
        return inputLabel;
    }
    
    public JTextField accumField() {
        return accumField;
    }
    
    public JTextField inputField() {
        return inputField;
    }
    
    public static final Font LABEL_FONT = new Font(MONOSPACED, PLAIN, 16);
    public static final Font FIELD_FONT = new Font(MONOSPACED, PLAIN, 18);
    
}
