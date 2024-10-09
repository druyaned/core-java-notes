package com.github.druyaned.corejava.vol1.ch08.src;

public class Sayer<T> {
    
    public String say(T message) {
        System.out.println("Saying: " + message.toString());
        return message.toString();
    }
    
}