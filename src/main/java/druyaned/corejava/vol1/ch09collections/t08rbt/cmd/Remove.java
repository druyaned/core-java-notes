package druyaned.corejava.vol1.ch09collections.t08rbt.cmd;

import druyaned.corejava.vol1.ch09collections.t06rbt.RBTIndexed;
import druyaned.corejava.vol1.ch09collections.util.AbstractCommand;

public class Remove extends AbstractCommand<RBTIndexed> {
    
    public Remove(RBTIndexed target) {
        super(target);
    }
    
    @Override public String code() {
        return "r";
    }
    
    @Override public String description() {
        return "Remove value";
    }
    
    @Override public String format() {
        return "r [INT_VAL]";
    }
    
    @Override public String run(String[] inputParts) {
        return Boolean.toString(target.remove(Integer.parseInt(inputParts[1])));
    }
    
}
