package com.github.druyaned.learn_java.vol1.chapter12;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

public class PaneStyled extends JPanel {
    public static final Font TITLE_FONT = new Font(Font.MONOSPACED, Font.PLAIN, 16);
    public static final Color HIGHLIGHTS = new Color(192, 192, 255);
    public static final Color SHADOWS = new Color(128, 128, 192);
    public static final Color BACKGROUND = new Color(224, 240, 224);

    public PaneStyled(String title) {
        Border border = BorderFactory.createTitledBorder(BorderFactory
               .createEtchedBorder(EtchedBorder.RAISED, HIGHLIGHTS, SHADOWS),
               title, TitledBorder.RIGHT, TitledBorder.TOP);
        TitledBorder tBorder = (TitledBorder) border;
        tBorder.setTitleFont(TITLE_FONT);
        tBorder.setTitleColor(Color.DARK_GRAY);
        setBorder(border);
        setBackground(BACKGROUND);
    }
}
