package by.bsuir.lab01.service;

import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.dao.DaoFactory;
import by.bsuir.lab01.dao.EmailDao;

import java.util.List;

/**
 * Created by stas- on 10/12/2015.
 */
public class UsersNotificationService {
    private UsersNotificationService(){}

    public static List<String> getUsersEmails() throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        EmailDao emailDao = daoFactory.getEmailDao();

        try {
            return emailDao.getUserEmails();
        } catch (DaoException e) {
            throw new ServiceException("Read file with user's emails Exception", e);
        }
    }
}
