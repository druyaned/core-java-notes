package com.github.druyaned.learn_java.vol2.chapter02;

import static com.github.druyaned.ConsoleColors.*;
import com.github.druyaned.learn_java.util.Strings;
import com.github.druyaned.learn_java.util.src.SrcLineMatcher;
import com.github.druyaned.learn_java.util.src.SrcLineReplacer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Part 11 of the chapter 2 to practice with {@link SrcLineMatcher}.
 * 
 * @author druyaned
 */
public class P11SrcLineMatcher {
    
    public static void run() {
        System.out.println("\n" + bold("Part 10 P11SrcLineMatcher"));
        
        try {
            String titleRegex = "\\h*public String getTitle\\(\\) \\{ return \"(.*)\"; }\\n";
            String startRegex = "(\\h*System.out.println" +
                                "\\(bold\\(\"Running Chapter[01][0-9])(\"\\)\\);\\n)";
            List<String> titles = new ArrayList<>();
            SrcLineMatcher.run(2, new Strings(titleRegex),
                               matchedLines -> titles.add(matchedLines.get(0)));
            
            Iterator<String> titleIter = titles.iterator();
            final Pattern titlePattern = Pattern.compile(titleRegex);
            final Pattern startPattern = Pattern.compile(startRegex);
            Function<Strings, Strings> replacer = (matchedLines) -> {
                String title = titleIter.next();
                String start = matchedLines.get(0);
                Matcher titleMatcher = titlePattern.matcher(title);
                Matcher startMatcher = startPattern.matcher(start);
                if (titleMatcher.matches() && startMatcher.matches()) {
                    String toReplace = startMatcher.group(1) + ": " +
                                       titleMatcher.group(1) +
                                       startMatcher.group(2);
                    return new Strings(toReplace);
                }
                return matchedLines;
            };
            SrcLineReplacer.run(2, new Strings(startRegex), replacer);
            
        } catch (IOException ex) {
            Logger.getLogger(P10SrcLineReplacer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
