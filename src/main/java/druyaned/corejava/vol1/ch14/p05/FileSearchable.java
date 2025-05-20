package druyaned.corejava.vol1.ch14.p05;

import java.nio.file.Path;
import java.util.List;

public interface FileSearchable extends Runnable {
    
    FileSearchable set(Path root, String target);
    
    List<Path> getResult();
    
    int getEntryCount();
    
    Path getRoot();
    
    String getTarget();
    
}
