package com.github.druyaned.learn_java.vol1.chapter01;

import static com.github.druyaned.ConsoleColors.*;
import com.github.druyaned.learn_java.Chapterable;

/**
 * Practice implementation of the chapter 1.
 * 
 * @author druyaned
 * @see com.github.druyaned.learn_java.vol1.Volume1
 */
public class Chapter01 implements Chapterable {

    @Override
    public void run() {
        System.out.println(bold("Running Chapter01: Introduction"));
        
//        Fixer.run();
    }

    @Override
    public int getNumber() { return 1; }
    
    @Override
    public String getTitle() { return "Introduction"; }
    
    @Override
    public boolean passed() { return false; }
}
