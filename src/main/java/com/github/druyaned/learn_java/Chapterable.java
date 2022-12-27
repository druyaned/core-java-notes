package com.github.druyaned.learn_java;

/**
 *
 * @author druyaned
 */
public interface Chapterable extends Runnable {
    
    /**
     * Returns a number of the chapter.
     * 
     * @return a number of the chapter.
     */
    int getNumber();
    
    /**
     * Returns a title of the chapter.
     * 
     * @return a title of the chapter.
     */
    String getTitle();
    
    /**
     * Returns true if the chapter has been passed (read and practiced) and false otherwise.
     * 
     * @return true if the chapter has been passed (read and practiced) and false otherwise.
     */
    boolean passed();
}
