package com.github.druyaned.horstmann.corejava.vol1.ch07;

import static com.github.druyaned.ConsoleColors.blueBold;
import static com.github.druyaned.ConsoleColors.bold;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class TestLog {
    
    // now the garbage collector will not remove it
    private static final String CLASS_NAME = TestLog.class.getName();
    public static final Logger logger = Logger.getLogger(CLASS_NAME);
    
    public static void run(Path chapterDataDir) {
        System.out.println("\n" + bold("TestLog"));
        Path logFile = chapterDataDir.resolve("log-file.xml");
        FileHandler fileHandler;
        try {
            if (!Files.exists(logFile)) {
                Files.createFile(logFile);
            }
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
        testLogManager();
        testLogger(logFile);
    }
    
    private static void testLogger(Path logFile) {
        logger.entering(CLASS_NAME, "testLogger", logFile);
        System.out.println("Logger's level: " + logger.getLevel());
        System.out.println("Logger's handlers:");
        Handler[] handlers = logger.getHandlers();
        for (int i = 0; i < handlers.length; i++) {
            System.out.printf(
                    "  %d. %s (level=%s)\n", i + 1, handlers[i], handlers[i].getLevel()
            );
        }
        logger.info("Wow! I've made an info message.");
        logger.log(Level.INFO, "This sentence doesn't bring any useful information.");
        System.out.println("Now check the file " + blueBold(logFile.toString()));
        logger.exiting(CLASS_NAME, "testLogger");
    }
    
    private static void testLogManager() {
        LogManager logManager = LogManager.getLogManager();
        Logger loggerFromManager = logManager.getLogger(CLASS_NAME);
        System.out.println("logger.hash:            " + logger.hashCode());
        System.out.println("loggerFromManager.hash: " + loggerFromManager.hashCode());
    }
    
}
