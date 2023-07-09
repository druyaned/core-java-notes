package com.github.druyaned.learn_java.vol2.chapter08;

import static com.github.druyaned.ConsoleColors.*;
import com.github.druyaned.learn_java.Chapterable;

/**
 * Practice implementation of the chapter 8.
 * 
 * @author druyaned
 * @see com.github.druyaned.learn_java.vol2.App
 */
public class Chapter08 implements Chapterable {

    @Override
    public void run() {
        System.out.println(bold("Running Chapter08: Scripts and Annotations"));
        P01ScriptingEngine.run();
        P02Annotations.run();
        P03ASM.run();
    }

    @Override
    public int getNumber() { return 8; }
    
    @Override
    public String getTitle() { return "Scripts and Annotations"; }
    
    @Override
    public boolean passed() { return true; }

}
