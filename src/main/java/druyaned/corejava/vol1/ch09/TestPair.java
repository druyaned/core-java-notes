package druyaned.corejava.vol1.ch09;

public class TestPair {
    
    final String name;
    final Runnable runner;
    
    TestPair(String name, Runnable runner) {
        this.name = name;
        this.runner = runner;
    }
    
}
