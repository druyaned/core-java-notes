package com.github.druyaned.learn_java.vol2.chapter08;

import static com.github.druyaned.ConsoleColors.bold;
import java.io.IOException;
import java.io.UncheckedIOException;

/**
 * Part 3 of the chapter 8 to practice with the library {@code ASM}.
 * 
 * @author druyaned
 */
public class P03ASM {

    public static void run() {
        System.out.println("\n" + bold("Running P03 ASM"));
        try {
            P03GetteredHandler.handlePerson();
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
    }

}
