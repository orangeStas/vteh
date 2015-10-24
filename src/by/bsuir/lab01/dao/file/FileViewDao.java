package by.bsuir.lab01.dao.file;

import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.dao.ViewDao;
import by.bsuir.lab01.dao.file.property.PropertiesLoader;
import by.bsuir.lab01.entity.Book;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stas- on 10/1/2015.
 */
public final class FileViewDao implements ViewDao {

    private final static FileViewDao instance = new FileViewDao();

    private static final String FILE_NAME = PropertiesLoader.loadFileBooksPath();

    private FileViewDao(){}

    public static FileViewDao getInstance() {
        return instance;
    }

    @Override
    public List<Book> getAllBooks() throws DaoException {
        List<Book> allBooks = new ArrayList<>();
        try {
            List<String> books = Files.readAllLines(Paths.get(FILE_NAME));

            for (String book : books) {
                if (book.isEmpty()) {
                    continue;
                }
                String title = book.split(" ")[0];
                String authorBook = book.split(" ")[1];

                allBooks.add(new Book(title, authorBook));
            }
        } catch (IOException e) {
            throw new DaoException("File reading failed", e);
        }

        return allBooks;
    }
}
