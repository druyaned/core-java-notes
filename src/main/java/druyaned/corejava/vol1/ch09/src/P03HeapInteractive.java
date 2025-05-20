package druyaned.corejava.vol1.ch09.src;

import static druyaned.ConsoleColors.blueBold;
import static druyaned.corejava.App.sin;
import druyaned.corejava.vol1.ch09.src.heap.HeapInt;
import druyaned.corejava.vol1.ch09.src.heap.PrinterInt;

public class P03HeapInteractive implements Runnable {
    
    @Override public void run() {
        HeapInt heap = new HeapInt(4, Integer::compare);
        String menu =
        """
            Menu description:
                Get root:       \"g\"
                Add value:      \"a [INT_VAL]\"
                Remove root:    \"r\"
                Quit:           \"q\"
        """;
        PrinterInt printer = new PrinterInt(heap, "_", null);
        String inputPrompt = menu + blueBold("Input") + "(e.g. q): ";
        System.out.print(inputPrompt);
        String inputLine;
        while (!(inputLine = sin.nextLine()).equals("q")) {
            String[] parts = inputLine.split(" ");
            String command = parts[0];
            String output;
            try {
                switch (command) {
                    case "g" -> { output = Integer.toString(heap.root()); }
                    case "a" -> {
                        int value = Integer.parseInt(parts[1]);
                        heap.add(value);
                        output = "[void]";
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
            System.out.println("heap.size=" + heap.size() + "; heap.capacity=" + heap.capacity());
            System.out.println("Printing the heap...");
            printer.print();
            System.out.print(inputPrompt);
        }
    }
    
}
