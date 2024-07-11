package com.github.druyaned.horstmann.corejava.vol1.ch12.src.user;

import java.awt.Window;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class CloseAction extends AbstractAction {
    
    private final Window window;

    public CloseAction(Window window) {
        this.window = window;
    }

    @Override public void actionPerformed(ActionEvent event) {
        window.dispose();
    }
    
}
