package druyaned.corejava.vol1.ch14.p01;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class TestBounce implements Runnable {
    
    public static final int BALL_COUNT = 8;
    public static final double BALL_DIAMETER = 32;
    public static final long DELAY_MILLIS = 4L; // 60 Hz -> 16.(6) millis
    
    public static final int PANEL_WIDTH;
    public static final int PANEL_HEIGHT;
    public static final int FRAME_X;
    public static final int FRAME_Y;
    
    static {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        PANEL_WIDTH = screenSize.width / 2;
        PANEL_HEIGHT = screenSize.height / 2;
        FRAME_X = screenSize.width / 4;
        FRAME_Y = screenSize.height / 4;
    }
    
    @Override public void run() {
        EventQueue.invokeLater(() -> {
            // Frame
            final JFrame frame = new JFrame("Concurrent Balls");
            frame.setLocation(FRAME_X, FRAME_Y);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // BallPanel
            BallPanel ballPanel = new BallPanel(
                    PANEL_WIDTH,
                    PANEL_HEIGHT,
                    BALL_COUNT,
                    BALL_DIAMETER
            );
            // Exit on [W + META] key binding
            addExitOnWPlusMetaAction(ballPanel);
            // Bounce, main actions
            bounce(ballPanel);
            // Add panel
            frame.getContentPane().add(ballPanel);
            frame.pack();
            frame.setVisible(true);
        });
    }
    
    private static void addExitOnWPlusMetaAction(JPanel panel) {
        AbstractAction exitOnWPlusMetaAction = new AbstractAction() {
            @Override public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        };
        String exitOnWPlusMetaKey = "exit.on.w.plus.meta";
        InputMap inputMap = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = panel.getActionMap();
        inputMap.put(
                KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.META_DOWN_MASK),
                exitOnWPlusMetaKey
        );
        actionMap.put(exitOnWPlusMetaKey, exitOnWPlusMetaAction);
    }
    
    private static void bounce(BallPanel ballPanel) {
        // Ball Movement Threads
        for (int i = 0; i < BALL_COUNT; i++) {
            final Ball ball = ballPanel.ballAt(i);
            new Thread(() -> {
                try {
                    while (true) {
                        // Ball movement calculation
                        ball.move(ballPanel.getPreferredSize());
                        Thread.sleep(DELAY_MILLIS);
                    }
                } catch (InterruptedException exc) {
                    throw new IllegalStateException(exc);
                }
            }).start();
        }
        // Repainter
        new Thread(() -> {
            try {
                while (true) {
                    // Repainting
                    EventQueue.invokeLater(() -> ballPanel.repaint());
                    Thread.sleep(DELAY_MILLIS);
                }
            } catch (InterruptedException exc) {
                throw new IllegalStateException(exc);
            }
        }).start();
    }
    
}
