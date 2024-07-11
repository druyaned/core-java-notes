package com.github.druyaned.horstmann.corejava;

import static com.github.druyaned.ConsoleColors.bold;
import static com.github.druyaned.horstmann.corejava.App.getProjectName;
import com.github.druyaned.horstmann.corejava.vol1.Volume1;
import com.github.druyaned.horstmann.corejava.vol2.Volume2;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import static java.util.Collections.unmodifiableList;
import java.util.List;
import java.util.function.IntConsumer;
import static com.github.druyaned.horstmann.corejava.App.sin;

/**
 * Horstmann's book "Core Java" (tenth edition).
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
    private final List<Volume> vols;
    
    /**
     * Creates a new practice implementation of the
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
        vols = new ArrayList();
        vols.add(new Volume1(dataDir));
        vols.add(new Volume2(dataDir));
    }
    
    /**
     * Returns all volumes of the book as an unmodifiable list.
     * @return all volumes of the book as an unmodifiable list
     * @see java.util.Collections#unmodifiableList(java.util.List)
     */
    public List<Volume> getVolumes() {
        return unmodifiableList(vols);
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
     * @param volNumber the number of a volume to choose the chapter
     */
    @Override public void accept(int volNumber) {
        String wrongVolNumberMessage = "The volume number is from 1 to " + vols.size();
        Volume selectedVol = null;
        for (Volume v : vols) {
            if (v.getNumber() == volNumber) {
                selectedVol = v;
                break;
            }
        }
        if (selectedVol == null) {
            System.out.println(wrongVolNumberMessage);
            return;
        }
        System.out.println("Chapters of the volume " + volNumber + ":");
        List<? extends Chapter> chapters = selectedVol.getChapters();
        for (Chapter chapter : chapters) {
            System.out.printf("  %d. %s\n", chapter.getNumber(), chapter.getTitle());
        }
        System.out.print("Number of a chapter to run: ");
        String wrongChapterMessage = "Wrong chapter number";
        Chapter selectedChapter = null;
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
        System.out.printf("Running the chapter %d: %s\n",
                selectedChapter.getNumber(), bold(selectedChapter.getTitle()));
        selectedChapter.run();
    }
    
}
