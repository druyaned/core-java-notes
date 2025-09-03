package druyaned.corejava.vol2.ch07international;

import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Currency;
import java.util.Locale;

public class T01LocaleCurrency extends Topic {
    
    public T01LocaleCurrency(Chapter chapter) {
        super(chapter, 1);
    }
    
    @Override public String title() {
        return "Topic 01 LocaleCurrency";
    }
    
    @Override public void run() {
        Locale deCh = Locale.forLanguageTag("de-CH");
        Locale deDe = Locale.forLanguageTag("de-DE");
        Locale enGb = new Locale.Builder().setLanguage("en").setRegion("GB").build();
        System.out.println(deCh + ": " + deCh.getDisplayName(Locale.GERMAN));
        double val = 123456.78;
        NumberFormat deDeFormat = NumberFormat.getCurrencyInstance(deDe);
        NumberFormat enGbFormat = NumberFormat.getCurrencyInstance(enGb);
        String deDeText = deDeFormat.format(val);
        String enGbText = enGbFormat.format(val);
        System.out.println("For " + deDe + " val: " + deDeText);
        System.out.println("For " + enGb + " val: " + enGbText);
        try {
            double deDeParsed = deDeFormat.parse(deDeText).doubleValue();
            double enGbParsed = enGbFormat.parse(enGbText).doubleValue();
            System.out.println("Parsed value from deDeText: " + deDeParsed);
            System.out.println("Parsed value from enGbText: " + enGbParsed);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
        Currency euro = Currency.getInstance("EUR");
        Currency dollar = Currency.getInstance("USD");
        Currency ruble = Currency.getInstance("RUB");
        enGbFormat.setCurrency(euro);
        System.out.println("For enGbFormat.setCurrency(euro): " + enGbFormat.format(val));
        enGbFormat.setCurrency(dollar);
        System.out.println("For enGbFormat.setCurrency(dollar): " + enGbFormat.format(val));
        enGbFormat.setCurrency(ruble);
        System.out.println("For enGbFormat.setCurrency(ruble): " + enGbFormat.format(val));
    }
    
}
