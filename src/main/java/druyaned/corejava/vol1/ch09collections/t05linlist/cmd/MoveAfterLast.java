package druyaned.corejava.vol1.ch09collections.t05linlist.cmd;

import druyaned.corejava.vol1.ch09collections.t05linlist.LinkedList;
import druyaned.corejava.vol1.ch09collections.util.AbstractCommand;

public class MoveAfterLast extends AbstractCommand<LinkedList<Integer>> {
    
    public MoveAfterLast(LinkedList<Integer> target) {
        super(target);
    }
    
    @Override public String code() {
        return "mal";
    }
    
    @Override public String description() {
        return "Move after last";
    }
    
    @Override public String format() {
        return "mal";
    }
    
    @Override public String run(String[] inputParts) {
        target.iterator().moveAfterLast();
        return VOID_RESULT;
    }
    
}
