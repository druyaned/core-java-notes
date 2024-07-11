package com.github.druyaned.horstmann.corejava.vol1.ch12.src.dialog;

import java.awt.*;
import javax.swing.*;

public class PaneShow extends PaneStyled {
    
    public PaneShow(ShowListener showListener, JTextField prevIn) {
        super("Show button");
        setLayout(new GridLayout(2, 1));
        JButton showButton = new JButton("Show");
        showButton.addActionListener(showListener);
        Font showFont = new Font(Font.MONOSPACED, Font.PLAIN, 32);
        showButton.setFont(showFont);
        add(showButton);
        add(prevIn);
    }
    
}
