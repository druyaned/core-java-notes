package druyaned.corejava;

import java.nio.file.Path;

/**
 * Topic of chapter.
 * @author druyaned
 * @see App
 * @see Book
 * @see Volume
 * @see Chapter
 */
public abstract class Topic implements BookItem, Runnable {
    
    private final Chapter chapter;
    private final int number;
    private final Path dataDir;
    
    public Topic(Chapter chapter, int number) {
        this.chapter = chapter;
        this.number = number;
        if (number < 10)
            this.dataDir = chapter.dataDir().resolve("t0" + number);
        else
            this.dataDir = chapter.dataDir().resolve("t" + number);
    }
    
    /**
     * Returns chapter to which the topic belongs.
     * @return chapter to which the topic belongs
     */
    public final Chapter chapter() {
        return chapter;
    }
    
    @Override public final Path dataDir() {
        return dataDir;
    }
    
    @Override public final int number() {
        return number;
    }
    
}
