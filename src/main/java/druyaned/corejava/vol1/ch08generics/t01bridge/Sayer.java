package druyaned.corejava.vol1.ch08generics.t01bridge;

public class Sayer<T> {
    
    public String say(T message) {
        System.out.println("Saying: " + message.toString());
        return message.toString();
    }
    
}
