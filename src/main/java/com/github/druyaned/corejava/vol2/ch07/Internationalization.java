package com.github.druyaned.corejava.vol2.ch07;

import com.github.druyaned.corejava.Chapter;
import com.github.druyaned.corejava.vol2.ch07.src.P01LocaleCurrency;
import com.github.druyaned.corejava.vol2.ch07.src.P02DateTimeFormatter;
import com.github.druyaned.corejava.vol2.ch07.src.P03CollatorNormalizer;
import com.github.druyaned.corejava.vol2.ch07.src.P04MessageFormat;
import com.github.druyaned.corejava.vol2.ch07.src.P05App;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Practice implementation of the Chapter#07: Internationalization.
 * @author druyaned
 */
public class Internationalization extends Chapter {
    
    /**
     * Creates the Chapter#07: Internationalization.
     * @param volDataDir the path to the volume's data-directory
     */
    public Internationalization(Path volDataDir) {
        super(volDataDir, 7);
    }
    
    @Override public String getTitle() {
        return "Internationalization";
    }
    
    @Override public void run() {
        List<Runnable> parts = new ArrayList<>();
        parts.add(new P01LocaleCurrency());
        parts.add(new P02DateTimeFormatter());
        parts.add(new P03CollatorNormalizer());
        parts.add(new P04MessageFormat());
        parts.add(new P05App());
        choosePartAndRun(parts);
    }
    
}
