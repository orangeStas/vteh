package by.bsuir.lab01.dao.file;

import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.dao.ModificationDao;
import by.bsuir.lab01.dao.file.property.PropertiesLoader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.List;

public final class FileModificationDao implements ModificationDao {

	private final static FileModificationDao instance = new FileModificationDao();
	
	private static final String FILE_NAME = PropertiesLoader.loadFileBooksPath();//you must read it from property file
	
	private FileModificationDao(){}
	
	public static FileModificationDao getInstance(){
		return instance;
	}
	@Override
	public boolean addNewBook(String title) throws DaoException {
		try {
			Files.write(Paths.get(FILE_NAME), ("\n" + title).getBytes(), StandardOpenOption.APPEND);
		} catch (IOException ex) {
			throw new DaoException("File writing failed", ex);
		}
		return true;
	}

	@Override
	public boolean removeBook(String title) throws DaoException {
		boolean result;
		try {
			List<String> books = Files.readAllLines(Paths.get(FILE_NAME));
			result = books.remove(title);
			Files.write(Paths.get(FILE_NAME), books);

		} catch (IOException e) {
			throw new DaoException("Book removing failed", e);
		}

		return result;
	}

}
