package druyaned.corejava.vol1.ch09.src;

import java.io.PrintStream;

public abstract class AbstractPrinter<T> {
    
    protected T target;
    protected String filler;
    protected PrintStream sout;
    
    public AbstractPrinter(T target, String filler, PrintStream sout) {
        this.target = target;
        setFiller(filler);
        setSout(sout);
    }
    
    public final T getTarget() {
        return target;
    }
    
    public final String getFiller() {
        return filler;
    }
    
    public final PrintStream getSout() {
        return sout;
    }
    
    public final void setTarget(T target) {
        this.target = target;
    }
    
    public final void setFiller(String filler) {
        if (filler == null)
            this.filler = " ";
        else
            this.filler = filler;
    }
    
    public final void setSout(PrintStream sout) {
        if (sout == null)
            this.sout = System.out;
        else
            this.sout = sout;
    }
    
    public abstract void print();
    
}
