package druyaned.corejava.vol2.ch08anno;

import druyaned.corejava.vol2.ch08anno.t03asm.GetteredHandler;
import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import java.io.IOException;
import java.io.UncheckedIOException;

public class T03ASM extends Topic {
    
    public T03ASM(Chapter chapter) {
        super(chapter, 3);
    }
    
    @Override public String title() {
        return "Topic 03 ASM";
    }
    
    @Override public void run() {
        try {
            GetteredHandler.handlePerson();
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
    }
    
}
