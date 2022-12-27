package com.github.druyaned.learn_java.vol1.chapter12;

import java.awt.Window;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class CloseAction extends AbstractAction {
    private Window window;

    public CloseAction(Window window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        window.dispose();
    }
}