package com.github.druyaned.horstmann.corejava.vol2.ch03;

import com.github.druyaned.horstmann.corejava.Chapter;
import com.github.druyaned.horstmann.corejava.vol2.ch03.src.P01WriteDTD;
import com.github.druyaned.horstmann.corejava.vol2.ch03.src.P02WriteXML;
import com.github.druyaned.horstmann.corejava.vol2.ch03.src.P03ReadXML;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import org.xml.sax.SAXException;

/**
 * Practice implementation of the Chapter#03: XML.
 * @author druyaned
 */
public class XML extends Chapter {
    
    /**
     * Creates the Chapter#03: XML.
     * @param volDataDir the path to the volume's data-directory
     */
    public XML(Path volDataDir) {
        super(volDataDir, 3);
    }
    
    @Override public String getTitle() {
        return "XML";
    }
    
    @Override public void run() {
        final Path dtdPath = getDataDir().resolve("config.dtd");
        final Path xmlPath = getDataDir().resolve("active-times.xml");
        try {
            if (!Files.exists(dtdPath)) {
                Files.createFile(dtdPath);
            }
            if (!Files.exists(xmlPath)) {
                Files.createFile(xmlPath);
            }
            P01WriteDTD.run(dtdPath);
            P02WriteXML.run(xmlPath);
            P03ReadXML.run(xmlPath);
        } catch (
                IOException |
                ParserConfigurationException |
                TransformerFactoryConfigurationError |
                TransformerException |
                SAXException ex
        ) {
            throw new RuntimeException(ex);
        }
    }
    
}
