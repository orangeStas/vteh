package by.bsuir.lab01.service;

import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.dao.DaoFactory;
import by.bsuir.lab01.dao.LoginDao;

/**
 * Created by stas- on 9/28/2015.
 */
public final class LoginUserService {
    private LoginUserService(){}

    public static boolean[] loginUser(String userData) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        LoginDao loginDao = daoFactory.getLoginUserDao();

        try {
            return loginDao.loginUser(userData);
        } catch (DaoException e) {
            throw new ServiceException("Login Service Exception", e);
        }

    }

}
