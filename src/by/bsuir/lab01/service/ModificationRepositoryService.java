package by.bsuir.lab01.service;

import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.dao.DaoFactory;
import by.bsuir.lab01.dao.ModificationDao;

public final class ModificationRepositoryService {
	private ModificationRepositoryService(){}
	
	public static boolean addNewBookService(String title) throws ServiceException{
		DaoFactory daoFactory = DaoFactory.getDaoFactory();
		ModificationDao modificationDao = daoFactory.getModificationDao();
		
		try {
			modificationDao.addNewBook(title);
		} catch (DaoException ex) {
			throw new ServiceException("Adding new book Service Exception", ex);
		}
		return true;
	}

	public static boolean removeBookService(String title) throws ServiceException {
		DaoFactory daoFactory = DaoFactory.getDaoFactory();
		ModificationDao modificationDao = daoFactory.getModificationDao();

		try {
			modificationDao.removeBook(title);
		} catch (DaoException ex) {
			throw new ServiceException("Remove book Service Exception Message", ex);
		}
		return true;
	}
}
