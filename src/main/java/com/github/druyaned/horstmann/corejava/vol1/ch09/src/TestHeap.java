package com.github.druyaned.horstmann.corejava.vol1.ch09.src;

import static com.github.druyaned.ConsoleColors.blueBold;
import static com.github.druyaned.ConsoleColors.bold;
import static com.github.druyaned.horstmann.corejava.App.sin;
import com.github.druyaned.horstmann.corejava.vol1.ch09.src.heap.IntHeap;
import com.github.druyaned.horstmann.corejava.vol1.ch09.src.heap.IntPrinter;

public class TestHeap {
    
    public static void interactiveTest() {
        System.out.println("\n" + bold("TestRedBlackTree.InteractiveTest"));
        IntHeap heap = new IntHeap(31);
        String menu =
                """
                    Menu description:
                        Get root:       \"g\"
                        Add value:      \"a [INT_VAL]\"
                        Remove root:    \"r\"
                        Quit:           \"q\"
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
                    case "g" -> { output = Integer.toString(heap.getRoot()); }
                    case "a" -> {
                        int value = Integer.parseInt(parts[1]);
                        output = Boolean.toString(heap.add(value));
                    }
                    case "r" -> { output = Integer.toString(heap.remove()); }
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
            System.out.println("heap.size=" + heap.size() + "; printing the heap...");
            IntPrinter.print(heap, "_", System.out);
            System.out.print(inputPrompt);
        }
    }
    
}
