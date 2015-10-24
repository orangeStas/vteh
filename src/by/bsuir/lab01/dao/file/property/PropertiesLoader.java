package by.bsuir.lab01.dao.file.property;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by stas- on 9/24/2015.
 */
public class PropertiesLoader {

    private static final String PROPERTIES_FILE = "resources/config/config.properties";

    private InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE);
    private static Properties properties;

    {
        try {
            properties = new Properties();

            inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE);

            if (inputStream != null) {
                properties.load(inputStream);
            }
            else {
                throw new FileNotFoundException("property file '" + PROPERTIES_FILE + "' not found in the classpath");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String loadFileBooksPath() {
        return properties.getProperty("fileNameBooks");
    }

    public String loadDaoType() {
        return properties.getProperty("dao_type");
    }

    public static String loadUsersFilePath() {
        return properties.getProperty("fileUsers");
    }

    public static String loadEmailsFilePath() {
        return properties.getProperty("fileEmails");
    }
}
