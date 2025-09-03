package druyaned.corejava.vol1.ch09collections.util;

public interface Command {
    
    static final String VOID_RESULT = "[void]";
    
    String code();
    
    String description();
    
    String format();
    
    String run(String[] inputParts);
    
}
