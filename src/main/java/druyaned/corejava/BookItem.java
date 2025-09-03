package druyaned.corejava;

import java.nio.file.Path;

public interface BookItem {
    
    /**
     * Returns number of the book item.
     * @return number of the book item
     */
    int number();
    
    /**
     * Returns path of data-directory of the book item.
     * @return path of data-directory of the book item
     */
    Path dataDir();
    
    /**
     * Returns title of the book item.
     * @return title of the book item
     */
    String title();
    
}
