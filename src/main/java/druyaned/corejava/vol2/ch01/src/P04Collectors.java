package druyaned.corejava.vol2.ch01.src;

import static druyaned.ConsoleColors.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Part 4 of the chapter 1 to practice with {@code collectors}.
 * @author druyaned
 */
public class P04Collectors implements Runnable {
    
    @Override public void run() {
        System.out.println("\n" + bold("Part 04 Collectors"));
        final Bro[] bros = P03Reductions.getBros();
        // List
        List<Bro> listOfBros = Stream.of(bros).collect(
                ArrayList<Bro>::new, // creates several lists (parallel)
                ArrayList<Bro>::add, // adds an element to a list
                ArrayList<Bro>::addAll // merges all lists
        ); // Collectors.toList()
        System.out.print(blueBold("listOfBros") + ":");
        listOfBros.forEach((bro) -> System.out.print(" " + bro.getPogremuha()));
        // Set
        Set<Bro> setOfBros = Stream.of(bros).collect(Collectors.toSet());
        System.out.print(blueBold("\nsetOfBros") + ":");
        setOfBros.forEach((bro) -> System.out.print(" " + bro.getPogremuha()));
        // Collectors.toCollection
        ArrayList<Bro> arrayListOfBros = Stream.of(bros).collect(
                Collectors.toCollection(ArrayList::new)
        );
        System.out.print(blueBold("\narrayListOfBros") + ":");
        arrayListOfBros.forEach((bro) -> System.out.print(" " + bro.getPogremuha()));
        // Collectors.joining
        String stringOfBros = Stream.of(bros)
                .map(Bro::getPogremuha)
                .collect(Collectors.joining(", "));
        System.out.println("\n" + blueBold("stringOfBros") + ": " + stringOfBros);
        // Statistics
        IntSummaryStatistics statistics = Stream.of(bros)
                .map(Bro::getPogremuha)
                .collect(Collectors.summarizingInt(String::length));
        System.out.println(
                blueBold("Statistics of length") +
                ": min=" + statistics.getMin() +
                ", max=" + statistics.getMax() +
                ", avg=" + statistics.getAverage() +
                ", cnt=" + statistics.getCount()
        );
        // Collectors.toMap(Function, Finction)
        Map<String, Bro.Authority> mapOfBros = Stream.of(bros).collect(
                Collectors.toMap(Bro::getPogremuha, Bro::getAuthority)
        ); // an exception can be thrown here
        System.out.print(blueBold("mapOfBros") + ":");
        mapOfBros.forEach((pogremuha, authority) ->
                System.out.print(" " + pogremuha + "(" + authority + ")")
        );
        // Collectors.toMap(keyMapper, valueMapper, mergeFunction)
        final String BELARUS = "Belarus";
        Map<String, List<String>> countryToLanguages = Stream.of(Locale.getAvailableLocales()).
                collect(Collectors.<Locale, String, List<String>>toMap(
                        Locale::getDisplayCountry,
                        (l) -> Collections.singletonList(l.getDisplayLanguage()),
                        (v1, v2) -> {
                            List<String> list = new ArrayList<>(v1);
                            list.addAll(v2);
                            return list;
                        }
                ));
        System.out.println("\n" + blueBold(BELARUS) + ": " + countryToLanguages.get(BELARUS));
        // Collectors.groupingBy
        Map<String, List<Locale>> countryToLocales = Stream.of(Locale.getAvailableLocales()).
                collect(Collectors.groupingBy(Locale::getDisplayCountry));
        System.out.println(blueBold(BELARUS) + ": " + countryToLocales.get(BELARUS));
        // Collectors.groupingBy(classifier, mapFactory, downstream)
        Map<String, ArrayList<String>> countryToArrayList = Stream.of(
                Locale.getAvailableLocales()
        ).collect(Collectors.groupingBy(
                Locale::getDisplayCountry,
                HashMap::new,
                Collectors.mapping(
                        Locale::getDisplayLanguage,
                        Collectors.toCollection(ArrayList::new)
                )
        ));
        System.out.println(blueBold(BELARUS) + ": " + countryToArrayList.get(BELARUS));
        // reduce(identity, accumulator, combiner)
        int totalLengthOfBros = Stream.of(bros).reduce(
                0,
                (totLen, bro) -> totLen + bro.getPogremuha().length(),
                (totLen1, totLen2) -> totLen1 + totLen2
        );
        System.out.println(blueBold("totalLengthOfBros") + "=" + totalLengthOfBros);
        // IntStream
        System.out.print(blueBold("Lengths of bros") + ":");
        int totalLength = Stream.of(bros)
                .mapToInt((bro) -> bro.getPogremuha().length())
                .peek((len) -> System.out.print(" " + len))
                .reduce(0, (len1, len2) -> len1 + len2);
        System.out.println("; total=" + totalLength);
    }
    
}
