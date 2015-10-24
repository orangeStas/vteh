package by.bsuir.lab01.dao;

import java.util.List;

/**
 * Created by stas- on 10/12/2015.
 */
public interface EmailDao {
    List<String> getUserEmails() throws DaoException;
}
