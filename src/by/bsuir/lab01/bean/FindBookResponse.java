package by.bsuir.lab01.bean;

import by.bsuir.lab01.entity.Book;

import java.util.List;

/**
 * Created by stas- on 9/28/2015.
 */
public class FindBookResponse extends BookResponse {
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
