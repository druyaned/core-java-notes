package druyaned.corejava.vol1.ch04obj;

import druyaned.corejava.Chapter;
import druyaned.corejava.Volume;

/**
 * Practical implementation of the Chapter#04: Objects and Classes.
 * @author druyaned
 */
public class C04ObjectsClasses extends Chapter {
    
    /**
     * Creates the Chapter#04: Objects and Classes.
     * @param volume to which the chapter belongs
     */
    public C04ObjectsClasses(Volume volume) {
        super(volume, 4);
        topics().add(new T01FileIO(this));
        topics().add(new T02BitOperations(this));
        topics().add(new T03LocalDate(this));
    }
    
    @Override public String title() {
        return "Chapter 04 Objects and Classes";
    }
    
}
