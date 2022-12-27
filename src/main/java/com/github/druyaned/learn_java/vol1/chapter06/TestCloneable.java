package com.github.druyaned.learn_java.vol1.chapter06;

import static com.github.druyaned.ConsoleColors.*;

public class TestCloneable {
    
    public static void run() {
        AgedPerson person = new AgedPerson("Nick", 19);
        AgedPerson peLink = person;
        AgedPerson aClone;
        try {
            aClone = person.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(bold("\nTesting clone()"));
        System.out.println("person:\t" + person + " [hash=" + person.hashCode() + "]");
        System.out.println("peLink:\t" + peLink + " [hash=" + peLink.hashCode() + "]");
        System.out.println("aClone:\t" + aClone + " [hash=" + aClone.hashCode() + "]");
    }
}
