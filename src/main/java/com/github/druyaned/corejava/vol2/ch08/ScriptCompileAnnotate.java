package com.github.druyaned.corejava.vol2.ch08;

import com.github.druyaned.corejava.Chapter;
import com.github.druyaned.corejava.vol2.ch08.src.P01ScriptingEngine;
import com.github.druyaned.corejava.vol2.ch08.src.P02Annotations;
import com.github.druyaned.corejava.vol2.ch08.src.P03ASM;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Practical implementation of the Chapter#08: Scripting, Compiling, Annotations.
 * @author druyaned
 */
public class ScriptCompileAnnotate extends Chapter {
    
    /**
     * Creates the Chapter#08: Scripting, Compiling, Annotations.
     * @param volDataDir the path to the volume's data-directory
     */
    public ScriptCompileAnnotate(Path volDataDir) {
        super(volDataDir, 8);
    }
    
    @Override public String getTitle() {
        return "Scripting, Compiling, Annotations";
    }
    
    @Override public void run() {
        List<Runnable> parts = new ArrayList<>();
        parts.add(new P01ScriptingEngine());
        parts.add(new P02Annotations());
        parts.add(new P03ASM());
        choosePartAndRun(parts);
    }
    
}
