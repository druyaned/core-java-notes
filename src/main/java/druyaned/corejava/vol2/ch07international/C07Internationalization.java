package druyaned.corejava.vol2.ch07international;

import druyaned.corejava.Chapter;
import druyaned.corejava.Volume;

/**
 * Practical implementation of the Chapter#07: C07Internationalization.
 * @author druyaned
 */
public class C07Internationalization extends Chapter {
    
    /**
     * Creates the Chapter#07: Internationalization.
     * @param volume to which the chapter belongs
     */
    public C07Internationalization(Volume volume) {
        super(volume, 7);
        topics().add(new T01LocaleCurrency(this));
        topics().add(new T02DateTimeFormatter(this));
        topics().add(new T03CollatorNormalizer(this));
        topics().add(new T04MessageFormat(this));
        topics().add(new T05App(this));
    }
    
    @Override public String title() {
        return "Chapter 07 Internationalization";
    }
    
}
