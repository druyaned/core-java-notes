package druyaned.corejava.vol1.ch12.src.dialog;

import java.awt.*;
import javax.swing.*;
import static druyaned.ConsoleColors.bold;

public class TestDialog implements Runnable {
    
    public static final int WIDTH;
    public static final int HEIGHT;
    public static final int X;
    public static final int Y;
    private static final double SCREEN_PART = 1.5;
    
    static {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension d = toolkit.getScreenSize();
        WIDTH = (int)(d.getWidth() / SCREEN_PART);
        HEIGHT = (int)(d.getHeight() / SCREEN_PART);
        X = (int)(d.getWidth() / 2) - (int)(WIDTH / 2);
        Y = (int)(d.getHeight() / 2) - (int)(HEIGHT / 2);
    }

    @Override public void run() {
        System.out.println("\n" + bold("Testing dialog windows"));
        EventQueue.invokeLater(() -> {
            // Creating and setting a frame
            JFrame frame = new JFrame("Testing dialog windows");
            frame.setBounds(X, Y, WIDTH, HEIGHT);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // Creating panes
            PaneType typePane = new PaneType();
            PaneIcon iconPane = new PaneIcon();
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
