package by.bsuir.lab01.service;

import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.dao.DaoFactory;
import by.bsuir.lab01.dao.ViewDao;
import by.bsuir.lab01.entity.Book;

import java.util.List;

/**
 * Created by stas- on 10/1/2015.
 */
public final class ViewBooksService {
    private ViewBooksService(){}

    public static List<Book> getAllBooks() throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        ViewDao fileViewDao = daoFactory.getViewDao();

        List<Book> allBooks;
        try {
            allBooks = fileViewDao.getAllBooks();
        } catch (DaoException e) {
            throw new ServiceException("Can't view books", e);
        }

        return allBooks;
    }
}
