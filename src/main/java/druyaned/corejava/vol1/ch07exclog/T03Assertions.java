package druyaned.corejava.vol1.ch07exclog;

import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;

/**
 * Testing assertions in Java.
 * To test it use the java-command option:<br><code>
 * -enableassertions:druyaned.corejava...
 * </code>
 */
public class T03Assertions extends Topic {
    
    public T03Assertions(Chapter chapter) {
        super(chapter, 3);
    }
    
    @Override public String title() {
        return "Topic 03 Assertions";
    }
    
    @Override public void run() {
        int L = 24;
        System.out.printf("%" + L + "s: %s\n", "SystemClassLoader",
                ClassLoader.getSystemClassLoader());
        System.out.printf("%" + L + "s: %s\n", "TopicAssertions",
                T03Assertions.class.getClassLoader());
        String message = "Negative value is detected. It's time to solve it.";
        try {
            for (int i = 8; i >= -2; --i) {
                int remainder = i % 3;
                assert remainder >= 0
                        : "remainder=" + remainder + "; remainder>=0. " + message;
                System.out.printf("i = %d; i %% 3 = %d\n", i, remainder);
            }
        } catch (AssertionError err) {
            err.printStackTrace();
        }
        System.out.println("End of assertion topic.");
    }
    
}
