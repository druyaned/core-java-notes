package com.github.druyaned.corejava.vol1.ch09.src;

import static com.github.druyaned.ConsoleColors.blueBold;
import static com.github.druyaned.ConsoleColors.bold;
import static com.github.druyaned.corejava.App.sin;
import com.github.druyaned.corejava.vol1.ch09.src.linlist.Iterator;
import com.github.druyaned.corejava.vol1.ch09.src.linlist.LinkedList;
import com.github.druyaned.corejava.vol1.ch09.src.linlist.Printer;

public class TestLinkedList {
    
    public static void interactiveTest() {
        System.out.println("\n" + bold("TestLinkedList.InteractiveTest"));
        LinkedList<Integer> linlist = new LinkedList<>();
        Iterator<Integer> iter = linlist.iterator();
        String menu =
                """
                    Menu description:
                        Previous value:     \"prv\"
                        Next value:         \"nxt\"
                        Add value before:   \"avb [INT_VAL]\"
                        Add value after:    \"ava [INT_VAL]\"
                        Remova previous:    \"rpr\"
                        Remova next:        \"rnx\"
                        Move back:          \"mvb\"
                        Move forward:       \"mvf\"
                        Go before first:    \"gof\"
                        Go before last:     \"gol\"
                        Quit:               \"q\"
                """;
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
                        Integer result = iter.previous();
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
                        Integer result = iter.removePrevious();
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
            Printer.print(linlist, "_", System.out);
            System.out.print(inputPrompt);
        }
    }
    
}
