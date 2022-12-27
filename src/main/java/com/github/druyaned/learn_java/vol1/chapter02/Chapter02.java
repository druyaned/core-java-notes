package com.github.druyaned.learn_java.vol1.chapter02;

import static com.github.druyaned.ConsoleColors.*;
import com.github.druyaned.learn_java.Chapterable;

/**
 * Practice implementation of the chapter 2.
 * 
 * @author druyaned
 * @see com.github.druyaned.learn_java.vol1.Volume1
 */
public class Chapter02 implements Chapterable {

    @Override
    public void run() {
        System.out.println(bold("Running Chapter02: Programming Environment"));
    }

    @Override
    public int getNumber() { return 2; }
    
    @Override
    public String getTitle() { return "Programming Environment"; }
    
    @Override
    public boolean passed() { return false; }
}
