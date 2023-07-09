package com.github.druyaned.learn_java.vol2.chapter04;

import static com.github.druyaned.ConsoleColors.*;
import com.github.druyaned.learn_java.vol2.Volume2;
import com.github.druyaned.learn_java.util.crypt.Password;
import java.io.Console;
import java.io.IOException;
import java.nio.file.Path;
import java.security.GeneralSecurityException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Part 6 of the chapter 4 to practice with {@code java.mail} by sending an email.
 * <p><i>NOTE</i>: using special password from <a href="https://yandex.ru">yandex</a>.
 * 
 * @author druyaned
 */
public class P06Mail {
    private static Path passPath = Volume2.getDataDirPath()
            .resolve("chapter04")
            .resolve("druyan-ed-yandex-ru-message-pass.txt");
    
    public static void run() {
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
        
        // message info
        String username = "druyan-ed@yandex.ru";
        String to = username;
        String subject = "Сообщение через Java";
        String text = "Замечательный текст сообщения!";
        Console console = System.console();
        console.printf("username: %s\n", blueBold(username));
        
        // sending
        Session session = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(text);
            try (Transport transport = session.getTransport()) {
                String pass = Password.get(passPath, "mail.yandex druyan-ed");
                transport.connect(username, pass);
                transport.sendMessage(message, message.getAllRecipients());
            } catch (IOException | GeneralSecurityException ex) {
                Logger.getLogger(P06Mail.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
        } catch (MessagingException exc) {
            Logger.getLogger(P06Mail.class.getName()).log(Level.SEVERE, null, exc);
            return;
        }
        System.out.println(bold("The message has been sent to") + " " + blueBold(to));
    }

}
