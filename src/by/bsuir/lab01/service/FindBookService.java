package by.bsuir.lab01.service;


import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.dao.DaoFactory;
import by.bsuir.lab01.dao.FindDao;
import by.bsuir.lab01.entity.Book;

import java.util.List;

public final class FindBookService {
	private FindBookService(){}

    public static List<Book> findBooks(String title) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        FindDao findDao = daoFactory.getFindDao();

        List<Book> foundBooks;
        try {
            foundBooks = findDao.findBookByAuthor(title);
        } catch (DaoException e) {
            throw new ServiceException("Can't find books", e);
        }

        return foundBooks;
    }
}
