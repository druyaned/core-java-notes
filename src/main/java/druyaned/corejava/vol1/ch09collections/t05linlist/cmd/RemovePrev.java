package druyaned.corejava.vol1.ch09collections.t05linlist.cmd;

import druyaned.corejava.vol1.ch09collections.t05linlist.LinkedList;
import druyaned.corejava.vol1.ch09collections.util.AbstractCommand;

public class RemovePrev extends AbstractCommand<LinkedList<Integer>> {
    
    public RemovePrev(LinkedList<Integer> target) {
        super(target);
    }
    
    @Override public String code() {
        return "rmp";
    }
    
    @Override public String description() {
        return "Remove previous";
    }
    
    @Override public String format() {
        return "rmp";
    }
    
    @Override public String run(String[] inputParts) {
        return target.iterator().removePrev().toString();
    }
    
}
