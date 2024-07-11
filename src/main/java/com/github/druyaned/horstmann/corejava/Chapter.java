package com.github.druyaned.horstmann.corejava;

import static com.github.druyaned.horstmann.corejava.App.sin;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * A chapter of a {@link Volume volume} of the Horstmann's book "Core Java" (tenth edition).
 * Represents all useful properties of a chapter: {@link #getNumber() number},
 * {@link #getDataDir() dataDir} and {@link #getTitle() title}.
 * 
 * <P><i>Important Note</i><br>
 * Creates the {@link #getDataDir() data-directory} if it was not exist.
 * 
 * @author druyaned
 * @see App
 * @see Volume
 */
public abstract class Chapter implements Runnable {
    
    private final int number;
    private final Path dataDir;
    
    /**
     * Creates a new chapter of a {@link Volume volume} and the
     * {@link #getDataDir() data-directory}.
     * 
     * @param volDataDir the path to the volume's {@link Volume#getDataDir() data-directory}
     * @param number the number of the chapter to be created
     */
    public Chapter(Path volDataDir, int number) {
        this.number = number;
        if (number < 10) {
            dataDir = volDataDir.resolve("ch0" + number);
        } else {
            dataDir = volDataDir.resolve("ch" + number);
        }
        try {
            if (!Files.exists(dataDir)) {
                Files.createDirectory(dataDir);
            }
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
    }
    
    /**
     * Returns the number of the chapter.
     * @return the number of the chapter
     */
    public final int getNumber() {
        return number;
    }
    
    /**
     * Returns the path of the created data-directory to deal with files or other data.
     * @return the path of the created data-directory to deal with files or other data
     */
    public final Path getDataDir() {
        return dataDir;
    }
    
    /**
     * Prompts to choose a part to run and run it.
     * @param parts to choose one of the part to run
     * @see App#sin
     */
    protected final void choosePartAndRun(List<Runnable> parts) {
        System.out.println("Choose the part to run:");
        for (int i = 0; i < parts.size(); i++) {
            System.out.printf("  %d) %s\n", i + 1, parts.get(i).getClass().getSimpleName());
        }
        System.out.print("Choice: ");
        int index = sin.nextInt() - 1;
        sin.nextLine();
        parts.get(index).run();
    }
    
    /**
     * Returns the title of the chapter.
     * @return the title of the chapter
     */
    public abstract String getTitle();
    
}
