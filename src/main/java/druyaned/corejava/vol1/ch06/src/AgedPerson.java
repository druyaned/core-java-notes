package druyaned.corejava.vol1.ch06.src;

import java.util.function.Consumer;

// Cloneable is necessary for "if (obj instanceof Cloneable)"
// and not to throw the CloneNotSupportedException
public class AgedPerson extends Person implements Cloneable {
    
    private int age = 21 + (int)(Math.random() * 10) % 3;

    public AgedPerson() { super(); }
    
    public AgedPerson(String name) { super(name); }
    
    public AgedPerson(String name, int age) {
        super(name);
        this.age = age;
    }

    public int getAge() { return age; }

    protected void show(String m, Consumer<String> consumer) { consumer.accept(m); }

    @Override public void show() { // method link
        show(toString(), super::show);
    }

    @Override public AgedPerson clone() throws CloneNotSupportedException {
        return (AgedPerson) super.clone();
    }

    @Override public String toString() {
        return "AgedPerson{name=" + getName() + ", age=" + age + "}";
    }
    
}
