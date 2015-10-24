package by.bsuir.lab01.dao;

/**
 * Created by stas- on 9/25/2015.
 */
public interface LoginDao {
    boolean[] loginUser(String userData) throws DaoException;
}
