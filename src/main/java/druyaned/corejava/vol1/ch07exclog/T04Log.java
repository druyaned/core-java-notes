package druyaned.corejava.vol1.ch07exclog;

import static druyaned.ConsoleColors.blueBold;
import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import druyaned.corejava.util.FileUtil;
import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class T04Log extends Topic {
    
    public T04Log(Chapter chapter) {
        super(chapter, 4);
    }
    
    @Override public String title() {
        return "Topic 04 Log";
    }
    
    @Override public void run() {
        Path logFile = dataDir().resolve("log-file.xml");
        FileHandler fileHandler;
        try {
            FileUtil.createFileOnDemand(logFile);
            fileHandler = new FileHandler(logFile.toString(), false);
        } catch (IOException exc) {
            logger.log(Level.WARNING, "smth wrong with the file {0}", logFile);
            exc.printStackTrace();
            return;
        }
        fileHandler.setLevel(Level.ALL);
        logger.setLevel(Level.FINER);
        logger.setUseParentHandlers(false); // by the default the ConsoleHandler is used
        logger.addHandler(fileHandler);     // check java logging.properties for details
        topicLogManager();
        topicLogger(logFile);
    }
    
    private void topicLogManager() {
        LogManager logManager = LogManager.getLogManager();
        Logger loggerFromManager = logManager.getLogger(CLASS_NAME);
        System.out.println("logger.hash:            " + logger.hashCode());
        System.out.println("loggerFromManager.hash: " + loggerFromManager.hashCode());
    }
    
    private void topicLogger(Path logFile) {
        logger.entering(CLASS_NAME, "testLogger", logFile);
        System.out.println("Logger's level: " + logger.getLevel());
        System.out.println("Logger's handlers:");
        Handler[] handlers = logger.getHandlers();
        for (int i = 0; i < handlers.length; i++) {
            System.out.printf("  %d. %s (level=%s)\n",
                    i + 1, handlers[i], handlers[i].getLevel());
        }
        logger.info("Wow! I've made an info message.");
        logger.log(Level.INFO, "This sentence doesn't bring any useful information.");
        System.out.println("Now check the file " + blueBold(logFile.toString()));
        logger.exiting(CLASS_NAME, "testLogger");
    }
    
    // Now the garbage collector will not remove it
    private static final String CLASS_NAME = T04Log.class.getName();
    private static final Logger logger = Logger.getLogger(CLASS_NAME);
    
}
