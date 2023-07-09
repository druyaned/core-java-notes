package com.github.druyaned.learn_java.vol2.chapter08;

import com.github.druyaned.ConsoleColors;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TODO: write description
 * 
 * @author druyaned
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface P02Announcement {
    
    /**
     * Color of the output message.
     * 
     * @return {@link ConsoleColors#RED "red"}, {@link ConsoleColors#GREEN "green"},
     *         {@link ConsoleColors#PURPLE "purple"}
     *         or anything else that indicates a bold text.
     * @see ConsoleColors
     */
    String color() default "";

}
