package com.github.druyaned.horstmann.corejava.vol2.ch03.src;

import static com.github.druyaned.ConsoleColors.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Random;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Part 2 of the chapter 3 to practice in writing an XML file.
 * @author druyaned
 */
public class P02WriteXML {
    
    public static void run(Path xmlPath)
            throws IOException,
            ParserConfigurationException,
            TransformerConfigurationException,
            TransformerFactoryConfigurationError,
            TransformerException
    {
        System.out.println("\n" + bold("Running P02 WriteXML"));
        DocumentBuilder builder = DocumentBuilderFactory
                .newInstance()
                .newDocumentBuilder();
        builder.setErrorHandler(new DefaultHandler());
        int[] yea1 = { 2020, 2021, 2021, 2021, 2021, 2021, 2021, 2021, 2021, 2021 };
        final int N = yea1.length;
        int[] mon1 = { 4, 4, 8, 8, 8, 9, 9, 9, 9, 9 };
        int[] day1 = { 29, 1, 2, 3, 4, 4, 5, 6, 7, 7 };
        int[] hou1 = { 10, 13, 10, 23, 0, 23, 10, 23, 0, 23 };
        int[] min1 = { 17, 29, 17, 59, 0, 59, 17, 59, 0, 59 };
        int[] sec1 = { 31, 23, 31, 59, 0, 59, 31, 59, 0, 59 };
        int[] yea2 = { 2020, 2021, 2021, 2021, 2021, 2021, 2021, 2021, 2021, 2021 };
        int[] mon2 = { 4, 4, 8, 8, 8, 9, 9, 9, 9, 9 };
        int[] day2 = { 29, 1, 2, 4, 4, 5, 5, 7, 7, 8 };
        int[] hou2 = { 13, 16, 13, 0, 2, 0, 13, 0, 2, 0 };
        int[] min2 = { 17, 22, 17, 0, 53, 0, 17, 0, 53, 0 };
        int[] sec2 = { 29, 42, 31, 0, 18, 0, 31, 0, 18, 0 };
        String[] mod = new String[N];
        Random generator = new Random();
        for (int i = 0; i < N; ++i) {
            int variant = generator.nextInt(3);
            switch (variant) {
                case 0 -> mod[i] = "DEVELOPMENT";
                case 1 -> mod[i] = "STAGNATION";
                default -> mod[i] = "RELAXATION";
            }
        }
        String des = "none";
        Document document = builder.newDocument();
        Element root = document.createElement("activeTimes");
        document.appendChild(root);
        for (int i = 0; i < yea1.length; ++i) {
            Element item = document.createElement("item");
            root.appendChild(item);
            // start
            Element start = document.createElement("start");
            Element yearStart = document.createElement("year");
            yearStart.setTextContent(Integer.toString(yea1[i]));
            Element monthStart = document.createElement("month");
            monthStart.setTextContent(Integer.toString(mon1[i]));
            Element dayStart = document.createElement("day");
            dayStart.setTextContent(Integer.toString(day1[i]));
            Element hourStart = document.createElement("hour");
            hourStart.setTextContent(Integer.toString(hou1[i]));
            Element minuteStart = document.createElement("minute");
            minuteStart.setTextContent(Integer.toString(min1[i]));
            Element secondStart = document.createElement("second");
            secondStart.setTextContent(Integer.toString(sec1[i]));
            start.appendChild(yearStart);
            start.appendChild(monthStart);
            start.appendChild(dayStart);
            start.appendChild(hourStart);
            start.appendChild(minuteStart);
            start.appendChild(secondStart);
            item.appendChild(start);
            // stop
            Element stop = document.createElement("stop");
            Element yearStop = document.createElement("year");
            yearStop.setTextContent(Integer.toString(yea2[i]));
            Element monthStop = document.createElement("month");
            monthStop.setTextContent(Integer.toString(mon2[i]));
            Element dayStop = document.createElement("day");
            dayStop.setTextContent(Integer.toString(day2[i]));
            Element hourStop = document.createElement("hour");
            hourStop.setTextContent(Integer.toString(hou2[i]));
            Element minuteStop = document.createElement("minute");
            minuteStop.setTextContent(Integer.toString(min2[i]));
            Element secondStop = document.createElement("second");
            secondStop.setTextContent(Integer.toString(sec2[i]));
            stop.appendChild(yearStop);
            stop.appendChild(monthStop);
            stop.appendChild(dayStop);
            stop.appendChild(hourStop);
            stop.appendChild(minuteStop);
            stop.appendChild(secondStop);
            item.appendChild(stop);
            // mode
            Element mode = document.createElement("mode");
            mode.setTextContent(mod[i]);
            item.appendChild(mode);
            // descr
            Element descr = document.createElement("descr");
            descr.setTextContent(des);
            item.appendChild(descr);
        }
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "config.dtd");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        String indentAmountKeyName = "{http://xml.apache.org/xslt}indent-amount";
        transformer.setOutputProperty(indentAmountKeyName, "2");
        transformer.transform(
                new DOMSource(document),
                new StreamResult(new FileOutputStream(xmlPath.toFile()))
        );
    }
    
}
