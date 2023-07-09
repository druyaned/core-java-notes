package com.github.druyaned.learn_java.vol2.chapter09;

import static com.github.druyaned.ConsoleColors.*;
import com.github.druyaned.learn_java.Chapterable;

/**
 * Practice implementation of the chapter 9.
 * 
 * @author druyaned
 * @see com.github.druyaned.learn_java.vol2.App
 */
public class Chapter09 implements Chapterable {

    @Override
    public void run() {
        System.out.println(bold("Running Chapter09: Security"));
    }

    @Override
    public int getNumber() { return 9; }
    
    @Override
    public String getTitle() { return "Security"; }
    
    @Override
    public boolean passed() { return false; }

}
