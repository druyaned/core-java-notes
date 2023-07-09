package com.github.druyaned.learn_java.vol2.chapter07;

import com.github.druyaned.learn_java.util.Strings;

/**
 * Names of countries in English for a graphical interface.
 * 
 * @author druyaned
 */
public interface P05Countries {
    
    Strings getCountries();
    
    Strings getTags();
    
    default String[] countriesAsArray() {
        Strings countries = getCountries();
        String[] array = new String[countries.size()];
        for (int i = 0; i < countries.size(); ++i) {
            array[i] = countries.get(i);
        }
        return array;
    }

}
