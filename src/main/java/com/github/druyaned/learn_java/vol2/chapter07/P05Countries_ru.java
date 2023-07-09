package com.github.druyaned.learn_java.vol2.chapter07;

import com.github.druyaned.learn_java.util.Strings;

/**
 * Names of countries in Russian for a graphical interface.
 * 
 * @author druyaned
 */
public class P05Countries_ru implements P05Countries {
    private final Strings countries;
    private final Strings tags;
    
    public P05Countries_ru() {
        countries = new Strings(new String[] {
            "Россия", "Великобритания", "Германия", "Китай", "Испания",
            "Индия", "Египет", "Бангладеш", "Португалия"
        });
        tags = new Strings(new String[] {
            "ru-RU", "en-GB", "de-DE", "zh-CN", "es-ES",
            "hi-IN", "ar-EG", "bn-BD", "pt-PT"
        });
    }
    
    @Override
    public Strings getCountries() { return countries; }
    
    @Override
    public Strings getTags() { return tags; }

}
