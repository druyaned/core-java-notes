package druyaned.corejava.vol1.ch03constr;

import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import java.math.BigDecimal;
import java.math.BigInteger;

public class T06BigNumbers extends Topic {
    
    public T06BigNumbers(Chapter chapter) {
        super(chapter, 6);
    }
    
    @Override public String title() {
        return "Topic 06 Big Numbers";
    }
    
    @Override public void run() {
        System.out.println("Max exponent: " + Double.MAX_EXPONENT);
        System.out.println("Max double:   " + Double.MAX_VALUE);
        BigDecimal bigDecimal;
        bigDecimal = BigDecimal.valueOf(10).multiply(BigDecimal.valueOf(Double.MAX_VALUE));
        System.out.println("After multiplication the bigDecimal is " + bigDecimal);
        BigInteger bigInteger;
        bigInteger = BigInteger.valueOf(10).add(BigInteger.valueOf(Integer.MAX_VALUE));
        System.out.println("MaxInteger: " + Integer.MAX_VALUE);
        System.out.println("BigInteger: " + bigInteger);
    }
    
}
