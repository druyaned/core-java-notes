package druyaned.corejava.vol2.ch08anno;

import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import java.util.List;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class T01ScriptingEngine extends Topic {
    
    public T01ScriptingEngine(Chapter chapter) {
        super(chapter, 1);
    }
    
    @Override public String title() {
        return "Topic 01 ScriptingEngine";
    }
    
    @Override public void run() {
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
