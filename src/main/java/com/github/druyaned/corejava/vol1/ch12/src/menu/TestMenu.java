package com.github.druyaned.corejava.vol1.ch12.src.menu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import static com.github.druyaned.ConsoleColors.bold;

public class TestMenu implements Runnable {
    
    public static final int WIDTH;
    public static final int HEIGHT;
    public static final int X;
    public static final int Y;
    private static final float HEIGHT_WIDTH_RATIO = 0.75F;
    private static final int SCREEN_PART = 2;
    
    static {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension d = toolkit.getScreenSize();
        WIDTH = (int)(d.getWidth() / SCREEN_PART);
        HEIGHT = (int)(WIDTH * HEIGHT_WIDTH_RATIO);
        X = (int)(d.getWidth() / 2) - WIDTH / 2;
        Y = (int)(d.getHeight() / 2) - HEIGHT / 2;
    }

    public static final int FONT_SIZE = 18;
    public static final Font FONT = new Font(Font.MONOSPACED, Font.PLAIN, FONT_SIZE);
    public static final int L = (int)(WIDTH / 2) - 2;

    @Override public void run() {
        System.out.println("\n" + bold("Testing menu"));
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("Testing menu");
            frame.setBounds(X, Y, WIDTH, HEIGHT);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            JTextArea area = new JTextArea("Default text.");
            area.setFont(FONT);
            area.setPreferredSize(new Dimension(WIDTH - 2, HEIGHT / 2));
            Action copyAction = new DefaultEditorKit.CopyAction();
            Action pasteAction = new DefaultEditorKit.PasteAction();
            JMenuItem copyItem = new JMenuItem(copyAction);
            copyItem.setText("Copy");
            KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.META_DOWN_MASK);
            copyItem.setAccelerator(ks);
            copyItem.setMnemonic('C');
            copyItem.setDisplayedMnemonicIndex(0);
            JMenuItem pasteItem = new JMenuItem(pasteAction);
            pasteItem.setText("Paste");
            ks = KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.META_DOWN_MASK);
            pasteItem.setAccelerator(ks);
            pasteItem.setMnemonic('P');
            pasteItem.setDisplayedMnemonicIndex(0);
            JButton copyButton = new JButton(copyAction);
            JButton pasteButton = new JButton(pasteAction);
            copyButton.setText("Copy");
            copyButton.setToolTipText("Copy to clipboard");
            pasteButton.setText("Paste");
            pasteButton.setToolTipText("Paste from clipboard");
            JPopupMenu popup = new JPopupMenu();
            JCheckBoxMenuItem popupEditable = new JCheckBoxMenuItem("Editable", true);
            JMenuItem popupCopyItem = new JMenuItem(copyAction);
            JMenuItem popupPasteItem = new JMenuItem(pasteAction);
            popupCopyItem.setText("Copy");
            popupPasteItem.setText("Paste");
            class EditListener implements ActionListener {
                private final AbstractButton button;           
                public EditListener(AbstractButton button) {
                    this.button = button;
                }
                @Override public void actionPerformed(ActionEvent event) {
                    if (button.isSelected()) {
                        area.setEditable(true);
                        pasteItem.setEnabled(true);
                        popupPasteItem.setEnabled(true);
                        pasteButton.setEnabled(true);
                    }
                    else {
                        area.setEditable(false);
                        pasteItem.setEnabled(false);
                        popupPasteItem.setEnabled(false);
                        pasteButton.setEnabled(false);
                    }
                }
            }
            popupEditable.addActionListener(new EditListener(popupEditable));
            popup.add(popupEditable);
            popup.addSeparator();
            popup.add(popupCopyItem);
            popup.add(popupPasteItem);
            JCheckBox editableBox = new JCheckBox("Editable", true);
            editableBox.addActionListener(new EditListener(editableBox));
            JToolBar toolBar = new JToolBar("Editor", JToolBar.HORIZONTAL);
            toolBar.add(editableBox);
            toolBar.add(copyButton);
            toolBar.add(pasteButton);
            JCheckBoxMenuItem editable = new JCheckBoxMenuItem("Editable", true);
            editable.addActionListener(new EditListener(editable));
            ks = KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.META_DOWN_MASK);
            editable.setAccelerator(ks);
            JMenu editMenu = new JMenu("Edit");
            editMenu.add(editable);
            editMenu.addSeparator();
            editMenu.add(copyItem);
            editMenu.add(pasteItem);
            JMenuBar menuBar = new JMenuBar();
            menuBar.add(editMenu);
            area.setComponentPopupMenu(popup);
            frame.add(area, BorderLayout.CENTER);
            frame.add(toolBar, BorderLayout.NORTH);
            frame.setJMenuBar(menuBar);
            frame.setVisible(true);
        });
    }
    
}
