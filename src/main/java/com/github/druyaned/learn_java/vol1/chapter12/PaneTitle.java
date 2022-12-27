package com.github.druyaned.learn_java.vol1.chapter12;

import java.awt.*;

import javax.swing.*;

public class PaneTitle extends PaneStyled {
    private JTextField titleField;
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
                                .setWeight(100, 100);
        northPane.add(titleLabel, titleLabelGBC);
        northPane.add(titleField, titleFieldGBC);
        setLayout(new BorderLayout());
        add(northPane, BorderLayout.NORTH);
    }

    public String getTitle() { return titleField.getText(); }
}
