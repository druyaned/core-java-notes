package druyaned.corejava.vol2.ch08.src;

import druyaned.corejava.vol2.ch08.src.p03.GetteredHandler;
import static druyaned.ConsoleColors.bold;
import java.io.IOException;
import java.io.UncheckedIOException;

/**
 * Part 3 of the chapter 8 to practice with the library {@code ASM}.
 * @author druyaned
 */
public class P03ASM implements Runnable {
    
    @Override public void run() {
        System.out.println("\n" + bold("Running P03 ASM"));
        try {
            GetteredHandler.handlePerson();
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
    }
    
}
