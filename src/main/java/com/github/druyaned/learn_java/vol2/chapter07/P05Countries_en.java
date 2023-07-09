package com.github.druyaned.learn_java.vol2.chapter07;

import com.github.druyaned.learn_java.util.Strings;

/**
 * Names of countries in English for a graphical interface.
 * 
 * @author druyaned
 */
public class P05Countries_en implements P05Countries {
    private final Strings countries;
    private final Strings tags;
    
    public P05Countries_en() {
        countries = new Strings(new String[] {
            "Great Britain", "Russia", "Germany", "China", "Spain",
            "India", "Egypt", "Bangladesh", "Portugal"
        });
        tags = new Strings(new String[] {
            "en-GB", "ru-RU", "de-DE", "zh-CN", "es-ES",
            "hi-IN", "ar-EG", "bn-BD", "pt-PT"
        });
    }
    
    @Override
    public Strings getCountries() { return countries; }
    
    @Override
    public Strings getTags() { return tags; }

}
