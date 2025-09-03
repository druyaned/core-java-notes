package druyaned.corejava.vol1.ch12gui.t05dialog;

import druyaned.corejava.vol1.ch12gui.t04gbc.GBC;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PaneTitle extends PaneStyled {
    
    private final JTextField titleField;
    
    public PaneTitle() {
        super("Title");
        titleField = new JTextField("Dialog title", 32);
        JLabel titleLabel = new JLabel("Title");
        titleLabel.setHorizontalAlignment(JLabel.RIGHT);
        PaneStyled northPane = new PaneStyled("Setting title");
        northPane.setLayout(new GridBagLayout());
        GBC titleLabelGBC = new GBC(0, 0, 1, 1);
        GBC titleFieldGBC = new GBC(1, 0, 2, 1)
                .setFill(GBC.HORIZONTAL)
                .setWeight(1d, 1d);
        northPane.add(titleLabel, titleLabelGBC);
        northPane.add(titleField, titleFieldGBC);
        setLayout(new BorderLayout());
        add(northPane, BorderLayout.NORTH);
    }
    
    public String getTitle() {
        return titleField.getText();
    }
    
}
