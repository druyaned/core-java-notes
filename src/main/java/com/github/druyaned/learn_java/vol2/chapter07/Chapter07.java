package com.github.druyaned.learn_java.vol2.chapter07;

import static com.github.druyaned.ConsoleColors.*;
import com.github.druyaned.learn_java.Chapterable;

/**
 * Practice implementation of the chapter 7.
 * 
 * @author druyaned
 * @see com.github.druyaned.learn_java.vol2.App
 */
public class Chapter07 implements Chapterable {

    @Override
    public void run() {
        System.out.println(bold("Running Chapter07: Internationalization"));
    }

    @Override
    public int getNumber() { return 7; }
    
    @Override
    public String getTitle() { return "Internationalization"; }
    
    @Override
    public boolean passed() { return false; }
}
