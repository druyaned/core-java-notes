package com.github.druyaned.horstmann.corejava.vol1.ch12.src.dialog;

import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class PaneType extends PaneButton {
    
    public static final String CONFIRM = "Confirm";
    public static final String INPUT = "Input";
    public static final String MESSAGE = "Message";
    public static final String OPTION = "Option";
    private static final Map<String, JRadioButton> buttons = new HashMap<>();
    
    static {
        buttons.put(CONFIRM, new JRadioButton(CONFIRM));
        buttons.put(INPUT, new JRadioButton(INPUT));
        buttons.put(MESSAGE, new JRadioButton(MESSAGE));
        buttons.put(OPTION, new JRadioButton(OPTION));
    }

    public PaneType() {
        super(
                "Type",
                buttons.get(MESSAGE).getModel(),
                buttons,
                new String[] { CONFIRM, INPUT, MESSAGE, OPTION }
        );
    }
    
}
