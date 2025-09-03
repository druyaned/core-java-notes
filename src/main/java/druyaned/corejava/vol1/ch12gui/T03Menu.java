package druyaned.corejava.vol1.ch12gui;

import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import druyaned.corejava.util.ScreenSize;
import javax.swing.text.DefaultEditorKit;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

public class T03Menu extends Topic {
    
    public T03Menu(Chapter chapter) {
        super(chapter, 3);
    }
    
    @Override public String title() {
        return "Topic 03 Menu";
    }
    
    @Override public void run() {
        Rectangle bounds = ScreenSize.get(0.5);
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("Testing menu");
            frame.setBounds(bounds);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            JTextArea area = new JTextArea("Default text.");
            area.setFont(FONT);
            area.setPreferredSize(new Dimension(bounds.width - 2, bounds.height / 2));
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
    
    public static final int FONT_SIZE = 18;
    public static final Font FONT = new Font(Font.MONOSPACED, Font.PLAIN, FONT_SIZE);
    
}
