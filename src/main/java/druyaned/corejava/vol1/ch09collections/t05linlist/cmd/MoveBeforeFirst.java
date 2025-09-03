package druyaned.corejava.vol1.ch09collections.t05linlist.cmd;

import druyaned.corejava.vol1.ch09collections.t05linlist.LinkedList;
import druyaned.corejava.vol1.ch09collections.util.AbstractCommand;

public class MoveBeforeFirst extends AbstractCommand<LinkedList<Integer>> {
    
    public MoveBeforeFirst(LinkedList<Integer> target) {
        super(target);
    }
    
    @Override public String code() {
        return "mbf";
    }
    
    @Override public String description() {
        return "Move before first";
    }
    
    @Override public String format() {
        return "mbf";
    }
    
    @Override public String run(String[] inputParts) {
        target.iterator().moveBeforeFirst();
        return VOID_RESULT;
    }
    
}
