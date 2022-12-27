package com.github.druyaned.learn_java.vol2;

import static com.github.druyaned.ConsoleColors.*;
import static com.github.druyaned.learn_java.App.APP_IN;
import com.github.druyaned.learn_java.Chapterable;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.UncheckedIOException;

/**
 * Practice implementation of learning Java by the Horstmann's book volume 2.
 * <p><b>NOTE</b>: the app creates the directory
 * "&lt;USER_HOME&gt;/learn-java/vol2/" with necessary data.
 *
 * @author druyaned
 * @see com.github.druyaned.learn_java.App
 */
public class Volume2 implements Runnable {
    
    /** Minimum number of a chapter. */
    public static final int MIN_CHAPTER = 1;
    
    /** Maximum number of a chapter. */
    public static final int MAX_CHAPTER = 12;
    
    private static final Path DATA_DIR_PATH;
    private static final Chapterable[] chapters = new Chapterable[MAX_CHAPTER + 1];
    
    static {
        String userHome = System.getProperty("user.home");
        DATA_DIR_PATH = Paths.get(userHome, "learn-java", "vol2");
        if (!Files.exists(DATA_DIR_PATH)) {
            try {
                Files.createDirectories(DATA_DIR_PATH);
            } catch (IOException ex) {
                throw new UncheckedIOException(ex);
            }
        }
    }
    
    /**
     * Returns the path to the data directory of the app (subdirectories may not exist).
     * 
     * @return the path to the data directory of the app.
     */
    public static Path getDataDirPath() { return DATA_DIR_PATH; }
    
//-Non-static---------------------------------------------------------------------------------------
    
    @Override
    public void run() {
        System.out.println(bold("Executing practice implementation of ") + greenBold("volume 2"));
        
        // adding all available chapter
        for (int i = MIN_CHAPTER; i <= MAX_CHAPTER; ++i) {
            String packageName;
            String className;
            
            if (i < 10) {
                packageName = "com.github.druyaned.learn_java.vol2.chapter0" + i;
                className = packageName + ".Chapter0" + i;
            } else {
                packageName = "com.github.druyaned.learn_java.vol2.chapter" + i;
                className = packageName + ".Chapter" + i;
            }
            
            try {
                Class<?> chapterClass = Class.forName(className);
                chapters[i] = (Chapterable)chapterClass.getConstructor().newInstance();
                
            } catch (ClassNotFoundException | NoSuchMethodException | SecurityException |
                     InstantiationException | IllegalAccessException | IllegalArgumentException |
                     InvocationTargetException exc) {
                
                String message = "An error with a chapter in volume 2";
                Logger.getLogger(Volume2.class.getName()).log(Level.SEVERE, message, exc);
            }
        }
        
        // printing available chapters
        System.out.println("Available chapters:");
        for (int i = MIN_CHAPTER; i <= MAX_CHAPTER; ++i) {
            if (chapters[i].passed()) {
                System.out.printf("  Chapter #%d: %s\n", i, chapters[i].getTitle());
            }
        }
        
        // choosing a chapter to run
        Chapterable chapter;
        System.out.print("Number of chapter to run: ");
        String inputLine = APP_IN.nextLine();
        try {
            int i = Integer.parseInt(inputLine);
            boolean badChapter = chapters[i] == null || !chapters[i].passed();
            if (i < MIN_CHAPTER || i > MAX_CHAPTER || badChapter) {
                String message = String.format("Wrong number of chapter \"%d\"", i);
                Logger.getLogger(Volume2.class.getName())
                        .log(Level.SEVERE, message, new IndexOutOfBoundsException());
                return;
            }
            chapter = chapters[i];
        } catch (NumberFormatException ex) {
            String message = String.format("\"%s\" is not a number", inputLine);
            Logger.getLogger(Volume2.class.getName()).log(Level.SEVERE, message, ex);
            return;
        }
        chapter.run();
    }
}
