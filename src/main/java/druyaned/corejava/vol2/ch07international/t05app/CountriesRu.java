package druyaned.corejava.vol2.ch07international.t05app;

/**
 * Names of countries in Russian for a graphical interface.
 * @author druyaned
 */
public class CountriesRu implements Countries {
    
    @Override public String[] getCountries() {
        return new String[] {
            "Россия", "Великобритания", "Германия", "Китай", "Испания",
            "Индия", "Египет", "Бангладеш", "Португалия"
        };
    }
    
    @Override public String[] getTags() {
        return new String[] {
            "ru-RU", "en-GB", "de-DE", "zh-CN", "es-ES",
            "hi-IN", "ar-EG", "bn-BD", "pt-PT"
        };
    }
    
}
