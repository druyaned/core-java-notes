package druyaned.corejava.vol2.ch07.src;

import static druyaned.ConsoleColors.bold;
import java.text.MessageFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Part 4 of the chapter 7 to practice with {@code MessageFormat}.
 * @author druyaned
 */
public class P04MessageFormat implements Runnable {
    
    @Override public void run() {
        System.out.println("\n" + bold("Running P04 MessageFormat"));
        Locale locale = new Locale.Builder().setLanguage("ru").setRegion("RU").build();
        String pattern = "Сегодня, {0,date,long}, компания \"{1}\" " +
                "{2,choice,-\u221e<терпит убыток в {2,number,currency}|" + // \u221e = inf
                "0#осталась без прибыли и убытков|" +
                "0<довольствуется прибылью в {2,number,currency}}.";
        MessageFormat messageFormat = new MessageFormat(pattern, locale);
        Date date = GregorianCalendar.from(ZonedDateTime.now()).getTime();
        String company = "Cocked Hat With a Feather";
        System.out.println(messageFormat.format(new Object[] { date, company, 12345678.9 }));
        System.out.println(messageFormat.format(new Object[] { date, company, -0.5 }));
        System.out.println(messageFormat.format(new Object[] { date, company, 0 }));
    }
    
}
/*
Сегодня, 6 июля 1970 г., компания "Cocked Hat With a Feather"
    довольствуется прибылью в 12 345 678,90 ₽.
Сегодня, 6 июля 1970 г., компания "Cocked Hat With a Feather"
    терпит убыток в -0,50 ₽.
Сегодня, 6 июля 1970 г., компания "Cocked Hat With a Feather"
    осталась без прибыли и убытков.
*/
