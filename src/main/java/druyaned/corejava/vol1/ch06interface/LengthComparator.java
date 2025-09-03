package druyaned.corejava.vol1.ch06interface;

import java.util.Comparator;

public class LengthComparator implements Comparator<String> {
    
    @Override public int compare(String s1, String s2) {
        return s1.length() - s2.length();
    }
    
}
