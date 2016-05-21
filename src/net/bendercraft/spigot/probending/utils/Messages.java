package net.bendercraft.spigot.probending.utils;

import net.bendercraft.spigot.probending.ProbendingPlugin;

import java.io.*;
import java.util.Properties;

/**
 * Created by Nokorbis on 3/04/2016.
 */
public abstract class Messages {

    private static final String CHARSET = "UTF-8";
    private static final String FILENAME = "messages.properties";

    private static Properties defaultMessages = null;
    private static Properties messages = null;

    public static String get(String key) {
        if (defaultMessages == null && messages == null) {
            loadMessages();
        }
        return messages.getProperty(key);
    }

    private static void loadMessages() {
        defaultMessages = new Properties();

        try {
            InputStream in = Messages.class.getClassLoader().getResourceAsStream(FILENAME);
            InputStreamReader reader = new InputStreamReader(in, CHARSET);
            defaultMessages.load(reader);
            reader.close();
        }
        catch (IOException e) {
            // Should never happens
        }
        messages = new Properties(defaultMessages);
        try {
            File folder = ProbendingPlugin.getInstance().getDataFolder();
            File custom = new File(folder, FILENAME);
            if (custom.exists()) {
                InputStream in = new FileInputStream(custom);
                InputStreamReader reader = new InputStreamReader(in, CHARSET);
                messages.load(reader);
                reader.close();
            }
        }
        catch (IOException e) {
        }
    }
}
