package com.github.druyaned.learn_java.vol1.chapter14;

import static com.github.druyaned.ConsoleColors.*;
import com.github.druyaned.learn_java.Chapterable;

/**
 * Practice implementation of the chapter 14.
 * 
 * @author druyaned
 * @see com.github.druyaned.learn_java.vol1.Volume1
 */
public class Chapter14 implements Chapterable {

    @Override
    public void run() {
        System.out.println(bold("Running Chapter14: Concurrency"));
        
        TestBall.run();
//        TestBank.run();
//        TestBankFixed.run();
//        TestBankSynchronized.run();
//        TestBankQueued.run();
//        TestSearch.run();
        TestBalls.run();
    }

    @Override
    public int getNumber() { return 14; }
    
    @Override
    public String getTitle() { return "Concurrency"; }
    
    @Override
    public boolean passed() { return true; }
}
