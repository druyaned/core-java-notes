package com.github.druyaned.learn_java.vol1.chapter12;

import static com.github.druyaned.ConsoleColors.bold;
import com.github.druyaned.learn_java.Chapterable;

/**
 * Practice implementation of the chapter 12.
 * 
 * @author druyaned
 * @see com.github.druyaned.learn_java.vol1.Volume1
 */
public class Chapter12 implements Chapterable {

    @Override
    public void run() {
        System.out.println(bold("Running Chapter12: GUI"));
        
        TestCalculator.run();
        TestText.run();
        TestMenu.run();
        TestGridBag.run();
        TestDialog.run();
        TestUser.run();
    }

    @Override
    public int getNumber() { return 12; }
    
    @Override
    public String getTitle() { return "GUI"; }
    
    @Override
    public boolean passed() { return true; }
}
