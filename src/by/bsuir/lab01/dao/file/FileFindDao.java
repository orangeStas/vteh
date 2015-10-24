package by.bsuir.lab01.dao.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.dao.FindDao;
import by.bsuir.lab01.dao.file.property.PropertiesLoader;
import by.bsuir.lab01.entity.Book;

public final class FileFindDao implements FindDao {
	private final static FileFindDao instance = new FileFindDao();

	private static final String FILE_NAME = PropertiesLoader.loadFileBooksPath();
	
	private FileFindDao(){}
	
	public static FileFindDao getInstance(){
		return instance;
	}
	
	public List<Book> findBookByAuthor(String author) throws DaoException{
		List<Book> foundBooks = new ArrayList<>();
		try {
			List<String> books = Files.readAllLines(Paths.get(FILE_NAME));

			for (String book : books) {
				if (book.isEmpty()) {
					continue;
				}
				String title = book.split(" ")[0];
				String authorBook = book.split(" ")[1];

				if (authorBook.equals(author)) {
					foundBooks.add(new Book(title, author));
				}
			}

		} catch (IOException e) {
			throw new DaoException("File reading failed", e);
		}

		return foundBooks;
	}
}
