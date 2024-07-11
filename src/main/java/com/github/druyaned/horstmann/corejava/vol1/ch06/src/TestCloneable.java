package com.github.druyaned.horstmann.corejava.vol1.ch06.src;

import static com.github.druyaned.ConsoleColors.*;

public class TestCloneable {
    
    public static void run() {
        System.out.println("\n" + bold("TestCloneable"));
        AgedPerson person = new AgedPerson("Nick", 19);
        AgedPerson link = person;
        AgedPerson clone;
        try {
            clone = person.clone();
        } catch (CloneNotSupportedException exc) {
            exc.printStackTrace();
            return;
        }
        System.out.println("person: " + person + " [hash=" + person.hashCode() + "]");
        System.out.println("link:   " + link + " [hash=" + link.hashCode() + "]");
        System.out.println("clone:  " + clone + " [hash=" + clone.hashCode() + "]");
    }
    
}
