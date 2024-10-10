package com.github.druyaned.corejava.vol1.ch11;

import com.github.druyaned.corejava.vol1.ch11.src.TestAction;
import com.github.druyaned.corejava.vol1.ch11.src.TestMouse;
import com.github.druyaned.corejava.vol1.ch11.src.TestFrameStyle;
import com.github.druyaned.corejava.Chapter;
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
        parts.add(new TestFrameStyle());
        parts.add(new TestAction());
        parts.add(new TestMouse());
        choosePartAndRun(parts);
    }
    
}
