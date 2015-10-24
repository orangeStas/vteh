package by.bsuir.lab01.dao.file;

import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.dao.LoginDao;
import by.bsuir.lab01.dao.file.property.PropertiesLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by stas- on 9/25/2015.
 */
public final class FileLoginDao implements LoginDao {
    private final static FileLoginDao instance = new FileLoginDao();

    private static final String FILE_NAME = PropertiesLoader.loadUsersFilePath();

    private FileLoginDao(){}

    public static FileLoginDao getInstance(){
        return instance;
    }

    public boolean[] loginUser(String userData) throws DaoException {
        boolean[] authorizationParams = new boolean[2];

        try {
            List<String> usersData = Files.readAllLines(Paths.get(FILE_NAME));
            if (usersData.contains(userData)){
                authorizationParams[0] = true;
                if (userData.startsWith("admin")) {
                    authorizationParams[1] = true;
                }
            }

        } catch (IOException e) {
            throw new DaoException("File with users reading failed", e);
        }

        return authorizationParams;

    }
}
