package com.github.druyaned.learn_java.vol1.chapter05;

import static com.github.druyaned.ConsoleColors.*;
import com.github.druyaned.learn_java.Chapterable;

/**
 * Practice implementation of the chapter 5.
 * 
 * @author druyaned
 * @see com.github.druyaned.learn_java.vol1.Volume1
 */
public class Chapter05 implements Chapterable {

    @Override
    public void run() {
        System.out.println(bold("Running Chapter05: Inheritance"));
        
        ClassAnalyzer.showClass(Chapter05.class);
    }

    @Override
    public int getNumber() { return 5; }
    
    @Override
    public String getTitle() { return "Inheritance"; }
    
    @Override
    public boolean passed() { return true; }
}
