package druyaned.corejava.vol1.ch14.src.loop;

import static druyaned.ConsoleColors.bold;
import static druyaned.corejava.App.sin;
import static druyaned.corejava.vol1.ch14.src.loop.TestLoop.DURATION_MILLIS;
import static druyaned.corejava.vol1.ch14.src.loop.TestLoop.getTask;

public class TestLoopInteractive implements Runnable {
    
    @Override public void run() {
        System.out.println("\n" + bold("Test Loop Interactive"));
        String input, menu =
        """
        Menu:
            1.  "r" - resume.
            2.  "p" - pause.
            3.  "t" - timed pause.
            4.  "s" - stop.""";
        try {
            Loop loop = new Loop();
            loop.start();
            loop.setTask(getTask());
            do {
                System.out.println(menu);
                switch (input = sin.nextLine()) {
                    case "r" -> System.out.println("Result: " + loop.resume());
                    case "p" -> System.out.println("Result: " + loop.pause());
                    case "t" -> System.out.println("Result: " + loop.pause(DURATION_MILLIS * 4));
                    case "s" -> System.out.println("Result: " + loop.stop());
                }
            } while (!input.equals("s"));
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
    
}
