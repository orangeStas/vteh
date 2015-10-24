package by.bsuir.lab01.controller.command.impl;

import by.bsuir.lab01.bean.BookRequest;
import by.bsuir.lab01.bean.BookResponse;
import by.bsuir.lab01.bean.Request;
import by.bsuir.lab01.bean.Response;
import by.bsuir.lab01.controller.command.Command;
import by.bsuir.lab01.controller.command.CommandException;
import by.bsuir.lab01.service.ModificationRepositoryService;
import by.bsuir.lab01.service.ServiceException;

/**
 * Created by stas- on 10/1/2015.
 */
public class RemoveBookCommand implements Command {
    @Override
    public Response execute(Request request) throws CommandException {
        if (!validationParameters(request)) {
            throw new CommandException("Validation Exception.");
        }

        // call service
        BookRequest removeBookRequest = (BookRequest) request;
        boolean result;
        try {
            result = ModificationRepositoryService
                    .removeBookService(removeBookRequest.getTitle());
        } catch (ServiceException ex) {
            throw new CommandException("Removing new book failed", ex);
        }

        // create response
        BookResponse response = new BookResponse();
        if (result) {
            response.setResultMessage("Book <" + removeBookRequest.getTitle() + "> was removed");
        } else {
            response.setErrorMessage("Can't remove the book.");
        }
        return response;
    }

    private boolean validationParameters(Request request) {
        BookRequest removeBookRequest = (BookRequest) request;
        String newBookInfo = removeBookRequest.getTitle();
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
