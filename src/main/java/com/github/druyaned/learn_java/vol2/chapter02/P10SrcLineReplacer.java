package com.github.druyaned.learn_java.vol2.chapter02;

import static com.github.druyaned.ConsoleColors.*;
import com.github.druyaned.learn_java.util.Strings;
import com.github.druyaned.learn_java.util.src.SrcLineReplacer;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Part 10 of the chapter 2 to practice with {@link SrcLineReplacer}.
 * 
 * @author druyaned
 */
public class P10SrcLineReplacer {
    
    public static void run() {
        System.out.println("\n" + bold("Part 10 SrcLineReplacer"));
        
        try {
            SrcLineReplacer.run(2, new Strings(new String[] {
                "/\\*\\n",
                " \\* Click.*\\n",
                " \\* Click.*\\n",
                " \\*/.*\\n"
            }), null);
            SrcLineReplacer.run(2, new Strings(new String[] {
                "\\h*\\n",
                "package.*\\n"
            }), ((matchedLines) -> matchedLines.from(1, 1)));
            SrcLineReplacer.run(2, new Strings(new String[] {
                "\\h*\\n",
                "\\h*\\n"
            }), ((matchedLines) -> matchedLines.from(1, 1)));
        } catch (IOException ex) {
            Logger.getLogger(P10SrcLineReplacer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
