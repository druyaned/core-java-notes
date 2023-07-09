package com.github.druyaned.learn_java.vol2.chapter08;

import static com.github.druyaned.ConsoleColors.bold;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

/**
 * A handler of the {@link P03Gettered} which adds getters
 * into bytecode of the {@link P03Person}. To check the work of the program
 * type in the Terminal: <code>javap -c -cp target/classes
 * com.github.druyaned.learn_java.vol2.chapter08.P03Person</code>.
 * 
 * @author druyaned
 */
public class P03GetteredHandler {

    public static void handlePerson() throws IOException {
        // define a class and make a path of the ".class" file with a bytecode
        Class<P03Person> cl = P03Person.class;
        String[] nameParts = cl.getName().split("\\.");
        nameParts[nameParts.length - 1] += ".class";
        String[] pathArguments = new String[nameParts.length + 1];
        pathArguments[0] = "classes";
        for (int i = 0; i < nameParts.length; ++i) {
            pathArguments[i + 1] = nameParts[i];
        }
        Path classPath = Paths.get("target", pathArguments);
        // add getters
        P03Gettered a = cl.getAnnotation(P03Gettered.class);
        if (a != null) {
            ClassReader reader = new ClassReader(cl.getName());
            ClassWriter writer = new ClassWriter(reader, 0);
            P03VisitorToAddGetters visitor = new P03VisitorToAddGetters(cl, writer);
            reader.accept(visitor, 0);
            byte[] classCode = writer.toByteArray();
            InputStream in = new ByteArrayInputStream(classCode);
            Files.copy(in, classPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Getters are added into the bytecode of the P03Person.");
            String command = "javap -c -cp target/classes " +
                    "com.github.druyaned.learn_java.vol2.chapter08.P03Person";
            System.out.println("To check it type in the Terminal: " + bold(command));
        }
    }

}
