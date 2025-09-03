package druyaned.corejava.vol1.ch09collections.t05linlist.cmd;

import druyaned.corejava.vol1.ch09collections.t05linlist.LinkedList;
import druyaned.corejava.vol1.ch09collections.util.AbstractCommand;

public class First extends AbstractCommand<LinkedList<Integer>> {
    
    public First(LinkedList<Integer> target) {
        super(target);
    }
    
    @Override public String code() {
        return "fst";
    }
    
    @Override public String description() {
        return "Get first";
    }
    
    @Override public String format() {
        return "fst";
    }
    
    @Override public String run(String[] inputParts) {
        return target.first() == null
                ? "null"
                : target.first().value().toString();
    }
    
}
