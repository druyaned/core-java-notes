package druyaned.corejava.vol1.ch14.src.states;

import java.util.List;

public class Utils {
    
    public static final long DELAY_MILLIS = 128L;
    private static int printCounter = 1;
    private static final StringBuilder builder = new StringBuilder();
    
    public static void resetPrintCounter() {
        printCounter = 1;
    }
    
    public static void printStates(List<? extends Thread> threads) {
        builder.setLength(0);
        // maxLength
        int maxLength = threads.get(0).getState().toString().length();
        for (int i = 1; i < threads.size(); i++) {
            int stateLength = threads.get(i).getState().toString().length();
            if (maxLength < stateLength) {
                maxLength = stateLength;
            }
        }
        for (Thread thread : threads) {
            int nameLength = thread.getName().length();
            if (maxLength < nameLength) {
                maxLength = nameLength;
            }
        }
        String format = "%" + maxLength + "s";
        String padding = "-".repeat(maxLength);
        // zero line
        builder.append("+-").append(padding);
        for (int i = 1; i < threads.size(); i++) {
            builder.append("-+-").append(padding);
        }
        builder.append("-+\n");
        String separator = builder.toString();
        // 1st line
        builder.append("| ").append(formattedName(format, threads.get(0)));
        for (int i = 1; i < threads.size(); i++) {
            builder.append(" | ").append(formattedName(format, threads.get(i)));
        }
        builder.append(" |\n");
        // 2nd line
        builder.append(separator);
        // 3rd lines
        builder.append("| ").append(formattedState(format, threads.get(0)));
        for (int i = 1; i < threads.size(); i++) {
            builder.append(" | ").append(formattedState(format, threads.get(i)));
        }
        builder.append(" |\n");
        // 4th line
        builder.append(separator);
        // prefix
        builder.insert(0, String.format("Print#%02d\n", printCounter++));
        // print
        System.out.print(builder.toString());
    }
    
    public static void currentThreadSleep(long durationMillis) {
        try {
            Thread.sleep(durationMillis);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
    
    public static void currentThreadJoin(Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
    
    public static void trackEnter(String methodName) {
        System.out.printf("\"%s\" ENTERS \"%s\"\n",
                Thread.currentThread().getName(), methodName);
    }
    
    public static void trackExit(String methodName) {
        System.out.printf("\"%s\" EXITS \"%s\"\n",
                Thread.currentThread().getName(), methodName);
    }
    
    private static String formattedName(String format, Thread thread) {
        return String.format(format, thread.getName());
    }
    
    private static String formattedState(String format, Thread thread) {
        return String.format(format, thread.getState().toString());
    }
    
}
