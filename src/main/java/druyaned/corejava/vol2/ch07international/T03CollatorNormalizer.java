package druyaned.corejava.vol2.ch07international;

import static druyaned.ConsoleColors.blueBold;
import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import java.text.Collator;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.Locale;

public class T03CollatorNormalizer extends Topic {
    
    public T03CollatorNormalizer(Chapter chapter) {
        super(chapter, 3);
    }
    
    @Override public String title() {
        return "Topic 03 CollatorNormalizer";
    }
    
    @Override public void run() {
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
