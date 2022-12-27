package com.github.druyaned.learn_java.vol2.chapter04;

import static com.github.druyaned.ConsoleColors.*;
import com.github.druyaned.learn_java.Chapterable;

/**
 * Practice implementation of the chapter 4.
 * 
 * @author druyaned
 * @see com.github.druyaned.learn_java.vol2.App
 */
public class Chapter04 implements Chapterable {

    @Override
    public void run() {
        System.out.println(bold("Running Chapter04: NetWork"));
        
        P01Socket.run();
        P02Server.run();
        P03Interruptible.run();
        P04URL.run();
        P05POST.run();
        P06Mail.run();
    }

    @Override
    public int getNumber() { return 4; }
    
    @Override
    public String getTitle() { return "NetWork"; }
    
    @Override
    public boolean passed() { return true; }
}
