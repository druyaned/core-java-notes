package com.github.druyaned.horstmann.corejava.vol2.ch04.src;

import static com.github.druyaned.ConsoleColors.*;
import com.github.druyaned.horstmann.corejava.util.crypt.Cryptographer;
import java.io.Console;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.security.GeneralSecurityException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Part 6 of the chapter 4 to practice with {@code java.mail} by sending an email.
 * 
 * <P><i>NOTE</i>: using special password from <a href="https://yandex.ru">yandex</a>.
 * 
 * @author druyaned
 */
public class P06Mail implements Runnable {
    
    private final Path passPath;
    
    public P06Mail(Path dataDir) {
        passPath = dataDir.resolve("druyan-ed-yandex-ru-message-pass.txt");
    }
    
    @Override public void run() {
        System.out.println("\n" + bold("Running Part 06 Mail"));
        // properties
        String[] linesOfProperties = { // protection: SSL
            "mail.smtp.host=smtp.yandex.ru",
            "mail.smtp.port=465",
            "mail.smtp.auth=true",
            "mail.smtp.starttls.enable=true",
            "mail.smtp.ssl.enable=true"
        };
        Properties properties = new Properties(linesOfProperties.length);
        for (int i = 0; i < linesOfProperties.length; ++i) {
            String[] keysToValues = linesOfProperties[i].split("=");
            properties.put(keysToValues[0], keysToValues[1]);
        }
        // username and pass
        final String username = "druyan-ed@yandex.ru";
        Console console = System.console();
        try {
            Cryptographer cryptographer = new Cryptographer();
            console.printf("username: %s\n", blueBold(username));
            if (!Files.exists(passPath)) {
                Files.createFile(passPath);
                console.printf(blueBold(passPath.toString()) + " was created!\n");
                char[] plainPass = console.readPassword("app-pass: ");
                String encryptedPass = cryptographer.encrypt(plainPass) + "\n";
                Files.writeString(passPath, encryptedPass, StandardOpenOption.WRITE);
                console.printf(blueBold(passPath.toString()) + " was written!\n");
            }
            // message info
            String subject = "Сообщение через Java";
            String text = "Замечательный текст сообщения!";
            // sending
            Session session = Session.getDefaultInstance(properties);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(username));
            message.setSubject(subject);
            message.setText(text);
            try (Transport transport = session.getTransport()) {
                char[] plainPass = cryptographer.decrypt(Files.readString(passPath));
                transport.connect(username, new String(plainPass));
                transport.sendMessage(message, message.getAllRecipients());
            } catch (AuthenticationFailedException ex) {
                char[] plainPass = console.readPassword(
                        "Authentication failed. Rewirting the app-pass: "
                );
                String encryptedPass = cryptographer.encrypt(plainPass);
                Files.writeString(passPath, encryptedPass, StandardOpenOption.WRITE);
                console.printf(blueBold(passPath.toString()) + " was written!\n");
            }
        } catch (
                IOException |
                GeneralSecurityException |
                MessagingException ex
        ) {
            throw new RuntimeException(ex);
        }
        System.out.println(
                bold("Message has been sent to") + " " + blueBold(username)
        );
    }
    
}
