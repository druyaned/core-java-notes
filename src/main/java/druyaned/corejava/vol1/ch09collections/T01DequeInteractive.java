package druyaned.corejava.vol1.ch09collections;

import druyaned.corejava.Chapter;
import druyaned.corejava.vol1.ch09collections.t01deque.DequeInt;
import druyaned.corejava.vol1.ch09collections.t01deque.PrinterDequeInt;
import druyaned.corejava.vol1.ch09collections.t01deque.cmd.AddFirst;
import druyaned.corejava.vol1.ch09collections.t01deque.cmd.AddLast;
import druyaned.corejava.vol1.ch09collections.t01deque.cmd.Clear;
import druyaned.corejava.vol1.ch09collections.t01deque.cmd.Get;
import druyaned.corejava.vol1.ch09collections.t01deque.cmd.GetFirst;
import druyaned.corejava.vol1.ch09collections.t01deque.cmd.GetLast;
import druyaned.corejava.vol1.ch09collections.t01deque.cmd.RemoveFirst;
import druyaned.corejava.vol1.ch09collections.t01deque.cmd.RemoveLast;
import druyaned.corejava.vol1.ch09collections.t01deque.cmd.Set;
import druyaned.corejava.vol1.ch09collections.util.Command;
import druyaned.corejava.vol1.ch09collections.util.InteractiveTopic;

public class T01DequeInteractive extends InteractiveTopic<DequeInt> {
    
    public T01DequeInteractive(Chapter chapter) {
        super(chapter, 1);
    }
    
    @Override public String title() {
        return "Topic 01 DequeInteractive";
    }
    
    @Override protected DequeInt createTarget() {
        return new DequeInt(2);
    }
    
    @Override protected void addCommands(DequeInt target) {
        Command command;
        commands.put((command = new Get(target)).code(), command);
        commands.put((command = new GetFirst(target)).code(), command);
        commands.put((command = new GetLast(target)).code(), command);
        commands.put((command = new Set(target)).code(), command);
        commands.put((command = new Clear(target)).code(), command);
        commands.put((command = new AddFirst(target)).code(), command);
        commands.put((command = new AddLast(target)).code(), command);
        commands.put((command = new RemoveFirst(target)).code(), command);
        commands.put((command = new RemoveLast(target)).code(), command);
    }
    
    @Override protected void setPrinter(DequeInt target) {
        printer = new PrinterDequeInt(target, "_", null);
    }
    
}
