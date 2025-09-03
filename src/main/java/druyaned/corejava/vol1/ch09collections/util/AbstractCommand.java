package druyaned.corejava.vol1.ch09collections.util;

public abstract class AbstractCommand<T> implements Command {
    
    protected final T target;
    
    public AbstractCommand(T target) {
        this.target = target;
    }
    
}
