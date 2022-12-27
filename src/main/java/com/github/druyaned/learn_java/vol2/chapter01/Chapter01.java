package com.github.druyaned.learn_java.vol2.chapter01;

import static com.github.druyaned.ConsoleColors.*;
import com.github.druyaned.learn_java.Chapterable;

/**
 * Practice implementation of the chapter 1.
 * 
 * @author druyaned
 * @see com.github.druyaned.learn_java.vol2.App
 */
public class Chapter01 implements Chapterable {

    @Override
    public void run() {
        System.out.println(bold("Running Chapter01: Stream API"));
        
        P01StreamInitialize.run();
        P02Intermediate.run();
        P03Reductions.run();
        P04Collectors.run();
        P05Parallel.run();
    }

    @Override
    public int getNumber() { return 1; }
    
    @Override
    public String getTitle() { return "Stream API"; }
    
    @Override
    public boolean passed() { return true; }
}
