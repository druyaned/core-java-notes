package druyaned.corejava.vol2.ch01streamapi;

import druyaned.corejava.util.WarAndPeace;
import druyaned.corejava.Chapter;
import druyaned.corejava.Volume;
import java.nio.file.Path;

/**
 * Practical implementation of the Chapter#01: Stream API.
 * @author druyaned
 */
public class C01StreamAPI extends Chapter {
    
    /**
     * Creates the Chapter#01: Stream API.
     * @param volume to which the chapter belongs
     */
    public C01StreamAPI(Volume volume) {
        super(volume, 1);
        Path textPath = WarAndPeace.textPath(volume);
        topics().add(new T01StreamInitialize(this, textPath));
        topics().add(new T02Intermediate(this, textPath));
        topics().add(new T03Reductions(this));
        topics().add(new T04Collectors(this));
        topics().add(new T05Parallel(this, textPath));
    }
    
    @Override public String title() {
        return "Chapter 01 Stream API";
    }
    
}
