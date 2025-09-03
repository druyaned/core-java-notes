package druyaned.corejava;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Volume of book.
 * @author druyaned
 * @see App
 * @see Book
 * @see Chapter
 * @see Topic
 */
public abstract class Volume implements BookItem {
    
    private final Book book;
    private final int number;
    private final Path dataDir;
    private final List<Chapter> chapters;
    
    /**
     * Creates a new volume of the Horstmann's book "Core Java" (tenth edition).
     * @param book to which the volume belongs
     * @param number the number of the volume to be created
     */
    public Volume(Book book, int number) {
        this.book = book;
        this.number = number;
        this.dataDir = book.dataDir().resolve("vol" + number);
        this.chapters = new ArrayList<>();
    }
    
    /**
     * Returns book to which the volume belongs.
     * @return book to which the volume belongs
     */
    public final Book book() {
        return book;
    }
    
    @Override public final int number() {
        return number;
    }
    
    @Override public final Path dataDir() {
        return dataDir;
    }
    
    /**
     * Returns chapters of the volume.
     * @return chapters of the volume
     */
    public final List<Chapter> chapters() {
        return chapters;
    }
    
}
