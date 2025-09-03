package druyaned.corejava.vol1.ch09collections.t05linlist.cmd;

import druyaned.corejava.vol1.ch09collections.t05linlist.LinkedList;
import druyaned.corejava.vol1.ch09collections.util.AbstractCommand;

public class Last extends AbstractCommand<LinkedList<Integer>> {
    
    public Last(LinkedList<Integer> target) {
        super(target);
    }
    
    @Override public String code() {
        return "lst";
    }
    
    @Override public String description() {
        return "Get last";
    }
    
    @Override public String format() {
        return "lst";
    }
    
    @Override public String run(String[] inputParts) {
        return target.last() == null
                ? "null"
                : target.last().value().toString();
    }
    
}
