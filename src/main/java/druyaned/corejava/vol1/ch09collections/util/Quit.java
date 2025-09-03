package druyaned.corejava.vol1.ch09collections.util;

public class Quit implements Command {
    
    @Override public String code() {
        return "q";
    }
    
    @Override public String description() {
        return "Quit";
    }
    
    @Override public String format() {
        return "q";
    }
    
    @Override public String run(String[] inputParts) {
        return VOID_RESULT;
    }
    
}
