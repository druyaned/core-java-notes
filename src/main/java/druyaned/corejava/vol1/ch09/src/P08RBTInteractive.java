package druyaned.corejava.vol1.ch09.src;

import static druyaned.ConsoleColors.blueBold;
import static druyaned.corejava.App.sin;
import druyaned.corejava.vol1.ch09.src.rbt.PrinterIndexed;
import druyaned.corejava.vol1.ch09.src.rbt.RBTIndexed;

public class P08RBTInteractive implements Runnable {
    
    @Override public void run() {
        RBTIndexed tree = new RBTIndexed();
        String menu =
        """
            Menu description:
                Add value:    \"a [INT_VAL]\"
                Remove value: \"r [INT_VAL]\"
                Get indexed:  \"g [INT_VAL]\"
                Quit:         \"q\"
        """;
        PrinterIndexed printer = new PrinterIndexed(tree, "_", null);
        String inputPrompt = menu + blueBold("Input") + "(e.g. q): ";
        System.out.print(inputPrompt);
        String inputLine;
        while (!(inputLine = sin.nextLine()).equals("q")) {
            String[] parts = inputLine.split(" ");
            String command = parts[0];
            String output;
            try {
                Integer val = Integer.valueOf(parts[1]);
                switch (command) {
                    case "a" -> { output = Boolean.toString(tree.add(val)); }
                    case "r" -> { output = Boolean.toString(tree.remove(val)); }
                    case "g" -> {
                        RBTIndexed.IndexedNode indexed = tree.getIndexedNode(val);
                        output = (indexed == null) ? "[null]"
                                : Integer.toString(indexed.node().value())
                                + '[' + indexed.index() + ']'
                                + '(' + indexed.node().leftCount()
                                + '|' + indexed.node().rightCount() + ')';
                    }
                    default -> {
                        System.out.print(inputPrompt);
                        continue;
                    }
                }
            } catch (RuntimeException ex) {
                System.out.print(inputPrompt);
                continue;
            }
            System.out.println("output: " + output);
            System.out.println("tree.size=" + tree.size() + "; printing the tree...");
            printer.print();
            System.out.print("tree:");
            tree.forEach(node -> System.out.print(" " + node.value()));
            System.out.println();
            System.out.print(inputPrompt);
        }
    }
    
}
