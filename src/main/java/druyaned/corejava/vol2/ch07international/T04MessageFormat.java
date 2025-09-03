package druyaned.corejava.vol2.ch07international;

import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import java.text.MessageFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class T04MessageFormat extends Topic {
    
    public T04MessageFormat(Chapter chapter) {
        super(chapter, 4);
    }
    
    @Override public String title() {
        return "Topic 04 MessageFormat";
    }
    
    @Override public void run() {
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
