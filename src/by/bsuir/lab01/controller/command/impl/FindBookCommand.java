package by.bsuir.lab01.controller.command.impl;

import by.bsuir.lab01.bean.*;
import by.bsuir.lab01.controller.command.Command;
import by.bsuir.lab01.controller.command.CommandException;
import by.bsuir.lab01.entity.Book;
import by.bsuir.lab01.service.FindBookService;
import by.bsuir.lab01.service.ServiceException;

import java.util.List;

/**
 * Created by stas- on 9/28/2015.
 */
public class FindBookCommand implements Command {
    @Override
    public Response execute(Request request) throws CommandException {
        if (!validationParameters(request))
        {
            throw new CommandException("Validation Exception.");
        }

        BookRequest findBookRequest = (BookRequest) request;
        List<Book> foundBooks;

        try {
            foundBooks = FindBookService.findBooks(findBookRequest.getTitle());
        } catch (ServiceException e) {
            throw new CommandException("Can't find book with title:" + findBookRequest.getTitle(), e);
        }

        FindBookResponse response = new FindBookResponse();

        if (foundBooks.size() != 0) {
            response.setBooks(foundBooks);
            response.setResultMessage("All ok.");
        }
        else
        {
            response.setErrorMessage("Can't find book.");
        }

        return response;
    }

    private boolean validationParameters(Request request) {
        FindBookRequest findBookRequest = (FindBookRequest) request;
        String newBookInfo = findBookRequest.getTitle();
        if (newBookInfo.split(" ").length == 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
