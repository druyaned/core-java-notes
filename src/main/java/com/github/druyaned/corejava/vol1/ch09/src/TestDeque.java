package com.github.druyaned.corejava.vol1.ch09.src;

import static com.github.druyaned.ConsoleColors.blueBold;
import static com.github.druyaned.ConsoleColors.bold;
import static com.github.druyaned.corejava.App.sin;
import com.github.druyaned.corejava.vol1.ch09.src.deque.IntDeque;
import com.github.druyaned.corejava.vol1.ch09.src.deque.IntPrinter;

public class TestDeque {
    
    public static void interactiveTest() {
        System.out.println("\n" + bold("TestIntDeque.InteractiveTest"));
        IntDeque deque = new IntDeque(8);
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
                        Pop first:          \"ppf\"
                        Pop last:           \"ppl\"
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
                        output = Boolean.toString(deque.addFirst(value));
                    }
                    case "adl" -> {
                        int value = Integer.parseInt(parts[1]);
                        output = Boolean.toString(deque.addLast(value));
                    }
                    case "rmf" -> { output = Boolean.toString(deque.removeFirst()); }
                    case "rml" -> { output = Boolean.toString(deque.removeLast()); }
                    case "ppf" -> { output = Integer.toString(deque.popFirst()); }
                    case "ppl" -> { output = Integer.toString(deque.popLast()); }
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
            System.out.println("deque.size=" + deque.size() + "; printing the deque...");
            IntPrinter.print(deque, "_", System.out);
            System.out.print(inputPrompt);
        }
    }
    
}
