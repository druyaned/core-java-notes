package com.github.druyaned.learn_java.vol2.chapter03;

import static com.github.druyaned.ConsoleColors.*;
import com.github.druyaned.learn_java.vol2.Volume2;
import com.github.druyaned.learn_java.Chapterable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import org.xml.sax.SAXException;

/**
 * Practice implementation of the chapter 3.
 * 
 * @author druyaned
 * @see com.github.druyaned.learn_java.vol2.Volume2
 */
public class Chapter03 implements Chapterable {

    @Override
    public void run() {
        System.out.println(bold("Running Chapter03: XML"));
        
        try {
            
            // creating the files
            Path dirPath = Volume2.getDataDirPath().resolve("chapter03");
            final Path dtdPath = dirPath.resolve("config.dtd");
            final Path xmlPath = dirPath.resolve("active-times.xml");
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }
            if (!Files.exists(dtdPath)) {
                Files.createFile(dtdPath);
            }
            if (!Files.exists(xmlPath)) {
                Files.createFile(xmlPath);
            }
            
            // run writing and reading files
            P01WriteDTD.run(dtdPath);
            P02WriteXML.run(xmlPath);
            P03ReadXML.run(xmlPath);
            
        } catch (IOException |
                 ParserConfigurationException |
                 TransformerException |
                 TransformerFactoryConfigurationError |
                 SAXException exc) {
            
            Logger.getLogger(Chapter03.class.getName()).log(Level.SEVERE, null, exc);
        }
    }
            
    @Override
    public int getNumber() { return 3; }
    
    @Override
    public String getTitle() { return "XML"; }
    
    @Override
    public boolean passed() { return true; }

}
