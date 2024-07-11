package com.github.druyaned.horstmann.corejava.vol2.ch08.src.p03;

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
 * A handler of the {@link Gettered} which adds getters
 * into bytecode of the {@link Person}. To check the work of the program
 * type in the Terminal: <pre>
 *  javap -c -cp target/classes &lt;PERSON_CLASS_NAME&gt;
 * </pre>.
 * 
 * @author druyaned
 */
public class GetteredHandler {
    
    public static void handlePerson() throws IOException {
        // define a class and make a path of the ".class" file with a bytecode
        Class<Person> cl = Person.class;
        String[] nameParts = cl.getName().split("\\.");
        nameParts[nameParts.length - 1] += ".class";
        String[] pathArguments = new String[nameParts.length + 1];
        pathArguments[0] = "classes";
        for (int i = 0; i < nameParts.length; ++i) {
            pathArguments[i + 1] = nameParts[i];
        }
        Path classPath = Paths.get("target", pathArguments);
        // add getters
        Gettered annotation = cl.getAnnotation(Gettered.class);
        if (annotation != null) {
            ClassReader reader = new ClassReader(cl.getName());
            ClassWriter writer = new ClassWriter(reader, 0);
            VisitorToAddGetters visitor = new VisitorToAddGetters(cl, writer);
            reader.accept(visitor, 0);
            byte[] classCode = writer.toByteArray();
            InputStream in = new ByteArrayInputStream(classCode);
            Files.copy(in, classPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Getters are added into the bytecode of the P03Person.");
            String command = "javap -c -cp target/classes " + Person.class.getName();
            System.out.println("To check it type in the Terminal: " + bold(command));
        }
    }
    
}
