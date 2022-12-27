package com.github.druyaned.learn_java.vol1.chapter13;

import static com.github.druyaned.ConsoleColors.bold;
import com.github.druyaned.learn_java.Chapterable;

/**
 * Practice implementation of the chapter 13.
 * 
 * @author druyaned
 * @see com.github.druyaned.learn_java.vol1.Volume1
 */
public class Chapter13 implements Chapterable {

    @Override
    public void run() {
        System.out.println(bold("Running Chapter13: App Deploying"));
        
        TestProperties.run();
    }

    @Override
    public int getNumber() { return 13; }
    
    @Override
    public String getTitle() { return "App Deploying"; }
    
    @Override
    public boolean passed() { return true; }
}
