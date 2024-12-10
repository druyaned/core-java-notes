package druyaned.corejava.vol1.ch07;

import static druyaned.ConsoleColors.bold;

/**
 * Testing assertions in Java.
 * To test it use the java-command option:<br><code>
 * -enableassertions:druyaned.corejava.vol1.ch07...
 * </code>
 */
public class TestAssertions {
    
    public static void run() {
        System.out.println("\n" + bold("TestAssertions"));
        int L = 24;
        System.out.printf(
                "%" + L + "s: %s\n", "SystemClassLoader",
                ClassLoader.getSystemClassLoader()
        );
        System.out.printf(
                "%" + L + "s: %s\n", "TestAssertions",
                TestAssertions.class.getClassLoader()
        );
        String message = "Negative value is detected. It's time to solve it.";
        try {
            for (int i = 8; i >= -2; --i) {
                int remainder = i % 3;
                assert remainder >= 0 : "remainder=" + remainder + "; remainder>=0. " + message;
                System.out.printf("i = %d; i %% 3 = %d\n", i, remainder);
            }
        } catch (AssertionError err) {
            err.printStackTrace();
        }
        System.out.println("End of the testing assertions.");
    }
    
}
