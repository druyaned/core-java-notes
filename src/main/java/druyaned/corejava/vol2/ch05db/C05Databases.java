package druyaned.corejava.vol2.ch05db;

import static druyaned.ConsoleColors.blueBold;
import druyaned.corejava.Chapter;
import druyaned.corejava.Volume;
import druyaned.corejava.util.FileUtil;
import druyaned.corejava.util.crypt.Cryptographer;
import java.io.Console;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.GeneralSecurityException;
import java.util.Scanner;

/**
 * Practical implementation of the Chapter#05: Database Programming.
 * @author druyaned
 */
public class C05Databases extends Chapter {
    
    /**
     * Creates the Chapter#05: Database Programming.
     * @param volume to which the chapter belongs
     */
    public C05Databases(Volume volume) {
        super(volume, 5);
        Object[] loginData = getUrlUserPass();
        String url = (String)loginData[0];
        String user = (String)loginData[1];
        String pass = (String)loginData[2];
        topics().add(new T01DB(this, url, user, pass));
        topics().add(new T02Insertion(this, url, user, pass));
        topics().add(new T03Prepared(this, url, user, pass));
        topics().add(new T04Scroll(this, url, user, pass));
        topics().add(new T05MetaData(this, url, user, pass));
        topics().add(new T06Commit(this, url, user, pass));
    }
    
    @Override public String title() {
        return "Chapter 05 Database Programming";
    }
    
    private Object[] getUrlUserPass() {
        try {
            // Some utils
            Path loginPath = dataDir().resolve("h2-db-login.txt");
            Cryptographer cryptographer = new Cryptographer();
            Console console = System.console();
            // Getting login data
            String dbName = "transactions";
            String user;
            char[] pass;
            if (!Files.exists(loginPath)) {
                // Creating
                FileUtil.createFileOnDemand(loginPath);
                // Reading of login data
                user = console.readLine("h2-username: ");
                pass = console.readPassword("h2-password: ");
                // Writing
                try (FileWriter writer = new FileWriter(loginPath.toFile())) {
                    writer.write(user + "\n");
                    writer.write(cryptographer.encrypt(pass) + "\n");
                }
                console.printf(blueBold(loginPath.toString()) + " was written.\n");
            } else {
                try (Scanner fin = new Scanner(loginPath)) {
                    user = fin.nextLine();
                    pass = cryptographer.decrypt(fin.nextLine());
                }
            }
            return new Object[] {
                "jdbc:h2:~/h2/databases/" + dbName,
                user,
                new String(pass)
            };
        } catch (
                IOException |
                GeneralSecurityException ex
        ) {
            throw new RuntimeException(ex);
        }
    }
    
}
