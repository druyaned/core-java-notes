package com.github.druyaned.corejava.vol2.ch08.src;

import static com.github.druyaned.ConsoleColors.*;
import java.util.List;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Part 1 of the chapter 8 to practice with {@code Scripting Engine}.
 * @author druyaned
 */
public class P01ScriptingEngine implements Runnable {
    
    @Override public void run() {
        System.out.println("\n" + bold("Running P01 ScriptingEngine"));
        ScriptEngineManager manager = new ScriptEngineManager();
        List<ScriptEngineFactory> factories = manager.getEngineFactories();
        if (factories.isEmpty()) {
            System.out.println("No script factories are found.");
        } else {
            System.out.println("Available engine factories:");
            for (ScriptEngineFactory factory : factories) {
                System.out.println(" ".repeat(2) + factory.getEngineName());
            }
        }
        String engineName = "nashorn";
        ScriptEngine engine = manager.getEngineByName(engineName);
        if (engine == null) {
            System.out.printf("Can't find script engine by name \"%s\".\n", engineName);
        } else {
            try {
                // evaluating a script command
                String goodLine = (String)engine.eval("'It is a' + ' good line.'");
                System.out.println("Result of the script evaluating: " + goodLine);
            } catch (ScriptException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    
}
