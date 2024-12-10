package druyaned.corejava.vol2.ch08.src.p03;

/**
 * This simple class of a person helps to verify the work of the
 * {@link Gettered} annotation interface.
 * 
 * @author druyaned
 */
@Gettered public class Person {
    
    private final String name;
    private final int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
}
