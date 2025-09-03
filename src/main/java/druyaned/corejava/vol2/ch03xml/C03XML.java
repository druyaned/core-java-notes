package druyaned.corejava.vol2.ch03xml;

import druyaned.corejava.Chapter;
import druyaned.corejava.Volume;
import druyaned.corejava.util.FileUtil;
import java.nio.file.Path;

/**
 * Practical implementation of the Chapter#03: C03XML.
 * @author druyaned
 */
public class C03XML extends Chapter {
    
    /**
     * Creates the Chapter#03: XML.
     * @param volume to which the chapter belongs
     */
    public C03XML(Volume volume) {
        super(volume, 3);
        Path dtdPath = dataDir().resolve("config.dtd");
        Path xmlPath = dataDir().resolve("active-times.xml");
        FileUtil.createFileOnDemand(dtdPath);
        FileUtil.createFileOnDemand(xmlPath);
        topics().add(new T01WriteDTD(this, dtdPath));
        topics().add(new T02WriteXML(this, xmlPath));
        topics().add(new T03ReadXML(this, xmlPath));
    }
    
    @Override public String title() {
        return "Chapter 03 XML";
    }
    
}
