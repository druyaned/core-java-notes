package com.github.druyaned.horstmann.corejava.vol2.ch01;

import com.github.druyaned.horstmann.corejava.Chapter;
import com.github.druyaned.horstmann.corejava.vol2.ch01.src.P01StreamInitialize;
import com.github.druyaned.horstmann.corejava.vol2.ch01.src.P02Intermediate;
import com.github.druyaned.horstmann.corejava.vol2.ch01.src.P03Reductions;
import com.github.druyaned.horstmann.corejava.vol2.ch01.src.P04Collectors;
import com.github.druyaned.horstmann.corejava.vol2.ch01.src.P05Parallel;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Practice implementation of the Chapter#01: Stream API.
 * @author druyaned
 */
public class StreamAPI extends Chapter {
    
    /**
     * Creates the Chapter#01: Stream API.
     * @param volDataDir the path to the volume's data-directory
     */
    public StreamAPI(Path volDataDir) {
        super(volDataDir, 1);
    }
    
    @Override public String getTitle() {
        return "Stream API";
    }
    
    @Override public void run() {
        List<Runnable> parts = new ArrayList<>();
        parts.add(new P01StreamInitialize());
        parts.add(new P02Intermediate());
        parts.add(new P03Reductions());
        parts.add(new P04Collectors());
        parts.add(new P05Parallel());
        choosePartAndRun(parts);
    }
    
}
