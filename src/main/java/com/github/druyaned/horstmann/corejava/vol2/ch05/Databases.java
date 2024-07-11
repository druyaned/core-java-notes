package com.github.druyaned.horstmann.corejava.vol2.ch05;

import static com.github.druyaned.ConsoleColors.blueBold;
import com.github.druyaned.horstmann.corejava.Chapter;
import com.github.druyaned.horstmann.corejava.util.crypt.Cryptographer;
import com.github.druyaned.horstmann.corejava.vol2.ch05.src.P01TestDB;
import com.github.druyaned.horstmann.corejava.vol2.ch05.src.P02Insertion;
import com.github.druyaned.horstmann.corejava.vol2.ch05.src.P03Prepared;
import com.github.druyaned.horstmann.corejava.vol2.ch05.src.P04Scroll;
import com.github.druyaned.horstmann.corejava.vol2.ch05.src.P05MetaData;
import com.github.druyaned.horstmann.corejava.vol2.ch05.src.P06Commit;
import java.io.Console;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Practice implementation of the Chapter#05: Database Programming.
 * @author druyaned
 */
public class Databases extends Chapter {
    
    /**
     * Creates the Chapter#05: Database Programming.
     * @param volDataDir the path to the volume's data-directory
     */
    public Databases(Path volDataDir) {
        super(volDataDir, 5);
    }
    
    @Override public String getTitle() {
        return "Database Programming";
    }
    
    @Override public void run() {
        // login data
        Object[] loginData = getUrlUserPass();
        String url = (String)loginData[0];
        String user = (String)loginData[1];
        String pass = (String)loginData[2];
        // parts
        List<Runnable> parts = new ArrayList<>();
        parts.add(new P01TestDB(url, user, pass));
        parts.add(new P02Insertion(url, user, pass));
        parts.add(new P03Prepared(url, user, pass));
        parts.add(new P04Scroll(url, user, pass));
        parts.add(new P05MetaData(url, user, pass));
        parts.add(new P06Commit(url, user, pass));
        choosePartAndRun(parts);
    }
    
    private Object[] getUrlUserPass() {
        try {
            // some utils
            Path loginPath = getDataDir().resolve("h2-db-login.txt");
            Cryptographer cryptographer = new Cryptographer();
            Console console = System.console();
            // getting login data
            String dbName = "transactions";
            String user;
            char[] pass;
            if (!Files.exists(loginPath)) {
                // creating
                Files.createFile(loginPath);
                console.printf(blueBold(loginPath.toString()) + " was created.\n");
                // reading of login data
                user = console.readLine("h2-username: ");
                pass = console.readPassword("h2-password: ");
                // writing
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
