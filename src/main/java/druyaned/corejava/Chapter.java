package druyaned.corejava;

import static druyaned.ConsoleColors.bold;
import static druyaned.corejava.App.sin;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Chapter of {@link Volume volume}.
 * Represents all useful properties of a chapter: {@link #getNumber() number},
 * {@link #getDataDir() dataDir} and {@link #getTitle() title}.
 * 
 * <P><i>Important Note</i><br>
 * Creates the {@link #getDataDir() data-directory} if it was not exist.
 * 
 * @author druyaned
 * @see App
 * @see Book
 * @see Volume
 */
public abstract class Chapter implements Runnable {
    
    private final int number;
    private final Path dataDir;
    
    /**
     * Creates a new chapter of a {@link Volume volume} and the
     * {@link #getDataDir() data-directory}.
     * 
     * @param volumeDataDir the path to the volume's
     *      {@link Volume#getDataDir() data-directory}
     * @param number the number of the chapter to be created
     */
    public Chapter(Path volumeDataDir, int number) {
        this.number = number;
        if (number < 10)
            dataDir = volumeDataDir.resolve("ch0" + number);
        else
            dataDir = volumeDataDir.resolve("ch" + number);
        try {
            if (!Files.exists(dataDir))
                Files.createDirectory(dataDir);
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
     * Prompts to choose a part to run and runs it.
     * @param parts to choose one of the part to run
     * @see App#sin
     */
    protected final void choosePartAndRun(List<Runnable> parts) {
        final int n = parts.size();
        String[] partNames = new String[parts.size()];
        for (int i = 0; i < n; i++)
            partNames[i] = parts.get(i).getClass().getSimpleName();
        // print parts
        System.out.println("Parts of the chapter:");
        for (int i = 0; i < parts.size(); i++)
            System.out.printf("  %d. %s\n", i + 1, partNames[i]);
        // validate and select part
        String wrongPartMessage = "One integer is required:"
                + " the part number from 1 to " + parts.size();
        int choice;
        System.out.print("Choice: ");
        try {
            choice = Integer.parseInt(sin.nextLine());
        } catch (NumberFormatException ex) {
            System.out.println(wrongPartMessage);
            return;
        }
        if (choice < 1 || choice > parts.size()) {
            System.out.println(wrongPartMessage);
            return;
        }
        int index = choice - 1;
        System.out.println("\nRunning " + bold(partNames[index]) + "...");
        parts.get(index).run();
    }
    
    /**
     * Returns the title of the chapter.
     * @return the title of the chapter
     */
    public abstract String getTitle();
    
}
