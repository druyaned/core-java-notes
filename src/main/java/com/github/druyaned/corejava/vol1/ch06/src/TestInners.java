package com.github.druyaned.corejava.vol1.ch06.src;

import static com.github.druyaned.ConsoleColors.bold;
import java.util.Arrays;
import java.util.Comparator;

import com.github.druyaned.corejava.vol1.ch05.ClassAnalyzer;

public class TestInners {
    
    private final int variable = 5; // To test TestInners.this.variable

    public static void run() {
        System.out.println("\n" + bold("TestInners"));
        // Show outer
        ClassAnalyzer.showClass(TestInners.class);
        // Using 2 inner classes
        TestInners testInners = new TestInners();
        RegionInner1 region = testInners.new RegionInner1("Region1", 7);
        RepublicInner2 republic = testInners.new RepublicInner2(
            "Republic1", 18, 3, true
        );
        System.out.println("\nrepublic.getInRussia(): " + republic.getInRussia());
        System.out.println("republic.regionsAmount: " + republic.regionsAmount);
        republic.showAdditions();
        region.show();
        republic.show();
        try {
            ClassAnalyzer.showClass(RegionInner1.class);
            ClassAnalyzer.showClass(RepublicInner2.class);
        } catch (SecurityException e) {
            throw new RuntimeException(e);
        }
        int basicAge = 5;
        testLocalClass(basicAge);
    }

    public static void testLocalClass(int basicAge) {
        System.out.println("\ntestLocalClass:");
        // Local class
        class PersonLocalClass implements Comparable<PersonLocalClass> {
            private final String name;
            private final int age;
            public PersonLocalClass(String name, int age) {
                this.name = name;
                this.age = basicAge + age;
            }
            @Override public String toString() {
                return "PersonLocalClass{name=" + name + ", age=" + age + "}";
            }
            @Override public int compareTo(PersonLocalClass otherPerson) {
                return Integer.compare(age, otherPerson.age);
            }
        }
        PersonLocalClass[] persons = new PersonLocalClass[3];
        persons[0] = new PersonLocalClass("name1", 5);
        persons[1] = new PersonLocalClass("name2", 6);
        persons[2] = new PersonLocalClass("name3", 3);
        // Anonymous class
        Comparator<PersonLocalClass> byNameAnonymousClass = new Comparator<>() {
            @Override public int compare(PersonLocalClass p1, PersonLocalClass p2) {
                return p2.name.compareTo(p1.name);
            }
        };
        Arrays.sort(persons, byNameAnonymousClass);
        for (PersonLocalClass person : persons) {System.out.println(person);}
        // Using nested class
        ArrayAlg.PairNestedClass<PersonLocalClass> personsPair = ArrayAlg.minmax(persons);
        System.out.println("Min person: " + personsPair.getFirst());
        System.out.println("Max person: " + personsPair.getSecond());
        try {
            ClassAnalyzer.showClass(byNameAnonymousClass.getClass());
            ClassAnalyzer.showClass(PersonLocalClass.class);
            ClassAnalyzer.showClass(personsPair.getClass());
        } catch (SecurityException e) {
            throw new RuntimeException(e);
        }
    }

    // Inner class 1
    public class RegionInner1 {
        private final String name;
        private final int citiesAmount;
        public RegionInner1(String name, int citiesAmount) {
            this.name = name;
            this.citiesAmount = citiesAmount;
        }
        public String getName() {return name;}
        public int getCitiesAmount() {return citiesAmount;}
        public void show() {
            System.out.println(
                "[name=" + name + ", citiesAmount=" + citiesAmount + "]"
            );
        }
    }

    // Inner class 2
    private class RepublicInner2 extends RegionInner1 {
        private final int regionsAmount;
        private final boolean inRussia;
        private RepublicInner2(
                String name, int citiesAmount, int regionsAmount, boolean inRussia
        ) {
            super(name, citiesAmount);
            this.regionsAmount = regionsAmount;
            this.inRussia = inRussia;
        }
        public boolean getInRussia() { return inRussia; }
        private void showAdditions() {
            System.out.println(
                getName() + ":[regionsAmount=" + regionsAmount +
                ", inRussia=" + inRussia +
                ", TestInners.this.variable=" + TestInners.this.variable + "]"
            );
        }
        @Override public void show() {
            System.out.println(
                "[name=" + getName() +
                ", citiesAmount=" + getCitiesAmount() +
                ", regionsAmount=" + regionsAmount +
                ", inRussia=" + inRussia + "]"
            );
        }
    }
}

class ArrayAlg {
    
    public static <T extends Comparable<T>> PairNestedClass<T> minmax(T[] arr) {
        T min = arr[0];
        T max = arr[0];
        for (int i = 1; i < arr.length; ++i) {
            if (arr[i].compareTo(min) < 0) { min = arr[i]; }
            if (arr[i].compareTo(max) > 0) { max = arr[i]; }
        }
        return new PairNestedClass<>(min, max);
    }
    
    // Static inner (nested) class
    public static class PairNestedClass<T> {
        private final T first;
        private final T second;
        public PairNestedClass(T first, T second) {
            this.first = first;
            this.second = second;
        }
        public T getFirst() { return first; }
        public T getSecond() { return second; }
    }
    
}
