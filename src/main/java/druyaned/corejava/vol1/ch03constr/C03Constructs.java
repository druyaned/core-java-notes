package druyaned.corejava.vol1.ch03constr;

import druyaned.corejava.Chapter;
import druyaned.corejava.Volume;

/**
 * Practical implementation of the Chapter#03: Basic C03Constructs.
 * @author druyaned
 */
public class C03Constructs extends Chapter {
    
    /**
     * Creates the Chapter#03: Basic Constructs.
     * @param volume to which the chapter belongs
     */
    public C03Constructs(Volume volume) {
        super(volume, 3);
        topics().add(new T01UTF16(this));
        topics().add(new T02In(this));
        topics().add(new T03Out(this));
        topics().add(new T04FileIO(this));
        topics().add(new T05ControlLogic(this));
        topics().add(new T06BigNumbers(this));
        topics().add(new T07Arrays(this));
    }
    
    @Override public String title() {
        return "Chapter 03 Basic Constructs";
    }
    
}
