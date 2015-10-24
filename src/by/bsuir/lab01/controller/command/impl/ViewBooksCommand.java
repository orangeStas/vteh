package by.bsuir.lab01.controller.command.impl;

import by.bsuir.lab01.bean.Request;
import by.bsuir.lab01.bean.Response;
import by.bsuir.lab01.bean.ViewBooksResponse;
import by.bsuir.lab01.controller.command.Command;
import by.bsuir.lab01.controller.command.CommandException;
import by.bsuir.lab01.entity.Book;
import by.bsuir.lab01.service.ServiceException;
import by.bsuir.lab01.service.ViewBooksService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stas- on 10/1/2015.
 */
public class ViewBooksCommand implements Command {
    @Override
    public Response execute(Request request) throws CommandException {

        List<Book> allBooks = null;

        try {
            allBooks = ViewBooksService.getAllBooks();
        } catch (ServiceException e) {
            throw new CommandException("Can't view books.", e);
        }

        ViewBooksResponse viewBooksResponse = new ViewBooksResponse();
        if (allBooks.size() != 0) {
            viewBooksResponse.setAllBooks(allBooks);
        }
        else {
            viewBooksResponse.setErrorMessage("Can't find book.");
        }

        return viewBooksResponse;
    }

}
