package druyaned.corejava.vol2.ch07.src;

import static druyaned.ConsoleColors.*;
import java.text.Collator;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.Locale;

/**
 * Part 3 of the chapter 7 to practice with {@code Collator} and {@code Normalizer}.
 * @author druyaned
 */
public class P03CollatorNormalizer implements Runnable {
    
    @Override public void run() {
        System.out.println("\n" + bold("Running P03 Collator & Normalizer"));
        final String[] words = {
            "crocodile",
            "able",
            "™JustASign",
            "Angstrom",
            "Zulu",
            "Ångström",
            "Tommy",
            "Athens",
            "zebra",
            "Able"
        };
        System.out.println(blueBold("Words befor sorting:"));
        for (String word : words) {
            System.out.println("  " + word);
        }
        Arrays.sort(words);
        System.out.println(blueBold("Words after sorting without collator:"));
        for (String word : words) {
            System.out.println("  " + word);
        }
        System.out.println("Locale: " + Locale.UK);
        Collator collator = Collator.getInstance(Locale.UK);
        collator.setStrength(Collator.PRIMARY); // Å
        collator.setDecomposition(Collator.FULL_DECOMPOSITION); // ™
        Arrays.sort(words, collator);
        System.out.println(blueBold("Words after sorting with collator:"));
        for (String word : words) {
            System.out.println("  " + word);
        }
        String wordWithTradeMark = "™JustASign";
        String normalizedWord = Normalizer.normalize(wordWithTradeMark, Normalizer.Form.NFKD);
        System.out.println(blueBold("Word with the trademark") + ": " + wordWithTradeMark);
        System.out.println(blueBold("Normalized word") + ": " + normalizedWord);
    }
    
}
