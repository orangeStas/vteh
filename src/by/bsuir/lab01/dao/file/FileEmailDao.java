package by.bsuir.lab01.dao.file;

import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.dao.EmailDao;
import by.bsuir.lab01.dao.file.property.PropertiesLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by stas- on 10/12/2015.
 */
public final class FileEmailDao implements EmailDao {
    private final static FileEmailDao instance = new FileEmailDao();

    private static final String FILE_NAME = PropertiesLoader.loadEmailsFilePath();

    private FileEmailDao(){}

    public static FileEmailDao getInstance() {
        return instance;
    }


    @Override
    public List<String> getUserEmails() throws DaoException {
        try {
            return Files.readAllLines(Paths.get(FILE_NAME));
        } catch (IOException e) {
            throw new DaoException("File with e-mails reading failed" ,e);
        }
    }
}
