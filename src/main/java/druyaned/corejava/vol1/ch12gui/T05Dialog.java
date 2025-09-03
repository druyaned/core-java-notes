package druyaned.corejava.vol1.ch12gui;

import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import druyaned.corejava.util.ScreenSize;
import druyaned.corejava.vol1.ch12gui.t05dialog.PaneConfirm;
import druyaned.corejava.vol1.ch12gui.t05dialog.PaneIcon;
import druyaned.corejava.vol1.ch12gui.t05dialog.Icons;
import druyaned.corejava.vol1.ch12gui.t05dialog.PaneMessageType;
import druyaned.corejava.vol1.ch12gui.t05dialog.PaneShow;
import druyaned.corejava.vol1.ch12gui.t05dialog.PaneTitle;
import druyaned.corejava.vol1.ch12gui.t05dialog.PaneType;
import druyaned.corejava.vol1.ch12gui.t05dialog.ShowListener;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class T05Dialog extends Topic {
    
    public T05Dialog(Chapter chapter) {
        super(chapter, 5);
    }
    
    @Override public String title() {
        return "Topic 05 Dialog";
    }
    
    @Override public void run() {
        Rectangle bounds = ScreenSize.get(0.75);
        EventQueue.invokeLater(() -> {
            // Creating and setting a frame
            JFrame frame = new JFrame("Testing dialog windows");
            frame.setBounds(bounds);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // Creating panes
            PaneType typePane = new PaneType();
            Icons paneIcons = new Icons(this);
            PaneIcon iconPane = new PaneIcon(paneIcons);
            PaneMessageType messageTypePane = new PaneMessageType();
            PaneConfirm confirmPane = new PaneConfirm();
            PaneTitle titlePane = new PaneTitle();
             // TextField to show previous input
            JTextField prevIn = new JTextField("Previous input.");
            prevIn.setEditable(false);
            // Creating a show pane and a listener for it
            ShowListener showListener = new ShowListener(
                    typePane, iconPane, messageTypePane,
                    confirmPane, titlePane, prevIn
            );
            PaneShow showPane = new PaneShow(showListener, prevIn);
            // Creating pane for button panes
            JPanel forButtonPanes = new JPanel(new GridLayout(2, 3));
            forButtonPanes.add(typePane);
            forButtonPanes.add(iconPane);
            forButtonPanes.add(messageTypePane);
            forButtonPanes.add(confirmPane);
            forButtonPanes.add(titlePane);
            forButtonPanes.add(showPane);
            // Adding all components to the frame
            if (!(frame.getLayout() instanceof BorderLayout)) {
                frame.setLayout(new BorderLayout());
            }
            frame.getContentPane().add(forButtonPanes, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
    
}
