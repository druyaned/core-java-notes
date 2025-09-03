package druyaned.corejava.vol2.ch08anno;

import druyaned.corejava.Chapter;
import druyaned.corejava.Volume;

/**
 * Practical implementation of the Chapter#08: Scripting, Compiling, Annotations.
 * @author druyaned
 */
public class C08ScriptCompileAnnotate extends Chapter {
    
    /**
     * Creates the Chapter#08: Scripting, Compiling, Annotations.
     * @param volume to which the chapter belongs
     */
    public C08ScriptCompileAnnotate(Volume volume) {
        super(volume, 8);
        topics().add(new T01ScriptingEngine(this));
        topics().add(new T02Annotations(this));
        topics().add(new T03ASM(this));
    }
    
    @Override public String title() {
        return "Chapter 08 Scripting, Compiling, Annotations";
    }
    
}
