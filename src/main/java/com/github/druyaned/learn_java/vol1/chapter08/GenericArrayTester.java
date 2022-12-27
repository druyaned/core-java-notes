package com.github.druyaned.learn_java.vol1.chapter08;

import static com.github.druyaned.ConsoleColors.bold;

public class GenericArrayTester {
    public static void run() {
        System.out.println("\n" + bold("Testing generic array."));

        GenericArray<String> strings = new GenericArray<>();
        System.out.println(strings);
        strings.forEach(System.out::println);
        
        strings = GenericArray.from("str1", "str2", "str3");
        System.out.println(strings);
        strings.forEach(System.out::println);
        System.out.println("strings.get(0)=" + strings.get(0));
        System.out.println("strings.get(-1)=" + strings.get(-1));
        System.out.println("strings.get(3)=" + strings.get(3));

        strings = new GenericArray<>();
        for (int i = 0; i < GenericArray.MIN_CAPACITY; ++i) {
            strings.add("str" + (i + 1));
        }
        System.out.println(strings);
        strings.forEach(System.out::println);

        strings.add("str" + (GenericArray.MIN_CAPACITY + 1));
        System.out.println(strings);
        strings.forEach(System.out::println);

        try {
            strings = new GenericArray<>(GenericArray.MAX_CAPACITY + 1);
            System.out.println("strings:\n" + strings);
        } catch (CapacityException e) {
            e.printStackTrace();
        }
    }
}
