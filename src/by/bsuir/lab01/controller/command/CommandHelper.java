package by.bsuir.lab01.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.bsuir.lab01.controller.command.impl.*;

public final class CommandHelper {
	private Map<CommandName, Command> commands = new HashMap<CommandName, Command>();
	
	public CommandHelper(){
		commands.put(CommandName.ADD_NEW_BOOK, new AddNewBookCommand());
		commands.put(CommandName.LOGIN_USER, new LoginUserCommand());
		commands.put(CommandName.FIND_BOOK, new FindBookCommand());
		commands.put(CommandName.REMOVE_BOOK, new RemoveBookCommand());
		commands.put(CommandName.VIEW_BOOKS, new ViewBooksCommand());
	}
	
	public Command getCommand(String commandName){
		CommandName command = CommandName.valueOf(commandName);
		return commands.get(command);		
	}
}
