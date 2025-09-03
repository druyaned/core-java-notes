package druyaned.corejava.vol1.ch06interface;

import druyaned.corejava.Chapter;
import druyaned.corejava.Volume;

/**
 * Practical implementation of the Chapter#06: Interfaces, Lambda and Internal Classes.
 * @author druyaned
 */
public class C06InterfacesInternalClasses extends Chapter {
    
    /**
     * Creates the Chapter#06: Interfaces, Lambda and Internal Classes.
     * @param volume to which the chapter belongs
     */
    public C06InterfacesInternalClasses(Volume volume) {
        super(volume, 6);
        topics().add(new T01Timer(this));
        topics().add(new T02Cloneable(this));
        topics().add(new T03Lambda(this));
        topics().add(new T04Inners(this));
    }
    
    @Override public String title() {
        return "Chapter 06 Interfaces, Lambda and Internal Classes";
    }
    
}
