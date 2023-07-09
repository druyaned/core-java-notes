package com.github.druyaned.learn_java.vol2.chapter07;

import static com.github.druyaned.ConsoleColors.bold;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Currency;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Part 1 of the chapter 7 to practice with the class {@code Locale} and {@code Currency}.
 * 
 * @author druyaned
 */
public class P01LocaleCurrency {
    
    public static void run() {
        System.out.println("\n" + bold("Running P01 Locale & Currency"));
        
        Locale deCh = Locale.forLanguageTag("de-CH");
        Locale deDe = Locale.forLanguageTag("de-DE");
        Locale enGb = new Locale("en", "GB");
        
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
            Logger.getLogger(P01LocaleCurrency.class.getName()).log(Level.SEVERE, null, ex);
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
