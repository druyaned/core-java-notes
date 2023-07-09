package com.github.druyaned.learn_java.vol2.chapter01;

import static com.github.druyaned.ConsoleColors.*;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Part 3 of the chapter 1 to practice with {@code reductions} and {@code Optional}.
 * 
 * @author druyaned
 */
public class P03Reductions {
    /*
    Reductions:
        - methods: count, min, max, findFirst, findAny (parallel), allMatch, noneMatch
    Some Optional methods.
    */
    
    public static void run() {
        System.out.println("\n" + bold("Part 03 Reductions"));
        
        // Optional.empty, Optional.ifPresentOrElse
        Optional.empty()
                .ifPresentOrElse((o) -> System.out.println(blueBold("optional") + "=" + o),
                                 () -> System.out.println(blueBold("optional") + " is empty"));
        
        final Bro[] bros = getBros();
        System.out.print(blueBold("Bros") + ":");
        Stream.of(bros)
                .sorted(Comparator.reverseOrder())
                .forEachOrdered((bro) -> System.out.print(" " + bro.getPogremuha()));
        
        // min, max, Optional.get
        System.out.print("\n" + blueBold("Min bro") + ": ");
        System.out.println(Stream.of(bros).min(Comparator.naturalOrder()).get().getPogremuha());
        System.out.print(blueBold("Max bro") + ": ");
        System.out.println(Stream.of(bros).max(Comparator.naturalOrder()).get().getPogremuha());
        
        // findFirst, findAny (parallel)
        System.out.print(blueBold("Find 1st with 1st \"B\"") + ": ");
        Stream.of(bros)
                .filter((bro) -> bro.getPogremuha().startsWith("B"))
                .findFirst()
                .ifPresentOrElse((o) -> System.out.println(o.getPogremuha()),
                                 () -> System.out.println("no matches"));
        System.out.print(blueBold("Find any with 1st \"G\"") + ": ");
        Stream.of(bros)
                .filter((bro) -> bro.getPogremuha().startsWith("G"))
                .findAny()
                .ifPresentOrElse((o) -> System.out.println(o.getPogremuha()),
                                 () -> System.out.println("no matches"));
        
        // noneMatch
        System.out.print(blueBold("Do not all bros start with 1st \"S\"") + ": ");
        System.out.println(Stream.of(bros).noneMatch((bro) -> bro.getPogremuha().startsWith("S")));
    }
    
    public static Bro[] getBros() {
        Bro[] bros = {
            new Bro("Pishniy", Bro.Authority.MID),
            new Bro("Gora", Bro.Authority.LOW),
            new Bro("Griha", Bro.Authority.MID),
            new Bro("Biloy", Bro.Authority.TOP),
            new Bro("Grachok", Bro.Authority.TOP)
        };
        return bros;
    }

}
