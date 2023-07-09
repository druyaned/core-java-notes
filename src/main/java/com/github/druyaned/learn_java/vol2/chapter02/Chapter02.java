package com.github.druyaned.learn_java.vol2.chapter02;

import static com.github.druyaned.ConsoleColors.*;
import com.github.druyaned.learn_java.Chapterable;

/**
 * Practice implementation of the chapter 2.
 * 
 * @author druyaned
 * @see com.github.druyaned.learn_java.vol2.App
 */
public class Chapter02 implements Chapterable {
    
    @Override
    public void run() {
        System.out.println(bold("Running Chapter02: Input-Output"));
        
        P01TextIO.run();
        P02BinData.run();
        P03RandomAccess.run();
        P04Zip.run();
        P05ObjectIO.run();
        P06FileManipulation.run();
        P07FileMap.run();
        P08RegEx.run();
        P09Walker.run();
        P10SrcLineReplacer.run();
        P11SrcLineMatcher.run();
    }

    @Override
    public int getNumber() { return 2; }
    
    @Override
    public String getTitle() { return "Input-Output"; }
    
    @Override
    public boolean passed() { return true; }

}
