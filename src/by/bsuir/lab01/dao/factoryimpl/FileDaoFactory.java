package by.bsuir.lab01.dao.factoryimpl;

import by.bsuir.lab01.dao.*;
import by.bsuir.lab01.dao.file.*;

public final class FileDaoFactory extends DaoFactory{
	private final static FileDaoFactory instance = new FileDaoFactory();
	
	private FileDaoFactory(){}
	
	public static FileDaoFactory getInstance(){
		return instance;
	}
	
	@Override
	public FindDao getFindDao() {
		return FileFindDao.getInstance();
	}

	@Override
	public ModificationDao getModificationDao() {
		return FileModificationDao.getInstance();
	}

	@Override
	public LoginDao getLoginUserDao() {
		return FileLoginDao.getInstance();
	}

	@Override
	public ViewDao getViewDao() {
		return FileViewDao.getInstance();
	}

	@Override
	public EmailDao getEmailDao() {
		return FileEmailDao.getInstance();
	}

}
