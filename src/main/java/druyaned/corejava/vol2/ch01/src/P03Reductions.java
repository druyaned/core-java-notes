package druyaned.corejava.vol2.ch01.src;

import static druyaned.ConsoleColors.*;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Part 3 of the chapter 1 to practice with {@code reductions} and {@code Optional}.
 * <P><i>Reductions</i><ul>
 *  <li>methods: count, min, max, findFirst, findAny (parallel), allMatch, noneMatch;</li>
 *  <li>some Optional methods.</li>
 * </ul>
 * 
 * @author druyaned
 */
public class P03Reductions implements Runnable {
    
    @Override public void run() {
        System.out.println("\n" + bold("Part 03 Reductions"));
        // Optional.empty, Optional.ifPresentOrElse
        Optional.empty().ifPresentOrElse(
                (o) -> System.out.println(blueBold("Optional") + "=" + o),
                () -> System.out.println(blueBold("Optional") + " is empty")
        );
        // show bros
        final Bro[] bros = getBros();
        System.out.print(blueBold("Bros") + ":");
        Stream.of(bros)
                .sorted(Comparator.reverseOrder())
                .forEachOrdered((bro) -> System.out.print(" " + bro.getPogremuha()));
        // min, max, Optional.get
        System.out.print("\n" + blueBold("Min bro") + ": ");
        System.out.println(
                Stream.of(bros).min(Comparator.naturalOrder()).get().getPogremuha()
        );
        System.out.print(blueBold("Max bro") + ": ");
        System.out.println(
                Stream.of(bros).max(Comparator.naturalOrder()).get().getPogremuha()
        );
        // findFirst, findAny (parallel)
        System.out.print(blueBold("Find 1st with 1st \"B\"") + ": ");
        Stream.of(bros)
                .filter((bro) -> bro.getPogremuha().startsWith("B"))
                .findFirst()
                .ifPresentOrElse(
                        (o) -> System.out.println(o.getPogremuha()),
                        () -> System.out.println("no matches")
                );
        System.out.print(blueBold("Find any with 1st \"G\"") + ": ");
        Stream.of(bros)
                .filter((bro) -> bro.getPogremuha().startsWith("G"))
                .findAny()
                .ifPresentOrElse(
                        (o) -> System.out.println(o.getPogremuha()),
                        () -> System.out.println("no matches")
                );
        // noneMatch (anyMatch is opposite)
        System.out.print(blueBold("Any of the bros do not start with the 1st \"S\"") + ": ");
        System.out.println(
                Stream.of(bros).noneMatch((bro) -> bro.getPogremuha().startsWith("S"))
        );
    }
    
    public static Bro[] getBros() {
        Bro[] bros = {
            new Bro("Biloy", Bro.Authority.TOP),
            new Bro("Gora", Bro.Authority.LOW),
            new Bro("Pishniy", Bro.Authority.MID),
            new Bro("Grachok", Bro.Authority.TOP),
            new Bro("Griha", Bro.Authority.MID)
        };
        return bros;
    }
    
}
