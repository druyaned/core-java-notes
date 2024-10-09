package com.github.druyaned.corejava.vol2.ch03.src;

import static com.github.druyaned.ConsoleColors.*;
import java.io.IOException;
import java.nio.file.Path;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Part 3 of the chapter 3 to practice in reading an XML file.
 * @author druyaned
 */
public class P03ReadXML {
    
    public static void run(Path xmlPath)
            throws ParserConfigurationException,
            SAXException,
            IOException
    {
        System.out.println("\n" + bold("Running P03 ReadXML"));
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        factory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        builder.setErrorHandler(new DefaultHandler());
        Document document = builder.parse(xmlPath.toFile());
        Element root = document.getDocumentElement();
        System.out.println("root.getTagName(): " + root.getTagName() + "\n");
        String timeFormat = "%04d/%02d/%02dt%02d:%02d:%02d";
        System.out.printf("%19s | %19s | %11s | %5s\n", "start", "stop", "mode", "descr");
        System.out.printf(
                "%19s-+-%19s-+-%11s-+-%5s\n",
                "-".repeat(19), "-".repeat(19), "-".repeat(11), "-".repeat(5)
        );
        NodeList nodeList = root.getChildNodes();
        int n = nodeList.getLength();
        for (int i = 0; i < n; ++i) {
            Node item = nodeList.item(i);
            NodeList fields = item.getChildNodes();
            Node start = fields.item(0);
            Node stop = fields.item(1);
            Node mode = fields.item(2);
            Node descr = fields.item(3);
            // start
            NodeList startFields = start.getChildNodes();
            int yea1 = Integer.parseInt(startFields.item(0).getTextContent());
            int mon1 = Integer.parseInt(startFields.item(1).getTextContent());
            int day1 = Integer.parseInt(startFields.item(2).getTextContent());
            int hou1 = Integer.parseInt(startFields.item(3).getTextContent());
            int min1 = Integer.parseInt(startFields.item(4).getTextContent());
            int sec1 = Integer.parseInt(startFields.item(5).getTextContent());
            // stop
            NodeList stopFields = stop.getChildNodes();
            int yea2 = Integer.parseInt(stopFields.item(0).getTextContent());
            int mon2 = Integer.parseInt(stopFields.item(1).getTextContent());
            int day2 = Integer.parseInt(stopFields.item(2).getTextContent());
            int hou2 = Integer.parseInt(stopFields.item(3).getTextContent());
            int min2 = Integer.parseInt(stopFields.item(4).getTextContent());
            int sec2 = Integer.parseInt(stopFields.item(5).getTextContent());
            // print
            String startStr = String.format(timeFormat, yea1, mon1, day1, hou1, min1, sec1);
            String stopStr = String.format(timeFormat, yea2, mon2, day2, hou2, min2, sec2);
            String modeStr = mode.getTextContent();
            String descrStr = descr.getTextContent();
            System.out.printf(
                    "%19s | %19s | %11s | %5s\n",
                    startStr, stopStr, modeStr, descrStr
            );
        }
    }
    
}
