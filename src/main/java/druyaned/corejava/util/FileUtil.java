package druyaned.corejava.util;

import static druyaned.ConsoleColors.blueBold;
import druyaned.corejava.Book;
import druyaned.corejava.BookItem;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.imageio.ImageIO;

public class FileUtil {
    
    /**
     * Creates the given file and also subdirectories.
     * @param filePath to be created
     */
    public static void createFileOnDemand(Path filePath) {
        Path parentPath = filePath.getParent();
        if (!Files.exists(parentPath)) {
            try {
                Files.createDirectories(parentPath);
                System.out.printf("Directory %s was created\n", blueBold(parentPath.toString()));
            } catch (IOException exc) {
                throw new UncheckedIOException(exc);
            }
        }
        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
                System.out.printf("File %s was created\n", blueBold(filePath.toString()));
            } catch (IOException exc) {
                throw new UncheckedIOException(exc);
            }
        }
    }
    
    /**
     * Returns image from the item's resource dataDir.
     * @param book to use {@link Path#relativize} to this book
     * @param item to give it to {@link Path#relativize}
     * @param fileName simple name without delimiters
     * @return image from the topic's resource dataDir
     */
    public static Image createImage(Book book, BookItem item, String fileName) {
        String imageName = "/" + book.dataDir().relativize(item.dataDir()).toString()
                + "/" + fileName;
        try {
            return ImageIO.read(FileUtil.class.getResourceAsStream(imageName));
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
    }
    
    /**
     * Returns input stream of the file which is located
     * under directory of the bookItem.
     * 
     * @param book to use {@link Path#relativize} to this book
     * @param item to give it to {@link Path#relativize}
     * @param fileName simple name without delimiters
     * @return input stream of the file
     */
    public static InputStream stream(Book book, BookItem item, String fileName) {
        String resource = "/" + book.dataDir().relativize(item.dataDir()).toString()
                + "/" + fileName;
        return FileUtil.class.getResourceAsStream(resource);
    }
    
}
