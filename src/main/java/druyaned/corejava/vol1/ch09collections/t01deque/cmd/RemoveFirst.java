package druyaned.corejava.vol1.ch09collections.t01deque.cmd;

import druyaned.corejava.vol1.ch09collections.util.AbstractCommand;
import druyaned.corejava.vol1.ch09collections.t01deque.DequeInt;

public class RemoveFirst extends AbstractCommand<DequeInt> {
    
    public RemoveFirst(DequeInt deque) {
        super(deque);
    }
    
    @Override public String code() {
        return "rmf";
    }
    
    @Override public String description() {
        return "Remove first";
    }
    
    @Override public String format() {
        return "rmf";
    }
    
    @Override public String run(String[] inputParts) {
        return Integer.toString(target.removeFirst());
    }
    
}
