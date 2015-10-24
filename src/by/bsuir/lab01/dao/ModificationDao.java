package by.bsuir.lab01.dao;

public interface ModificationDao {
	boolean addNewBook(String title) throws DaoException;
	boolean removeBook(String title) throws DaoException;
}
