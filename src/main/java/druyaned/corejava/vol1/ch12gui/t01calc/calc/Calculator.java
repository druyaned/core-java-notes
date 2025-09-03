package druyaned.corejava.vol1.ch12gui.t01calc.calc;

/**
 * Calculator has accumulator and inputField; <u>calculation example</u>:
 * <code>(0) accumulator=0, (1) inputField.value=123, (2) UploadToAccumulator,
 * (3) accumulator=123, (4) inputField.value=4, (5) Sum,
 * (6) accumulator=127</code>.
 * 
 * @author druyaned
 */
public class Calculator {
    
    private double accum = 0d;
    private final InputField input = new InputField();
    
    /**
     * Returns accumulator of this calculator.
     * @return accumulator of this calculator
     */
    public double accumulator() {
        return accum;
    }
    
    /**
     * Sets accumulator of this calculator.
     * @param value to be set
     */
    public void setAccumulator(double value) {
        accum = value;
    }
    
    /**
     * Returns inputField of this calculator.
     * @return inputField of this calculator
     */
    public InputField input() {
        return input;
    }
    
    /**
     * Returns memento of this calculator.
     * @return memento of this calculator
     */
    public CalcMemento save() {
        return new CalcMemento(this, accum, input.clone());
    }
    
}
