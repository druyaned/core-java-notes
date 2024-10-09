package com.github.druyaned.corejava.vol1.ch07;

import com.github.druyaned.corejava.Chapter;
import java.nio.file.Path;

/**
 * Practice implementation of the Chapter#07: Exceptions, Assertions, and Logging.
 * 
 * <P><i>Exception Notes</i>:<pre>
 *        Throwable
 *     _______|_______
 *    |               |
 *  Error          Exception
 *              _______|_______
 *             |               |
 *            ...        RuntimeException
 * </pre>
 * <u>Error</u> - internal errors and situations that arise due to a lack of resources
 * in the Java executing system, for example, memory has run out.
 * There is no point in handling such exceptions.<br>
 * <u>Runtime</u> (unchecked) - at the time of compilation, it is not known
 * whether the error will be thrown, i.e. exceptions of this type
 * occur due to programming errors:
 * <ul>
 *      <li>incorrect type conversion;</li>
 *      <li>going outside the array;</li>
 *      <li>an attempt to access an object using an empty null reference.</li><br>
 * </ul>
 * <u>Exception</u> (checked) - compile-time exceptions that occur in the following cases:
 * <ul>
 *      <li>attempt to read when the end of the file is reached;</li>
 *      <li>attempt to open a non-existent file;</li>
 *      <li>an attempt to get an object of the Class type
 *          if a non-existent class is specified in the character string.</li>
 * </ul>
 * 
 * @author druyaned
 */
public class ExceptionsLogging extends Chapter {
    
    /**
     * Creates the Chapter#07: Exceptions, Assertions, and Logging.
     * @param volDataDir the path to the volume's data-directory
     */
    public ExceptionsLogging(Path volDataDir) {
        super(volDataDir, 7);
    }
    
    @Override public String getTitle() {
        return "Exceptions, Assertions, and Logging";
    }
    
    @Override public void run() {
        TestStackTrace.run();
        TestTryCatchTime.run();
        TestAssertions.run();
        TestLog.run(getDataDir());
    }
    
}
