package by.bsuir.lab01.controller.command.impl;

import by.bsuir.lab01.bean.LoginUserRequest;
import by.bsuir.lab01.bean.LoginUserResponse;
import by.bsuir.lab01.bean.Request;
import by.bsuir.lab01.bean.Response;
import by.bsuir.lab01.controller.command.Command;
import by.bsuir.lab01.controller.command.CommandException;
import by.bsuir.lab01.service.LoginUserService;
import by.bsuir.lab01.service.ServiceException;
import org.apache.commons.codec.digest.DigestUtils;

public class LoginUserCommand implements Command{

	@Override
	public Response execute(Request request) throws CommandException{
		if (!validationParameters(request)) {
			throw new CommandException("User validation parameters exception");
		}

		LoginUserRequest loginUserRequest = (LoginUserRequest) request;

		boolean authentication[];

		try {
			String currentPassword = DigestUtils.md5Hex(loginUserRequest.getUserData().split(" ")[1]);
			String currentUserName = loginUserRequest.getUserData().split(" ")[0];
			loginUserRequest.setUserData(currentUserName + " " + currentPassword);
			authentication = LoginUserService.loginUser(loginUserRequest.getUserData());
		} catch (ServiceException e) {
			throw new CommandException("Command Login Exception", e);
		}

		LoginUserResponse loginUserResponse = new LoginUserResponse();

		if (authentication[0]) {
			loginUserResponse.setResultMessage("Successful authentication");
			if (authentication[1]) {
				loginUserResponse.setAdmin(true);
			}
		}
		else {
			loginUserResponse.setErrorMessage("Login failed");
		}

		return loginUserResponse;
	}

	private boolean validationParameters(Request request) {
		LoginUserRequest loginUserRequest = (LoginUserRequest) request;
		String userData = loginUserRequest.getUserData();

		if (userData.split(" ").length == 2){
			return true;
		}
		else
		{
			return false;
		}
	}
}
