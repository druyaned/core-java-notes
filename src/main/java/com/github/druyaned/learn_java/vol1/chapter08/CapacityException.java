package com.github.druyaned.learn_java.vol1.chapter08;

/**
 * Indicates wrong array capacity.
 */
public class CapacityException extends RuntimeException {
    public CapacityException() {super();}
    public CapacityException(String message) {super(message);}
}
