package com.github.druyaned.corejava.vol1.ch08;

import com.github.druyaned.corejava.vol1.ch08.src.TestGenericCars;
import com.github.druyaned.corejava.vol1.ch08.src.TestGenericArray;
import com.github.druyaned.corejava.vol1.ch08.src.TestGenericsLimitations;
import com.github.druyaned.corejava.vol1.ch08.src.TestBridgeMethod;
import com.github.druyaned.corejava.Chapter;
import java.nio.file.Path;

/**
 * Practice implementation of the Chapter#08: Generics.
 * 
 * <P>Remember the rule:<pre>
 *  &lt;? extends Type> : to read data from a generic instance
 *  &lt;? super Type>   : to write data to a generic instance
 * </pre>
 * 
 * @author druyaned
 */
public class Generics extends Chapter {
    
    /**
     * Creates the Chapter#08: Generics.
     * @param volDataDir the path to the volume's data-directory
     */
    public Generics(Path volDataDir) {
        super(volDataDir, 8);
    }
    
    @Override public String getTitle() {
        return "Generics";
    }
    
    @Override public void run() {
        TestBridgeMethod.run();
        TestGenericArray.run();
        TestGenericCars.run();
        TestGenericsLimitations.run();
    }
    
}
