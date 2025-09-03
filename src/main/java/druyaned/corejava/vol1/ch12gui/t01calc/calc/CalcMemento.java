package druyaned.corejava.vol1.ch12gui.t01calc.calc;

public class CalcMemento {
    
    private final Calculator calc;
    private final double accum;
    private final InputField input;
    
    public CalcMemento(Calculator calc, double accum, InputField input) {
        this.calc = calc;
        this.accum = accum;
        this.input = input;
    }
    
    public void restore() {
        calc.setAccumulator(accum);
        calc.input().clear();
        for (int i = 0; i < input.size(); i++)
            calc.input().append(input.charAt(i));
    }
    
}
