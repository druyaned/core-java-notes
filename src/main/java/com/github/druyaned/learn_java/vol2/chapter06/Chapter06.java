package com.github.druyaned.learn_java.vol2.chapter06;

import static com.github.druyaned.ConsoleColors.*;
import com.github.druyaned.learn_java.Chapterable;

/**
 * Practice implementation of the chapter 6.
 * 
 * @author druyaned
 * @see com.github.druyaned.learn_java.vol2.App
 */
public class Chapter06 implements Chapterable {

    @Override
    public void run() {
        System.out.println(bold("Running Chapter06: Date-Time API"));
    }
    @Override
    public int getNumber() { return 6; }
    
    @Override
    public String getTitle() { return "Date-Time API"; }
    
    @Override
    public boolean passed() { return false; }
}
