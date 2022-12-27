package com.github.druyaned.learn_java.vol1.chapter06;

import static com.github.druyaned.ConsoleColors.*;
import com.github.druyaned.learn_java.Chapterable;

/**
 * Practice implementation of the chapter 6.
 * 
 * @author druyaned
 * @see com.github.druyaned.learn_java.vol1.Volume1
 */
public class Chapter06 implements Chapterable {

    @Override
    public void run() {
        System.out.println(bold("Running Chapter06: Interfaces, Lambda and Internal Classes"));
        
        TestTimer.run();
        TestCloneable.run();
        TestLambda.stringComparison();
        TestLambda.methodLinks();
        TestInners.run();
    }

    @Override
    public int getNumber() { return 6; }
    
    @Override
    public String getTitle() { return "Interfaces, Lambda and Internal Classes"; }
    
    @Override
    public boolean passed() { return true; }
}
