package druyaned.corejava;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Volume of {@link Book book}.
 * Represents all useful properties of a volume: {@link #getNumber() number},
 * {@link #getDataDir() dataDir} and {@link #getChapters() chapters}.
 * 
 * <P><i>Important Note</i><br>
 * Creates the {@link #getDataDir() data-directory} if it was not exist.
 * 
 * @author druyaned
 * @see App
 * @see Book
 * @see Chapter
 */
public abstract class Volume {
    
    protected final int number;
    protected final Path dataDir;
    
    /**
     * Creates a new volume of the Horstmann's book "Core Java" (tenth edition)
     * and the {@link #getDataDir() data-directory}.
     * 
     * @param bookDataDir the path to the book's {@link Book#getDataDir() data-directory}
     * @param number the number of the volume to be created
     */
    public Volume(Path bookDataDir, int number) {
        this.number = number;
        dataDir = bookDataDir.resolve("vol" + number);
        try {
            if (!Files.exists(dataDir)) {
                Files.createDirectory(dataDir);
            }
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
    }
    
    /**
     * Returns the number of the volume.
     * @return the number of the volume
     */
    public final int getNumber() {
        return number;
    }
    
    /**
     * Returns the path of the created data-directory which contains
     * data-directories of each {@link Chapter chapter}.
     * 
     * @return the path of the created data-directory which contains
     *      data-directories of each {@link Chapter chapter}
     */
    public final Path getDataDir() {
        return dataDir;
    }
    
    /**
     * Return all {@link Chapter chapters} of the volume.
     * 
     * <P><i>Implementation note</i><br>
     * Unmodifiable list is necessary.
     * 
     * @return all {@link Chapter chapters} of the volume
     * @see java.util.Collections#unmodifiableList(java.util.List)
     */
    public abstract List<? extends Chapter> getChapters();
    
}
