package druyaned.corejava;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Chapter of volume.
 * @author druyaned
 * @see App
 * @see Book
 * @see Volume
 * @see Topic
 */
public abstract class Chapter implements BookItem {
    
    private final Volume volume;
    private final int number;
    private final Path dataDir;
    private final List<Topic> topics;
    
    /**
     * Creates a new chapter of volume.
     * @param volume to which the chapter belongs
     * @param number the number of the chapter to be created
     */
    public Chapter(Volume volume, int number) {
        this.volume = volume;
        this.number = number;
        if (number < 10)
            this.dataDir = volume.dataDir().resolve("ch0" + number);
        else
            this.dataDir = volume.dataDir().resolve("ch" + number);
        this.topics = new ArrayList<>();
    }
    
    /**
     * Returns volume to which the chapter belongs.
     * @return volume to which the chapter belongs
     */
    public Volume volume() {
        return volume;
    }
    
    @Override public final int number() {
        return number;
    }
    
    @Override public final Path dataDir() {
        return dataDir;
    }
    
    /**
     * Returns topics of the chapter.
     * @return topics of the chapter
     */
    public final List<Topic> topics() {
        return topics;
    }
    
}
