package druyaned.corejava;

import static druyaned.ConsoleColors.bold;
import java.util.List;
import java.util.Scanner;

/**
 * App of a practical implementation of Horstmann's book
 * "Core Java" (tenth edition); the entry point of the program.
 * 
 * @author druyaned
 * @see Book
 */
public class App {
    
    /** The app {@link Scanner scanner} of the {@link System#in}. */
    public static final Scanner sin = new Scanner(System.in);
    
    /**
     * Creates the {@link Book book} and accepts the volume number
     * which is the only argument of the executable command.
     * @param args the only argument of the executable command
     *      which is the volume number
     */
    public static void main(String[] args) {
        // 1. book
        Book book = new Book();
        // 2. volume
        Volume volume = selectBookItem(args, 0, "Volumes", book.volumes(), "volume");
        if (volume == null)
            return;
        // 3. chapter
        Chapter chapter = selectBookItem(args, 1, "Chapters", volume.chapters(), "chapter");
        if (chapter == null)
            return;
        // 4. topic
        Topic topic = selectBookItem(args, 2, "Topics", chapter.topics(), "topic");
        if (topic == null)
            return;
        // 5. run
        topic.run();
    }
    
    private static <T extends BookItem> T selectBookItem(
            String[] args,
            int argIndex,
            String listName,
            List<T> list,
            String itemName
    ) {
        // select number
        String itemNumberString;
        if (argIndex < args.length) {
            // retrieve from command line arguments
            itemNumberString = args[argIndex];
        } else if (!list.isEmpty()) {
            // retrieve from {@link #sin}
            System.out.println(listName + ":");
            for (int i = 0; i < list.size(); i++)
                System.out.printf("  %2d: %s\n", i + 1, list.get(i).title());
            System.out.printf("Select a %s number: ", itemName);
            itemNumberString = sin.nextLine();
        } else {
            System.out.printf("%s are empty\n", listName);
            return null;
        }
        // validate the number
        int itemNumber;
        try {
            itemNumber = Integer.parseInt(itemNumberString);
        } catch (NumberFormatException exc) {
            System.out.printf("Bad number of %s (given=%s)\n", itemName, itemNumberString);
            return null;
        }
        if (itemNumber < 1 || list.size() < itemNumber) {
            System.out.printf("Number of %s must be in [1, %d]\n", itemName, list.size());
            return null;
        }
        BookItem item = list.get(itemNumber - 1);
        System.out.printf("Selected %s is %s\n", itemName, bold(item.title()));
        return list.get(itemNumber - 1);
    }
    
    /**
     * Returns the name of the project.
     * @return project's name
     */
    public static String projectName() {
        return "core-java-notes";
    }
    
}
