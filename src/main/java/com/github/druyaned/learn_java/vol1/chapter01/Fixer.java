package com.github.druyaned.learn_java.vol1.chapter01;

import static com.github.druyaned.ConsoleColors.bold;
import com.github.druyaned.learn_java.util.Strings;
import com.github.druyaned.learn_java.util.src.SrcLineMatcher;
import com.github.druyaned.learn_java.util.src.SrcLineReplacer;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author druyaned
 */
public class Fixer {
    public static void run() {
        System.out.println("\n" + bold("Running Fixer."));
        
        try {
            String pattern = "(.*public boolean passed\\(\\) \\{ return )(false)(; }\\n)";
            Consumer<Strings> matcher = (matched) -> {
                Matcher m = Pattern.compile(pattern).matcher(matched.get(0));
                if (m.matches()) {
                    System.out.println(m.group(2));
                }
            };
            SrcLineMatcher.run(1, new Strings(pattern), matcher);
            Function<Strings, Strings> replacer = (matched) -> {
                Matcher m = Pattern.compile(pattern).matcher(matched.get(0));
                if (m.matches()) {
                    return new Strings(m.group(1) + "true" + m.group(3));
                } else {
                    return matched;
                }
            };
            SrcLineReplacer.run(1, new Strings(pattern), replacer);
        } catch (IOException ex) {
            Logger.getLogger(Fixer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
