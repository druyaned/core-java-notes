package com.github.druyaned.learn_java.vol2.chapter05;

import static com.github.druyaned.ConsoleColors.*;
import com.github.druyaned.learn_java.vol2.Volume2;
import com.github.druyaned.learn_java.Chapterable;
import com.github.druyaned.learn_java.util.crypt.Password;
import java.io.IOException;
import java.nio.file.Path;
import java.security.GeneralSecurityException;

/**
 * Practice implementation of the chapter 5.
 * 
 * @author druyaned
 * @see com.github.druyaned.learn_java.vol2.Volume2
 */
public class Chapter05 implements Chapterable {
    private static final Path passPath =
            Volume2.getDataDirPath().resolve("chapter05").resolve("postgresql-druyaned-pass.txt");
    
    @Override
    public void run() {
        System.out.println(bold("Running Chapter05: DataBases"));
        
        P01TestDB.run();
        P02Insertion.run();
        P03Prepared.run();
        P04Scroll.run();
        P05MetaData.run();
        P06Commit.run();
    }
    
    /**
     * Returns a password in the PostgreSQL DB (localhost, port 5432) of the {@code user druyaned};
     * 
     * @return a password in the PostgreSQL DB (localhost, port 5432) of the {@code user druyaned}.
     */
    static String getPass() throws IOException, GeneralSecurityException {
        return Password.get(passPath, "PostgreSQL druyaned");
    }
    
    @Override
    public int getNumber() { return 5; }
    
    @Override
    public String getTitle() { return "DataBases"; }
    
    @Override
    public boolean passed() { return true; }
}
