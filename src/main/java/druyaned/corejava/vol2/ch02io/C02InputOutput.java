package druyaned.corejava.vol2.ch02io;

import druyaned.corejava.Chapter;
import druyaned.corejava.Volume;
import druyaned.corejava.util.WarAndPeace;
import java.nio.file.Path;

/**
 * Practical implementation of the Chapter#02: Input-Output.
 * @author druyaned
 */
public class C02InputOutput extends Chapter {
    
    /**
     * Creates the Chapter#02: Input-Output.
     * @param volume to which the chapter belongs
     */
    public C02InputOutput(Volume volume) {
        super(volume, 2);
        Path textPath = WarAndPeace.textPath(volume);
        EmployeeData data = new EmployeeData(dataDir());
        topics().add(new T01TextIO(this));
        topics().add(new T02BinData(this, data));
        topics().add(new T03RandomAccess(this, data));
        topics().add(new T04Zip(this, data));
        topics().add(new T05ObjectIO(this, data));
        topics().add(new T06FileManipulation(this, data));
        topics().add(new T07FileMap(this, textPath));
        topics().add(new T08RegEx(this));
    }
    
    @Override public String title() {
        return "Chapter 02 Input-Output";
    }
    
}
