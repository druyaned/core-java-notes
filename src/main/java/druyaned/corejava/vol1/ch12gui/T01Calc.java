package druyaned.corejava.vol1.ch12gui;

import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import druyaned.corejava.vol1.ch12gui.t01calc.calc.CalcMediator;
import druyaned.corejava.vol1.ch12gui.t01calc.calc.CalcMemento;
import druyaned.corejava.vol1.ch12gui.t01calc.calc.Calculator;
import druyaned.corejava.vol1.ch12gui.t01calc.calc.InputField;
import druyaned.corejava.vol1.ch12gui.t01calc.cmd.CalcCommand;
import druyaned.corejava.vol1.ch12gui.t01calc.gui.CalcAction;
import druyaned.corejava.vol1.ch12gui.t01calc.gui.CalcFrame;
import java.awt.EventQueue;

/**
 * The project is an app of {@link Calculator calculator}
 * with usage of some design patterns.
 * 
 * <P>Design Pattern implementations:<ul>
 *   <li>{@link CalcMediator} - Mediator Pattern;</li>
 *   <li>{@link CalcMemento} - Memento Pattern;</li>
 *   <li>{@link InputField} - Prototype Pattern;</li>
 *   <li>{@link CalcCommand} - Command Pattern;</li>
 *   <li>{@link CalcAction} - Adapter Pattern.</li>
 * </ul>
 * 
 * @author druyaned
 */
public class T01Calc extends Topic {
    
    public T01Calc(Chapter chapter) {
        super(chapter, 1);
    }
    
    @Override public String title() {
        return "Topic 01 Calc";
    }
    
    @Override public void run() {
        EventQueue.invokeLater(() -> new CalcFrame().setVisible(true));
    }
    
}
