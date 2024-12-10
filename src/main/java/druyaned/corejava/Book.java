package druyaned.corejava;

import static druyaned.ConsoleColors.bold;
import static druyaned.corejava.App.getProjectName;
import druyaned.corejava.vol1.Volume1;
import druyaned.corejava.vol2.Volume2;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import static java.util.Collections.unmodifiableList;
import java.util.List;
import java.util.function.IntConsumer;
import static druyaned.corejava.App.sin;

/**
 * Practical implementation of Horstmann's book "Core Java" (tenth edition).
 * 
 * <P>There are some {@link Volume volumes}.
 * In each volume there are some {@link Chapter chapters}.
 * Each chapter has some classes that represents
 * some important aspects of the book. Such an approach helps to learn
 * well and to code more.
 * 
 * <P><i>Important Note</i><br>
 * Creates the {@link #getDataDir() data-directory} if it was not exist.
 *
 * @author druyaned
 * @see App
 * @see Volume
 * @see Chapter
 */
public final class Book implements IntConsumer {
    
    private final Path dataDir;
    private final List<Volume> volumes;
    
    /**
     * Creates a new practical implementation of the
     * Horstmann's book "Core Java" (tenth edition).
     * Also creates the {@link #getDataDir() dataDir} and adds
     * all {@link Volume volumes}.
     */
    public Book() {
        dataDir = Paths.get(System.getProperty("user.home"), getProjectName() + "-data");
        try {
            if (!Files.exists(dataDir)) {
                Files.createDirectory(dataDir);
            }
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
        volumes = new ArrayList();
        volumes.add(new Volume1(dataDir));
        volumes.add(new Volume2(dataDir));
    }
    
    /**
     * Returns all volumes of the book as an unmodifiable list.
     * @return all volumes of the book as an unmodifiable list
     * @see java.util.Collections#unmodifiableList(java.util.List)
     */
    public List<Volume> getVolumes() {
        return unmodifiableList(volumes);
    }
    
    /**
     * Returns the path of the created data-directory which contains
     * data-directories of each {@link Volume volume}.
     * 
     * @return the path of the created data-directory which contains
     *      data-directories of each {@link Volume volume}
     */
    public Path getDataDir() {
        return dataDir;
    }
    
    /**
     * Runs a {@link Volume volume's} {@link Chapter chapter}
     * that is defined by the command line input.
     * @param volumeNumber the number of a volume to choose the chapter
     */
    @Override public void accept(int volumeNumber) {
        // validate volumeNumber
        String wrongVolNumberMessage = "The volume number is from 1 to " + volumes.size();
        Volume selectedVolume = null;
        for (Volume volume : volumes) {
            if (volume.getNumber() == volumeNumber) {
                selectedVolume = volume;
                break;
            }
        }
        if (selectedVolume == null) {
            System.out.println(wrongVolNumberMessage);
            return;
        }
        // print volume's chapters
        System.out.println("Chapters of the volume " + volumeNumber + ":");
        List<? extends Chapter> chapters = selectedVolume.getChapters();
        for (Chapter chapter : chapters) {
            System.out.printf("  %d. %s\n", chapter.getNumber(), chapter.getTitle());
        }
        // validate and select chapter
        String wrongChapterMessage = "Wrong chapter number";
        Chapter selectedChapter = null;
        System.out.print("Number of a chapter to run: ");
        try {
            int chapterNumber = Integer.parseInt(sin.nextLine());
            for (Chapter chapter : chapters) {
                if (chapterNumber == chapter.getNumber()) {
                    selectedChapter = chapter;
                    break;
                }
            }
        } catch (NumberFormatException exc) {
            System.out.println(wrongChapterMessage);
            return;
        }
        if (selectedChapter == null) {
            System.out.println(wrongChapterMessage);
            return;
        }
        // run chapter
        System.out.printf("Running the chapter %d: %s\n",
                selectedChapter.getNumber(), bold(selectedChapter.getTitle()));
        selectedChapter.run();
    }
    
}
