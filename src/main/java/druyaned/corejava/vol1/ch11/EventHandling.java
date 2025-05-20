package druyaned.corejava.vol1.ch11;

import druyaned.corejava.vol1.ch11.p03.TestAction;
import druyaned.corejava.vol1.ch11.p04.TestMouse;
import druyaned.corejava.vol1.ch11.p02.TestFrameStyle;
import druyaned.corejava.Chapter;
import druyaned.corejava.vol1.ch11.p01.TestKeyPress;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Practical implementation of the Chapter#11: Event Handling.
 * @author druyaned
 */
public class EventHandling extends Chapter {
    
    /**
     * Creates the Chapter#11: Event Handling.
     * @param volDataDir the path to the volume's data-directory
     */
    public EventHandling(Path volDataDir) {
        super(volDataDir, 11);
    }
    
    @Override public String getTitle() {
        return "Event Handling";
    }
    
    @Override public void run() {
        List<Runnable> parts = new ArrayList<>();
        parts.add(new TestKeyPress());
        parts.add(new TestFrameStyle());
        parts.add(new TestAction());
        parts.add(new TestMouse());
        choosePartAndRun(parts);
    }
    
}
