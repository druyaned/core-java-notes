package com.github.druyaned.horstmann.corejava.vol2.ch02.src;

import static com.github.druyaned.ConsoleColors.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Part 8 of the chapter 2 to practice in regular expressions.
 * @author druyaned
 */
public class P08RegEx implements Runnable {
    
    @Override public void run() {
        System.out.println("\n" + bold("Part 08 RegEx"));
        // Greedy and Lazy Quantifiers
        String s = "Greedy (*) and Lazy (Reluctant=\"*?\", Possessive=\"*+\") Quantifiers";
        System.out.println(blueBold(s) + ":");
        String[] stringArray = { "abcd", "adc", "aabbcd", "yeah", "acd", "cd", "bcd", "cd" };
        System.out.print(" ".repeat(2) + "stringArray:");
        for (int i = 0; i < stringArray.length; ++i) {
            System.out.print(" " + stringArray[i]);
        }
        System.out.println();
        final String[] REG_EXS = { "[a-z]+cd", "[a-z]+?cd", "[a-z]++cd" };
        for (int i = 0; i < REG_EXS.length; ++i) {
            System.out.print(" ".repeat(2) + "regEx=\"" + greenBold(REG_EXS[i]) + "\":");
            Pattern pattern = Pattern.compile(REG_EXS[i]);
            for (int j = 0; j < stringArray.length; ++j) {
                if (pattern.matcher(stringArray[j]).matches()) {
                    System.out.print(" \"" + purpleBold(stringArray[j]) + "\"");
                }
            }
            System.out.println();
        }
        // time regular expression with groups
        System.out.println(blueBold("Time regular expression with groups") + ":");
        String timeRegEx = "((0[0-9]|1[0-2]):([0-5][0-9]))[ap]m";
        Pattern timePattern = Pattern.compile(timeRegEx);
        String timeString = "02:47am 11:21pm 12:00am";
        String[] times = timeString.split(" ");
        System.out.println(" ".repeat(2) + "timeRegEx=\"" + greenBold(timeRegEx) + "\":");
        for (int i = 0; i < times.length; ++i) {
            Matcher timeMatcher = timePattern.matcher(times[i]);
            if (!timeMatcher.matches()) {
                continue;
            }
            System.out.println(" ".repeat(4) + purpleBold(times[i]));
            int groupCount = timeMatcher.groupCount();
            for (int j = 0; j <= groupCount; ++j) {
                System.out.println(" ".repeat(6) + "group(" + j + "): " + timeMatcher.group(j));
            }
        }
        // Matcher.find
        System.out.println(blueBold("Matcher.find") + ":");
        System.out.println(" ".repeat(2) + "timeString=\"" + purpleBold(timeString) + "\"");
        Matcher timeMatcher = timePattern.matcher(timeString);
        while (timeMatcher.find()) {
            int start = timeMatcher.start();
            int end = timeMatcher.end();
            System.out.println(
                    " ".repeat(4) + "start=" + start + ", end=" + end +
                    ", substring=" + purpleBold(timeString.substring(start, end))
            );
        }
    }
    
}
