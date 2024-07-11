package com.github.druyaned.horstmann.corejava.vol2.ch08.src.p02;

import com.github.druyaned.ConsoleColors;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Announcement {
    
    /**
     * Color of the output message.
     * @return {@code red}, {@code blue}, {@code green}
     *      or anything else that indicates a bold text
     * @see ConsoleColors
     */
    String color() default "";
    
}
