package by.bsuir.lab01.view;

import by.bsuir.lab01.bean.*;
import by.bsuir.lab01.controller.BookController;
import by.bsuir.lab01.controller.command.CommandName;
import by.bsuir.lab01.entity.Book;

import java.io.IOException;
import java.util.List;

public class View {
	private BookController controller = new BookController();
	private MenuHandler menuHandler = new MenuHandler(controller);
	private boolean isAdmin = false;
	
	public void menu(){

		CommandName commandName = null;
		boolean success = false;
		do {
			try {
				ConsoleHelper.writeMessage("Type your login and password..");
				LoginUserRequest request = new LoginUserRequest();
				request.setUserData(ConsoleHelper.readString());
				request.setCommandName(CommandName.LOGIN_USER.toString());
				Response response = controller.executeRequest(request);

				if (response.getErrorMessage() != null) {
					ConsoleHelper.writeMessage(response.getErrorMessage());
				}
				else {
					LoginUserResponse loginUserResponse = (LoginUserResponse) response;
					isAdmin = loginUserResponse.isAdmin();
					ConsoleHelper.writeMessage(loginUserResponse.getResultMessage());
					success = true;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} while (!success);

		do {
			try {
				if (isAdmin) {
					commandName = askAdminCommandName();
				}
				else {
					commandName = askCommandName();
				}

				switch (commandName) {
					case ADD_NEW_BOOK: menuHandler.addNewBook(commandName); break;
					case FIND_BOOK: menuHandler.findBooks(commandName); break;
					case REMOVE_BOOK: menuHandler.removeBook(commandName); break;
					case VIEW_BOOKS: menuHandler.viewAllBooks(commandName); break;
					default:
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		} while (commandName != CommandName.EXIT);

		ConsoleHelper.writeMessage("");
	}

	public CommandName askCommandName() throws IOException {
		ConsoleHelper.writeMessage("");
		ConsoleHelper.writeMessage("Choose command:");
		ConsoleHelper.writeMessage(String.format("\t %d - find books by author", CommandName.FIND_BOOK.ordinal()));
		ConsoleHelper.writeMessage(String.format("\t %d - view all books in the library", CommandName.VIEW_BOOKS.ordinal()));
		ConsoleHelper.writeMessage(String.format("\t %d - exit", CommandName.EXIT.ordinal()));

		int commandPos = ConsoleHelper.readInt();
		while (commandPos > CommandName.values().length - 1) {
			ConsoleHelper.writeMessage("Wrong number of command");
			commandPos = ConsoleHelper.readInt();
		}

		return CommandName.values()[commandPos];
	}

	public CommandName askAdminCommandName() throws IOException {
		ConsoleHelper.writeMessage("");
		ConsoleHelper.writeMessage("Choose command:");
		ConsoleHelper.writeMessage(String.format("\t %d - add new book to the library", CommandName.ADD_NEW_BOOK.ordinal()));
		ConsoleHelper.writeMessage(String.format("\t %d - remove book from the library", CommandName.REMOVE_BOOK.ordinal()));
		ConsoleHelper.writeMessage(String.format("\t %d - find books by author", CommandName.FIND_BOOK.ordinal()));
		ConsoleHelper.writeMessage(String.format("\t %d - view all books in the library", CommandName.VIEW_BOOKS.ordinal()));
		ConsoleHelper.writeMessage(String.format("\t %d - exit", CommandName.EXIT.ordinal()));

		int commandPos = ConsoleHelper.readInt();
		while (commandPos > CommandName.values().length - 1) {
			ConsoleHelper.writeMessage("Wrong number of command");
			commandPos = ConsoleHelper.readInt();
		}

		return CommandName.values()[commandPos];
	}

}
