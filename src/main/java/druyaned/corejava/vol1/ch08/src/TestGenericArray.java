package druyaned.corejava.vol1.ch08.src;

import static druyaned.ConsoleColors.bold;

public class TestGenericArray {
    
    public static void run() {
        System.out.println("\n" + bold("TestGenericArray"));
        try {
            GenericArray<String> strings = new GenericArray<>();
            System.out.println("strings: " + strings);
            strings = GenericArray.from("str1", "str2", "str3");
            System.out.println("strings: " + strings);
            System.out.println("strings.get(0)=" + strings.get(0));
            System.out.println("strings.get(2)=" + strings.get(3));
            strings = new GenericArray<>();
            for (int i = 1; i <= GenericArray.MIN_CAPACITY; i++) {
                strings.add("str" + i);
            }
            System.out.println("strings: " + strings);
            System.out.println("strings.forEach:");
            strings.forEach(s -> System.out.println("  " + s));
            strings.add("str" + (GenericArray.MIN_CAPACITY + 1));
            System.out.println("strings: " + strings);
            strings = new GenericArray<>(GenericArray.MAX_CAPACITY + 1);
            System.out.println("strings: " + strings);
        } catch (RuntimeException exc) {
            exc.printStackTrace();
        }
    }
    
}
