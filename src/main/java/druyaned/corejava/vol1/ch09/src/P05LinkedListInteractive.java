package druyaned.corejava.vol1.ch09.src;

import static druyaned.ConsoleColors.blueBold;
import static druyaned.corejava.App.sin;
import druyaned.corejava.vol1.ch09.src.linlist.LinkedList;
import druyaned.corejava.vol1.ch09.src.linlist.Printer;

public class P05LinkedListInteractive implements Runnable {
    
    @Override public void run() {
        LinkedList<Integer> linlist = new LinkedList<>();
        LinkedList.Iterator<Integer> iter = linlist.iterator();
        String menu =
        """
            Menu description:
                Prev value:         \"prv\"
                Next value:         \"nxt\"
                Add value before:   \"avb [INT_VAL]\"
                Add value after:    \"ava [INT_VAL]\"
                Remove previous:    \"rpr\"
                Remove next:        \"rnx\"
                Move back:          \"mvb\"
                Move forward:       \"mvf\"
                Go before first:    \"gof\"
                Go before last:     \"gol\"
                Quit:               \"q\"
        """;
        Printer<Integer> printer = new Printer<>(linlist, "_", null);
        String inputPrompt = menu + blueBold("Input") + "(e.g. q): ";
        System.out.print(inputPrompt);
        String inputLine;
        while (!(inputLine = sin.nextLine()).equals("q")) {
            String[] parts = inputLine.split(" ");
            String command = parts[0];
            String output;
            try {
                switch (command) {
                    case "prv" -> {
                        Integer result = iter.prev();
                        if (result == null) {
                            output = "[null]";
                        } else {
                            output = result.toString();
                        }
                    }
                    case "nxt" -> {
                        Integer result = iter.next();
                        if (result == null) {
                            output = "[null]";
                        } else {
                            output = result.toString();
                        }
                    }
                    case "avb" -> {
                        Integer val = Integer.valueOf(parts[1]);
                        iter.addBefore(val);
                        output = "[none]";
                    }
                    case "ava" -> {
                        Integer val = Integer.valueOf(parts[1]);
                        iter.addAfter(val);
                        output = "[none]";
                    }
                    case "rpr" -> {
                        Integer result = iter.removePrev();
                        if (result == null) {
                            output = "[null]";
                        } else {
                            output = result.toString();
                        }
                    }
                    case "rnx" -> {
                        Integer result = iter.removeNext();
                        if (result == null) {
                            output = "[null]";
                        } else {
                            output = result.toString();
                        }
                    }
                    case "mvb" -> { output = Boolean.toString(iter.moveBack()); }
                    case "mvf" -> { output = Boolean.toString(iter.moveForward()); }
                    case "gof" -> {
                        iter.moveBeforeFirst();
                        output = "[none]";
                    }
                    case "gol" -> {
                        iter.moveAfterLast();
                        output = "[none]";
                    }
                    default -> {
                        System.out.print(inputPrompt);
                        continue;
                    }
                }
            } catch (RuntimeException exc) {
                System.out.print(inputPrompt);
                continue;
            }
            System.out.println("output: " + output);
            System.out.println("linlist.size=" + linlist.size() + "; printing the list...");
            printer.print();
            System.out.print(inputPrompt);
        }
    }
    
}
