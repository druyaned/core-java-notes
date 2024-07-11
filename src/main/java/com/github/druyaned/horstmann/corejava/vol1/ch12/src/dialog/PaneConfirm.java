package com.github.druyaned.horstmann.corejava.vol1.ch12.src.dialog;

import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class PaneConfirm extends PaneButton {
    
    public static final String DEFAULT_OPTION = "DEFAULT_OPTION";
    public static final String YES_NO_OPTION = "YES_NO_OPTION";
    public static final String YES_NO_CANCEL_OPTION = "YES_NO_CANCEL_OPTION";
    public static final String OK_CANCEL_OPTION = "OK_CANCEL_OPTION";
    private static final Map<String, JRadioButton> buttons = new HashMap<>();
    
    static {
        buttons.put(DEFAULT_OPTION, new JRadioButton(DEFAULT_OPTION));
        buttons.put(YES_NO_OPTION, new JRadioButton(YES_NO_OPTION));
        buttons.put(YES_NO_CANCEL_OPTION, new JRadioButton(YES_NO_CANCEL_OPTION));
        buttons.put(OK_CANCEL_OPTION, new JRadioButton(OK_CANCEL_OPTION));
    }

    public PaneConfirm() {
        super(
                "Confirm pane",
                buttons.get(OK_CANCEL_OPTION).getModel(),
                buttons,
                new String[] {
                    DEFAULT_OPTION,
                    YES_NO_OPTION,
                    YES_NO_CANCEL_OPTION,
                    OK_CANCEL_OPTION
                }
        );
    }

    public int getSelectedOption() {
        if (isSelected(YES_NO_OPTION)) {
            return JOptionPane.YES_NO_OPTION;
        } else if (isSelected(YES_NO_CANCEL_OPTION)) {
            return JOptionPane.YES_NO_CANCEL_OPTION;
        } else if (isSelected(OK_CANCEL_OPTION)) {
            return JOptionPane.OK_CANCEL_OPTION;
        } else {
            return JOptionPane.DEFAULT_OPTION;
        }
    }
}
