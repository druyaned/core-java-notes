package com.github.druyaned.horstmann.corejava.vol1.ch08.src;

public class StringSayer extends Sayer<String> {
    
    @Override // A little fake
    public String say(String message) {
        System.out.println("Saying: " + message);
        return message;
    }
    
}
