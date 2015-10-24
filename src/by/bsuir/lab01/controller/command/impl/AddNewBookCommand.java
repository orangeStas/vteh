package by.bsuir.lab01.controller.command.impl;

import by.bsuir.lab01.bean.BookRequest;
import by.bsuir.lab01.bean.BookResponse;
import by.bsuir.lab01.bean.Request;
import by.bsuir.lab01.bean.Response;
import by.bsuir.lab01.controller.command.CommandException;
import by.bsuir.lab01.controller.command.Command;
import by.bsuir.lab01.service.ModificationRepositoryService;
import by.bsuir.lab01.service.ServiceException;
import by.bsuir.lab01.service.UsersNotificationService;

import java.util.List;

public class AddNewBookCommand implements Command {

	@Override
	public Response execute(Request request) throws CommandException {
		if (!validationParameters(request)) {
			throw new CommandException("Validation Exception.");
		}

		BookRequest newBookRequest = (BookRequest) request;
		boolean result = false;
		try {
			result = ModificationRepositoryService
					.addNewBookService(newBookRequest.getTitle());
		} catch (ServiceException ex) {
			throw new CommandException("Adding new book failed", ex);
		}

		BookResponse response = new BookResponse();
		if (result) {

			try {
				List<String> emails = UsersNotificationService.getUsersEmails();
				notifyUsers(emails);
			} catch (ServiceException e) {
				throw new CommandException("Reading emails failed", e);
			}

			response.setResultMessage("All OK. Book <" + newBookRequest.getTitle() + "> added.\n" +
					"Notifications have been send to users e-mails");
		} else {
			response.setErrorMessage("Can't add the book.");
		}
		return response;
	}

	private void notifyUsers(List<String> users) {
		for (String email : users) {
			String address = email.split(" ")[1];
			sendNotification(address);
		}
	}

	private void sendNotification(String email) {
		//sending message
	}

	private boolean validationParameters(Request request) {
		BookRequest newBookRequest = (BookRequest) request;
		String newBookInfo = newBookRequest.getTitle();
		if (newBookInfo.split(" ").length == 2)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
