package by.bsuir.lab01.dao;

import by.bsuir.lab01.entity.Book;

import java.util.List;

/**
 * Created by stas- on 10/1/2015.
 */
public interface ViewDao {
    List<Book> getAllBooks() throws DaoException;
}
