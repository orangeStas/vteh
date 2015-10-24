package by.bsuir.lab01.bean;

import by.bsuir.lab01.entity.Book;

import java.util.List;

/**
 * Created by stas- on 10/1/2015.
 */
public class ViewBooksResponse extends Response {
    private List<Book> allBooks;

    public List<Book> getAllBooks() {
        return allBooks;
    }

    public void setAllBooks(List<Book> allBooks) {
        this.allBooks = allBooks;
    }
}
