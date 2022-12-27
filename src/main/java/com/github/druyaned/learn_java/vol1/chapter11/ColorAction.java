package com.github.druyaned.learn_java.vol1.chapter11;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JPanel;

public class ColorAction extends AbstractAction {
    private final JPanel panel;

    public ColorAction(JPanel panel, String name, Icon icon, Color color) {
        this.panel = panel;
        putValue(Action.NAME, name);
        putValue(Action.SMALL_ICON, icon);
        putValue(Action.SHORT_DESCRIPTION, "Setting background color");
        putValue("color", color);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panel.setBackground((Color) getValue("color"));
    }
}
