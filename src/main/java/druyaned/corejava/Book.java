package druyaned.corejava;

import druyaned.corejava.vol1.Volume1;
import druyaned.corejava.vol2.Volume2;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Practical implementation of Horstmann's book "Core Java" (tenth edition).
 * 
 * <P>There are some {@link Volume volumes}.
 * In each volume there are some {@link Chapter chapters}.
 * Each chapter has some {@link Topic topics} that reflect
 * some important aspects of the book. Such an approach
 * helps to learn well and to code more.
 * 
 * @author druyaned
 * @see App
 * @see Volume
 * @see Chapter
 * @see Topic
 */
public final class Book {
    
    private final Path dataDir;
    private final List<Volume> volumes;
    
    /**
     * Creates a new practical implementation of the
     * Horstmann's book "Core Java" (tenth edition).
     */
    public Book() {
        dataDir = Path.of(System.getProperty("user.home"), App.projectName() + "-data");
        volumes = new ArrayList();
        volumes.add(new Volume1(this));
        volumes.add(new Volume2(this));
    }
    
    /**
     * Returns path of data-directory of the book item.
     * @return path of data-directory of the book item
     */
    public Path dataDir() {
        return dataDir;
    }
    
    /**
     * Returns volumes of the book.
     * @return volumes of the book
     */
    public List<Volume> volumes() {
        return volumes;
    }
    
}
