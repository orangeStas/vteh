package by.bsuir.lab01.view;

import by.bsuir.lab01.bean.*;
import by.bsuir.lab01.controller.BookController;
import by.bsuir.lab01.controller.command.CommandName;
import by.bsuir.lab01.entity.Book;

import java.io.IOException;
import java.util.List;

/**
 * Created by stas- on 10/2/2015.
 */
public class MenuHandler {

    private BookController controller;

    public MenuHandler(BookController controller) {
        this.controller = controller;
    }

    public void addNewBook(CommandName commandName) throws IOException {
        BookRequest request = new BookRequest();
        request.setCommandName(commandName.toString());
        ConsoleHelper.writeMessage("Type title and author for new book");
        request.setTitle(ConsoleHelper.readString());

        Response response = controller.executeRequest(request);
        if(response.getErrorMessage() != null)
        {
            ConsoleHelper.writeMessage(response.getErrorMessage());
        }
        else
        {
            BookResponse newBookREsponse = (BookResponse)response;
            ConsoleHelper.writeMessage(newBookREsponse.getResultMessage());
        }
    }

    public void findBooks(CommandName commandName) throws IOException {
        FindBookRequest request = new FindBookRequest();
        request.setCommandName(commandName.toString());
        ConsoleHelper.writeMessage("Type author..");
        request.setTitle(ConsoleHelper.readString());

        Response response = controller.executeRequest(request);
        if(response.getErrorMessage() != null){
            ConsoleHelper.writeMessage(response.getErrorMessage());
        }else {
            FindBookResponse findBookResponse = (FindBookResponse) response;
            ConsoleHelper.writeMessage(findBookResponse.getResultMessage());
            List<Book> bookList = findBookResponse.getBooks();
            ConsoleHelper.writeMessage("Found books:");
            for (Book book : bookList) {
                ConsoleHelper.writeMessage(book.getTitle() + " - " + book.getAuthor());
            }
        }
    }

    public void removeBook(CommandName commandName) throws IOException {
        BookRequest request = new BookRequest();
        request.setCommandName(commandName.toString());
        ConsoleHelper.writeMessage("Type title removing book..");
        request.setTitle(ConsoleHelper.readString());

        Response response = controller.executeRequest(request);
        if(response.getErrorMessage() != null){
            ConsoleHelper.writeMessage(response.getErrorMessage());
        }else
        {
            BookResponse removeBookResponse = (BookResponse) response;
            ConsoleHelper.writeMessage(removeBookResponse.getResultMessage());
        }
    }

    public void viewAllBooks(CommandName commandName) {
        ViewBooksRequest request = new ViewBooksRequest();
        request.setCommandName(commandName.toString());

        Response response = controller.executeRequest(request);
        if(response.getErrorMessage() != null){
            ConsoleHelper.writeMessage(response.getErrorMessage());
        }else {
            ViewBooksResponse viewBooksResponse = (ViewBooksResponse) response;
            List<Book> bookList = viewBooksResponse.getAllBooks();
            ConsoleHelper.writeMessage("All books:");
            for (Book book : bookList) {
                ConsoleHelper.writeMessage(book.getTitle() + " - " + book.getAuthor());
            }
        }
    }
}
