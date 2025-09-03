package druyaned.corejava.vol1.ch09collections.t01deque.cmd;

import druyaned.corejava.vol1.ch09collections.util.AbstractCommand;
import druyaned.corejava.vol1.ch09collections.t01deque.DequeInt;

public class RemoveLast extends AbstractCommand<DequeInt> {
    
    public RemoveLast(DequeInt deque) {
        super(deque);
    }
    
    @Override public String code() {
        return "rml";
    }
    
    @Override public String description() {
        return "Remove last";
    }
    
    @Override public String format() {
        return "rml";
    }
    
    @Override public String run(String[] inputParts) {
        return Integer.toString(target.removeLast());
    }
    
}
