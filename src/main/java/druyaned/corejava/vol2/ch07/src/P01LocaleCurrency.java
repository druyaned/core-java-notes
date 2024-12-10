package druyaned.corejava.vol2.ch07.src;

import static druyaned.ConsoleColors.bold;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Currency;
import java.util.Locale;

/**
 * Part 1 of the chapter 7 to practice with {@code Locale} and {@code Currency}.
 * @author druyaned
 */
public class P01LocaleCurrency implements Runnable {
    
    @Override public void run() {
        System.out.println("\n" + bold("Running P01 Locale & Currency"));
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
