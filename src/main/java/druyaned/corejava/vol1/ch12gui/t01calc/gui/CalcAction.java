package druyaned.corejava.vol1.ch12gui.t01calc.gui;

import druyaned.corejava.vol1.ch12gui.t01calc.cmd.CalcCommand;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;

public class CalcAction extends AbstractAction {
    
    private final CalcCommand command;
    private final String text;
    private final String desc;
    private final int keyCode;
    private final int mod;
    private final String mapKey;
    
    public CalcAction(
            CalcCommand command,
            String text,
            String desc,
            int keyCode,
            int mod,
            String mapKey
    ) {
        this.command = command;
        this.text = text;
        this.desc = desc;
        this.keyCode = keyCode;
        this.mod = mod;
        this.mapKey = mapKey;
        putValue(Action.NAME, text);
        putValue(Action.SHORT_DESCRIPTION, desc);
    }
    
    @Override public void actionPerformed(ActionEvent event) {
        command.execute();
    }
    
    public CalcAction putInto(InputMap inputMap, ActionMap actionMap) {
        KeyStroke keyStroke = KeyStroke.getKeyStroke(keyCode, mod);
        inputMap.put(keyStroke, mapKey);
        actionMap.put(mapKey, this);
        return this;
    }
    
    public String text() {
        return text;
    }
    
    public String desc() {
        return desc;
    }
    
    public int keyCode() {
        return keyCode;
    }
    
    public int mod() {
        return mod;
    }
    
    public String mapKey() {
        return mapKey;
    }
    
}
