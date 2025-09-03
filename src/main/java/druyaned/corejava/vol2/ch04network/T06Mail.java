package druyaned.corejava.vol2.ch04network;

import static druyaned.ConsoleColors.blueBold;
import static druyaned.ConsoleColors.bold;
import druyaned.corejava.Chapter;
import druyaned.corejava.Topic;
import druyaned.corejava.util.FileUtil;
import druyaned.corejava.util.crypt.Cryptographer;
import java.io.Console;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.security.GeneralSecurityException;
import java.util.Properties;
import javax.mail.AuthenticationFailedException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class T06Mail extends Topic {
    
    private final Path passPath;
    
    public T06Mail(Chapter chapter) {
        super(chapter, 6);
        passPath = dataDir().resolve("druyan-ed-yandex-ru-message-pass.txt");
    }
    
    @Override public String title() {
        return "Topic 06 Mail";
    }
    
    @Override public void run() {
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
                FileUtil.createFileOnDemand(passPath);
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
                char[] plainPass = console.readPassword("Authentication failed."
                        + " Rewirting the app-pass: ");
                String encryptedPass = cryptographer.encrypt(plainPass);
                Files.writeString(passPath, encryptedPass, StandardOpenOption.WRITE);
                console.printf(blueBold(passPath.toString()) + " was written!\n");
            }
        } catch (
                IOException
                        | GeneralSecurityException
                        | MessagingException
                        exc
        ) {
            throw new RuntimeException(exc);
        }
        System.out.println(bold("Message has been sent to") + " " + blueBold(username));
    }
    
}
