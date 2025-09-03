package druyaned.corejava.vol1.ch12gui.t01calc.gui;

import druyaned.corejava.vol1.ch12gui.t01calc.calc.CalcMediator;
import druyaned.corejava.vol1.ch12gui.t01calc.calc.Calculator;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class CalcPanel extends JPanel {
    
    private final Calculator calc;
    private final CalcMediator mediator;
    private final DisplayPanel displayPanel;
    private final ButtonPanel buttonPanel;
    
    public CalcPanel() {
        super();
        GridBagLayout layout = new GridBagLayout();
        GBC displaysGbc = new GBC(0, 0, 1, 1)
                .setFill(GBC.BOTH)
                .setWeight(0.25, 0.25);
        GBC buttonsGbc = new GBC(0, 1, 1, 1)
                .setFill(GBC.BOTH)
                .setWeight(1d, 1d);
        setLayout(layout);
        calc = new Calculator();
        displayPanel = new DisplayPanel(calc);
        mediator = new GuiMediator(calc, displayPanel);
        buttonPanel = new ButtonPanel(calc, mediator);
        add(displayPanel, displaysGbc);
        add(buttonPanel, buttonsGbc);
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        // Additional actions
        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();
        int acceleratorKey = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();
        addAction(
                inputMap,
                actionMap,
                () -> System.exit(0),
                "exit.on.w.plus.meta",
                KeyEvent.VK_W,
                acceleratorKey
        );
        addAction(
                inputMap,
                actionMap,
                () -> Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
                        new StringSelection(displayPanel.accumField().getText()),
                        null
                ),
                "copy.accum",
                KeyEvent.VK_C,
                acceleratorKey
        );
    }
    
    private void addAction(
            InputMap inputMap,
            ActionMap actionMap,
            Runnable execution,
            String actionKey,
            int keyCode,
            int modifiers
    ) {
        AbstractAction action = new AbstractAction() {
            @Override public void actionPerformed(ActionEvent event) {
                execution.run();
            }
        };
        inputMap.put(KeyStroke.getKeyStroke(keyCode, modifiers), actionKey);
        actionMap.put(actionKey, action);
    }
    
    public static final int PANEL_WIDTH = 480;
    public static final int PANEL_HEIGHT = 600;
    
}
