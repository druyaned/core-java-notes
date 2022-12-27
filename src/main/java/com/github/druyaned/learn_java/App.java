package com.github.druyaned.learn_java;

import com.github.druyaned.learn_java.vol1.Volume1;
import com.github.druyaned.learn_java.vol2.Volume2;
import java.util.Scanner;

/**
 * Practice implementation of learning Java by the Horstmann's book (tenth edition).
 * <p>
 * There are 2 volumes. In each there are chapters with different parts of implementation.
 * For example, in the chapter titled "Inheritance" there are some classes that were written
 * by the author to practice with it.
 *
 * @author druyaned
 * @see Volume1
 * @see Volume2
 */
public class App {
    
    /** An app {@link Scanner scanner} for the {@link System#in}. */
    public static final Scanner APP_IN = new Scanner(System.in);
    
    /**
     * Runs 1 of 2 volumes to choose the chapter.
     * 
     * @param args one number {@code 1} or {@code 2} which defines the volume to run.
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Run the app with the argument 1 or 2.");
            return;
        }
        switch (args[0]) {
            case "1" -> new Volume1().run();
            case "2" -> new Volume2().run();
            default -> System.out.println("Run the app with the argument 1 or 2.");
        }
    }
}
