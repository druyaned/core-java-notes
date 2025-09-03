package druyaned.corejava.vol2.ch01streamapi;

import static druyaned.ConsoleColors.blueBold;
import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

public class T03Reductions extends Topic {
    
    public T03Reductions(Chapter chapter) {
        super(chapter, 3);
    }
    
    @Override public String title() {
        return "Toopic 03 Reductions";
    }
    
    @Override public void run() {
        // Optional.empty, Optional.ifPresentOrElse
        Optional.empty().ifPresentOrElse(
                (v) -> System.out.println(blueBold("Optional") + "=" + v),
                () -> System.out.println(blueBold("Optional") + " is empty")
        );
        // show bros
        final Bro[] bros = Bro.getBros();
        System.out.print(blueBold("Bros") + ":");
        Stream.of(bros)
                .sorted(Comparator.reverseOrder())
                .forEachOrdered((bro) -> System.out.print(" " + bro));
        // min, max, Optional.get
        System.out.print("\n" + blueBold("Min bro") + ": ");
        System.out.println(Stream.of(bros)
                .min(Comparator.naturalOrder())
                .get());
        System.out.print(blueBold("Max bro") + ": ");
        System.out.println(Stream.of(bros)
                .max(Comparator.naturalOrder())
                .get());
        // findFirst, findAny (parallel)
        System.out.print(blueBold("Find 1st with 1st \"B\"") + ": ");
        Stream.of(bros)
                .filter((bro) -> bro.getPogremuha().startsWith("B"))
                .findFirst()
                .ifPresentOrElse(
                        (v) -> System.out.println(v),
                        () -> System.out.println("no matches")
                );
        System.out.print(blueBold("Find any with 1st \"G\"") + ": ");
        Stream.of(bros)
                .filter((bro) -> bro.getPogremuha().startsWith("G"))
                .findAny()
                .ifPresentOrElse(
                        (v) -> System.out.println(v),
                        () -> System.out.println("no matches")
                );
        // noneMatch (anyMatch is opposite)
        System.out.print(blueBold("Any of the bros do not start with the 1st \"S\"") + ": ");
        System.out.println(Stream.of(bros)
                .noneMatch((bro) -> bro.getPogremuha().startsWith("S")));
    }
    
}
