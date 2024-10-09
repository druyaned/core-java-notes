package com.github.druyaned.corejava.vol2.ch07.src.p05;

/**
 * Names of countries in English for a graphical interface.
 * @author druyaned
 */
public class Countries_en implements Countries {
    
    @Override public String[] getCountries() {
        return new String[] {
            "Great Britain", "Russia", "Germany", "China", "Spain",
            "India", "Egypt", "Bangladesh", "Portugal"
        };
    }
    
    @Override public String[] getTags() {
        return new String[] {
            "en-GB", "ru-RU", "de-DE", "zh-CN", "es-ES",
            "hi-IN", "ar-EG", "bn-BD", "pt-PT"
        };
    }
    
}
