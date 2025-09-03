package druyaned.corejava.vol1.ch09collections.t01deque.cmd;

import druyaned.corejava.vol1.ch09collections.util.AbstractCommand;
import druyaned.corejava.vol1.ch09collections.t01deque.DequeInt;

public class GetLast extends AbstractCommand<DequeInt> {
    
    public GetLast(DequeInt deque) {
        super(deque);
    }
    
    @Override public String code() {
        return "gtl";
    }
    
    @Override public String description() {
        return "Get last";
    }
    
    @Override public String format() {
        return "gtl";
    }
    
    @Override public String run(String[] inputParts) {
        return Integer.toString(target.getLast());
    }
    
}
