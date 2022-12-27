package com.github.druyaned.learn_java.vol1.chapter11;

import static com.github.druyaned.ConsoleColors.bold;
import com.github.druyaned.learn_java.Chapterable;

/**
 * Practice implementation of the chapter 11.
 * 
 * @author druyaned
 * @see com.github.druyaned.learn_java.vol1.Volume1
 */
public class Chapter11 implements Chapterable {

    @Override
    public void run() {
        System.out.println(bold("Running Chapter11: Event Handling"));
        
        TestFrameStyle.run();
        TestAction.run();
        TestMouse.run();
    }

    @Override
    public int getNumber() { return 11; }
    
    @Override
    public String getTitle() { return "Event Handling"; }
    
    @Override
    public boolean passed() { return true; }
}
