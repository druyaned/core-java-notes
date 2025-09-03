package druyaned.corejava.vol1.ch09collections.util;

import static druyaned.ConsoleColors.blueBold;
import static druyaned.ConsoleColors.greenBold;
import static druyaned.corejava.App.sin;
import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import druyaned.corejava.vol1.ch09collections.t07rbm.RBM;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class InteractiveTopic<T> extends Topic {
    
    public InteractiveTopic(Chapter chapter, int number) {
        super(chapter, number);
    }
    
    protected RBM<String, Command> commands;
    protected AbstractPrinter<T> printer;
    
    protected abstract T createTarget();
    
    protected abstract void addCommands(T target);
    
    protected abstract void setPrinter(T target);
    
    @Override public void run() {
        // Target, commands and printer
        T target = createTarget();
        commands = new RBM<>(String::compareTo);
        addCommands(target);
        Command command;
        commands.put((command = new Quit()).code(), command);
        setPrinter(target);
        // Menu
        StringBuilder sb = new StringBuilder();
        sb.append(greenBold("Menu description")).append(":\n");
        AtomicInteger maxDescriptionLength = new AtomicInteger(0);
        commands.forEach(node -> {
            int length = node.value().description().length();
            if (maxDescriptionLength.get() < length)
                maxDescriptionLength.set(length);
        });
        commands.forEach(node -> sb.append(String.format(
                "  %-" + maxDescriptionLength.get() + "s: \"%s\"\n",
                node.value().description(),
                node.value().format()
        )));
        String menu = sb.toString();
        // Interractive program
        String inputPrompt = blueBold("Input") + ": ";
        String input;
        System.out.print(menu + inputPrompt);
        try {
            while (!(input = sin.nextLine()).equals("q")) {
                String[] inputParts = input.split(" ");
                String commandCode = inputParts[0];
                RBM.Node<String, Command> commandNode = commands.getNode(commandCode);
                if (commandNode != null)
                    System.out.println("result=" + commandNode.value().run(inputParts));
                System.out.print(menu);
                printer.print();
                System.out.print(inputPrompt);
            }
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            System.out.println("Bye bye :D");
        }
    }
    
}
