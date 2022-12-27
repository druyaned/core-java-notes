package com.github.druyaned.learn_java.vol2.chapter12;

import static com.github.druyaned.ConsoleColors.*;
import com.github.druyaned.learn_java.Chapterable;

/**
 * Practice implementation of the chapter 2.
 * 
 * @author druyaned
 * @see com.github.druyaned.learn_java.vol2.App
 */
public class Chapter12 implements Chapterable {

    @Override
    public void run() {
        System.out.println(bold("Running Chapter12: Platform-Oriented Methods"));
    }

    @Override
    public int getNumber() { return 12; }
    
    @Override
    public String getTitle() { return "Platform-Oriented Methods"; }
    
    @Override
    public boolean passed() { return false; }
}
