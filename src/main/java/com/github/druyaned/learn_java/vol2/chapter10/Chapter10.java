package com.github.druyaned.learn_java.vol2.chapter10;

import static com.github.druyaned.ConsoleColors.*;
import com.github.druyaned.learn_java.Chapterable;

/**
 * Practice implementation of the chapter 0.
 * 
 * @author druyaned
 * @see com.github.druyaned.learn_java.vol2.App
 */
public class Chapter10 implements Chapterable {

    @Override
    public void run() {
        System.out.println(bold("Running Chapter10: Advanced Swing-Tools"));
    }

    @Override
    public int getNumber() { return 10; }
    
    @Override
    public String getTitle() { return "Advanced Swing-Tools"; }
    
    @Override
    public boolean passed() { return false; }

}
