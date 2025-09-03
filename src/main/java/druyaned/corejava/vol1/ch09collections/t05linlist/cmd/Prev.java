package druyaned.corejava.vol1.ch09collections.t05linlist.cmd;

import druyaned.corejava.vol1.ch09collections.t05linlist.LinkedList;
import druyaned.corejava.vol1.ch09collections.util.AbstractCommand;

public class Prev extends AbstractCommand<LinkedList<Integer>> {
    
    public Prev(LinkedList<Integer> target) {
        super(target);
    }
    
    @Override public String code() {
        return "prv";
    }
    
    @Override public String description() {
        return "Get previous";
    }
    
    @Override public String format() {
        return "prv";
    }
    
    @Override public String run(String[] inputParts) {
        return target.iterator().prev() == null
                ? "null"
                : target.iterator().prev().value().toString();
    }
    
}
