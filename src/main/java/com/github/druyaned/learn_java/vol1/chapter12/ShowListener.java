package com.github.druyaned.learn_java.vol1.chapter12;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import static javax.swing.JOptionPane.*;

public class ShowListener implements ActionListener {
    private PaneType typePane;
    private PaneIcon iconPane;
    private PaneMessageType messageTypePane;
    private PaneConfirm confirmPane;
    private PaneTitle titlePane;
    private JTextField prevIn;

    public ShowListener(PaneType typePane, PaneIcon iconPane,
                        PaneMessageType messageTypePane, PaneConfirm confirmPane,
                        PaneTitle titlePane, JTextField prevOut) {
        this.typePane = typePane;
        this.iconPane = iconPane;
        this.messageTypePane = messageTypePane;
        this.confirmPane = confirmPane;
        this.titlePane = titlePane;
        this.prevIn = prevOut;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String iconKey = iconPane.getSelectedKey();
        ImageIcon imageIcon = null;
        if (!iconKey.equals(PaneIcon.NONE)) {
            imageIcon = iconPane.getImageIcon(iconKey);
        }
        String title = titlePane.getTitle();
        int option = confirmPane.getSelectedOption();
        int messageType = messageTypePane.getSelectedMessageType();
        
        if (typePane.isSelected(PaneType.CONFIRM)) {
            String message = "Are we confirming something?";
            Component parentComponent = null;
            int in;
            in = showConfirmDialog(parentComponent, message,
                                   title, option, messageType, imageIcon);
            prevIn.setText("Previous input: " + getOption(in));

        } else if (typePane.isSelected(PaneType.INPUT)) {
            String message = "Let's do some input!";
            String[] toChoose = new String[] { "Variant A", "Variant B" };
            Object in = showInputDialog(null, message, title,
                                        messageType, imageIcon, toChoose, toChoose[0]);
            prevIn.setText("Previous input: " + in);

        } else if (typePane.isSelected(PaneType.MESSAGE)) {
            String message = "Here is some kind of message.";
            showMessageDialog(null, message, title, messageType, imageIcon);
            prevIn.setText("Hmm... Previous input couldn't be seen.");
            
        } else if (typePane.isSelected(PaneType.OPTION)) {
            String message = "Here is some kind of message.";
            String[] toChoose = new String[] { "Variant 1", "Variant 2", "Variant 3" };
            int in = showOptionDialog(null, message, title, option,
                                      messageType, imageIcon, toChoose, toChoose[0]);
            if (in == -1) { prevIn.setText("Previous input: CLOSE_OPTION"); }
            else { prevIn.setText("Previous input: " + toChoose[in]); }
        }
    }

    public static String getOption(int in) {
        if (in == JOptionPane.YES_OPTION) { return "YES_OPTION"; }
        else if (in == JOptionPane.NO_OPTION) { return "NO_OPTION"; }
        else if (in == JOptionPane.OK_OPTION) { return "OK_OPTION"; }
        else if (in == JOptionPane.CANCEL_OPTION) { return "CANCEL_OPTION"; }
        else { return "DEFAULT_OPTION"; }
    }
}
