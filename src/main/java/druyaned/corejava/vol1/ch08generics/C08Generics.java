package druyaned.corejava.vol1.ch08generics;

import druyaned.corejava.Chapter;
import druyaned.corejava.Volume;

/**
 * Practical implementation of the Chapter#08: Generics.
 * 
 * <P>Remember the rule (PECS = Producer Extends Consumer Super):<pre>
 *  &lt;? extends Type> : to read data from a generic instance
 *  &lt;? super Type>   : to write data to a generic instance
 * </pre>
 * 
 * @author druyaned
 */
public class C08Generics extends Chapter {
    
    /**
     * Creates the Chapter#08: Generics.
     * @param volume to which the chapter belongs
     */
    public C08Generics(Volume volume) {
        super(volume, 8);
        topics().add(new T01BridgeMethod(this));
        topics().add(new T02GenericArray(this));
        topics().add(new T03GenericCars(this));
        topics().add(new T04GenericsLimitations(this));
        topics().add(new T05DoubtfulGenericArray(this));
    }
    
    @Override public String title() {
        return "Chapter 08 Generics";
    }
    
}
