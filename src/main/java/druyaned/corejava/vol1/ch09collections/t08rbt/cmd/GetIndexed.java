package druyaned.corejava.vol1.ch09collections.t08rbt.cmd;

import druyaned.corejava.vol1.ch09collections.t06rbt.RBTIndexed;
import druyaned.corejava.vol1.ch09collections.t06rbt.RBTIndexed.IndexedNode;
import druyaned.corejava.vol1.ch09collections.util.AbstractCommand;

public class GetIndexed extends AbstractCommand<RBTIndexed> {
    
    public GetIndexed(RBTIndexed target) {
        super(target);
    }
    
    @Override public String code() {
        return "g";
    }
    
    @Override public String description() {
        return "Get node";
    }
    
    @Override public String format() {
        return "g [INT_VAL]";
    }
    
    @Override public String run(String[] inputParts) {
        IndexedNode node = target.getIndexedNode(Integer.parseInt(inputParts[1]));
        return node == null ? "null" : node.toString();
    }
    
}
