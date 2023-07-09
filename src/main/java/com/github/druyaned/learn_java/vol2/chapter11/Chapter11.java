package com.github.druyaned.learn_java.vol2.chapter11;

import static com.github.druyaned.ConsoleColors.*;
import com.github.druyaned.learn_java.Chapterable;

/**
 * Practice implementation of the chapter 1.
 * 
 * @author druyaned
 * @see com.github.druyaned.learn_java.vol2.App
 */
public class Chapter11 implements Chapterable {

    @Override
    public void run() {
        System.out.println(bold("Running Chapter11: Advanced AWT-Tools"));
    }

    @Override
    public int getNumber() { return 11; }
    
    @Override
    public String getTitle() { return "Advanced AWT-Tools"; }
    
    @Override
    public boolean passed() { return false; }

}
