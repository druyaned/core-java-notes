package com.github.druyaned.learn_java.vol1.chapter12;

import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;

public enum CalcCommand {
    ALL_CLEAR("AC", "All clear", VK_ESCAPE, 0, "allClear"),
    EQUALITY("=", "Equality", VK_ENTER, 0, "equals"),
    DIVISION("÷", "Division", VK_SLASH, 0, "division"),
    MULTIPLICATION("×", "Multiplication", VK_8, SHIFT_DOWN_MASK, "multiply"),
    DIFFERENCE("-", "Difference", VK_MINUS, 0, "minus"),
    SUM("+", "Sum", VK_EQUALS, SHIFT_DOWN_MASK, "plus"),
    DOT(".", "Dot", VK_PERIOD, 0, "dot"),
    BACKSPACE("⬅", "Backspace", VK_BACK_SPACE, 0, "backspace");

    public static final String NONE = "no action";

    private String text;
    private String description;
    private int keyCode;
    private int mod;
    private String keyText;
    private String mapKey;

    private CalcCommand(String txt, String descr, int kCode, int mod, String mKey) {
        this.text = txt;
        this.description = descr;
        this.keyCode = kCode;
        this.mod = mod;
        keyText = KeyEvent.getKeyText(kCode);
        this.mapKey = mKey;
    }

    public String getText() {return text;}
    public String getDescription() {return description;}
    public int getKeyCode() {return keyCode;}
    public int getMod() {return mod;}
    public String getKeyText() {return keyText;}
    public String getMapKey() {return mapKey;}
}
