package druyaned.corejava.vol1.ch09collections.t01deque.cmd;

import druyaned.corejava.vol1.ch09collections.util.AbstractCommand;
import druyaned.corejava.vol1.ch09collections.t01deque.DequeInt;

public class GetFirst extends AbstractCommand<DequeInt> {
    
    public GetFirst(DequeInt deque) {
        super(deque);
    }
    
    @Override public String code() {
        return "gtf";
    }
    
    @Override public String description() {
        return "Get first";
    }
    
    @Override public String format() {
        return "gtf";
    }
    
    @Override public String run(String[] inputParts) {
        return Integer.toString(target.getFirst());
    }
    
}
