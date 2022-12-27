package com.github.druyaned.learn_java.vol1.chapter12;

import java.awt.*;
import java.util.Map;

import javax.swing.*;

public class PaneButton extends PaneStyled {
    private ButtonGroup group;
    private Map<String, JRadioButton> buttons;

    /**
     * NOTES:<p>
     * 1) sets {@code new GridLayout()};<p>
     * 2) buttons's map initialized by the parameter.
     */
    public PaneButton(String title,
                      ButtonModel toSelect, Map<String, JRadioButton> buttons, String[] keys) {
        
        super(title);
        this.buttons = buttons;
        setLayout(new GridLayout(buttons.size(), 1));
        group = new ButtonGroup();
        for (String key : keys) {
            JRadioButton button = buttons.get(key);
            group.add(button);
            add(button);
        }
        if (toSelect != null) {
            group.setSelected(toSelect, true);
        }
    }

    public ButtonModel getSelection() { return group.getSelection(); }

    public JRadioButton getButton(String key) { return buttons.get(key); }

    public boolean isSelected(String key) {
        return group.isSelected(buttons.get(key).getModel());
    }
}
