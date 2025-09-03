package druyaned.corejava.vol1.ch09collections.t05linlist.cmd;

import druyaned.corejava.vol1.ch09collections.t05linlist.LinkedList;
import druyaned.corejava.vol1.ch09collections.util.AbstractCommand;

public class Next extends AbstractCommand<LinkedList<Integer>> {
    
    public Next(LinkedList<Integer> target) {
        super(target);
    }
    
    @Override public String code() {
        return "nxt";
    }
    
    @Override public String description() {
        return "Get next";
    }
    
    @Override public String format() {
        return "nxt";
    }
    
    @Override public String run(String[] inputParts) {
        return target.iterator().next() == null
                ? "null"
                : target.iterator().next().value().toString();
    }
    
}
