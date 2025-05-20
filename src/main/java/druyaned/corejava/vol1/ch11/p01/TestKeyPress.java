package druyaned.corejava.vol1.ch11.p01;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class TestKeyPress implements Runnable {
    
    @Override public void run() {
        EventQueue.invokeLater(() -> {
            // Frame
            final JFrame frame = new JFrame("TestKeyPress");
            halfScreenSizeAndCentered(frame);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // Panel
            int gap = 20;
            JPanel panel = new JPanel(new BorderLayout(gap, gap));
            // Label for key press tracking
            final Label outputLabel = new Label("Nothing has been pressed yet", Label.CENTER);
            outputLabel.setFont(new Font("Monospaced", Font.PLAIN, 24));
            panel.add(outputLabel, BorderLayout.CENTER);
            // Add key listener to track key pressing
            requestFocus(frame, panel);
            addKeyAdapterToTrackPressing(panel, outputLabel);
            // Exit on [W + META] key binding
            addExitOnWPlusMetaAction(panel);
            // Add panel
            frame.getContentPane().add(panel);
            frame.setVisible(true);
        });
    }
    
    private static void halfScreenSizeAndCentered(JFrame frame) {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int frameWidth = screenSize.width / 2;
        int frameHeight = screenSize.height / 2;
        int frameX = screenSize.width / 4;
        int frameY = screenSize.height / 4;
        frame.setSize(new Dimension(frameWidth, frameHeight));
        frame.setLocation(frameX, frameY);
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
    
    private static void requestFocus(JFrame frame, JPanel panel) {
        frame.addWindowFocusListener(new WindowAdapter() {
            @Override public void windowGainedFocus(WindowEvent event) {
                panel.requestFocusInWindow();
            }
        });
    }
    
    private static void addKeyAdapterToTrackPressing(JPanel panel, Label outputLabel) {
        KeyAdapter trackPressingAdapter = new KeyAdapter() {
            @Override public void keyPressed(KeyEvent event) {
                String text = "Last pressed key: '" + event.getKeyChar() + "'";
                outputLabel.setText(text);
            }
        };
        panel.addKeyListener(trackPressingAdapter);
    }
    
}
