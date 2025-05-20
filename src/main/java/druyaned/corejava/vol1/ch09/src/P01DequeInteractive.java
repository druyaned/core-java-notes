package druyaned.corejava.vol1.ch09.src;

import static druyaned.ConsoleColors.blueBold;
import static druyaned.corejava.App.sin;
import druyaned.corejava.vol1.ch09.src.deque.DequeInt;
import druyaned.corejava.vol1.ch09.src.deque.PrinterInt;

public class P01DequeInteractive implements Runnable {
    
    @Override public void run() {
        DequeInt deque = new DequeInt(4);
        String menu =
        """
            Menu description:
                Get value by index: \"get [IND]\"
                Get first:          \"gtf\"
                Get fast:           \"gtl\"
                Set value by index: \"set [IND] [INT_VAL]\"
                Clear:              \"clr\"
                Add first:          \"adf [INT_VAL]\"
                Add last:           \"adl [INT_VAL]\"
                Remove first:       \"rmf\"
                Remove last:        \"rml\"
                Quit:               \"q\"
        """;
        PrinterInt printer = new PrinterInt(deque, "_", null);
        String inputPrompt = menu + blueBold("Input") + "(e.g. q): ";
        System.out.print(inputPrompt);
        String inputLine;
        while (!(inputLine = sin.nextLine()).equals("q")) {
            String[] parts = inputLine.split(" ");
            String command = parts[0];
            String output;
            try {
                switch (command) {
                    case "get" -> {
                        int index = Integer.parseInt(parts[1]);
                        output = Integer.toString(deque.get(index));
                    }
                    case "gtf" -> { output = Integer.toString(deque.getFirst()); }
                    case "gtl" -> { output = Integer.toString(deque.getLast()); }
                    case "set" -> {
                        int index = Integer.parseInt(parts[1]);
                        int value = Integer.parseInt(parts[2]);
                        output = "[none]";
                        deque.set(index, value);
                    }
                    case "clr" -> {
                        output = "[none]";
                        deque.clear();
                    }
                    case "adf" -> {
                        int value = Integer.parseInt(parts[1]);
                        deque.addFirst(value);
                        output = "[void]";
                    }
                    case "adl" -> {
                        int value = Integer.parseInt(parts[1]);
                        deque.addLast(value);
                        output = "[void]";
                    }
                    case "rmf" -> { output = Integer.toString(deque.removeFirst()); }
                    case "rml" -> { output = Integer.toString(deque.removeLast()); }
                    default -> {
                        System.out.print(inputPrompt);
                        continue;
                    }
                }
            } catch (RuntimeException ex) {
                ex.printStackTrace();
                break;
            }
            System.out.println("output: " + output);
            System.out.println("deque.size=" + deque.getSize() + "; printing the deque...");
            printer.print();
            System.out.print(inputPrompt);
        }
    }
    
}
