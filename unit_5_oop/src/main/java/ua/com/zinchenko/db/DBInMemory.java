package ua.com.zinchenko.db;

import ua.com.zinchenko.entity.Author;
import ua.com.zinchenko.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class DBInMemory {

    private final List<Book> books;
    private final List<Author> authors;

    private static DBInMemory instance;

    private DBInMemory() {
        books = new ArrayList<>();
        authors = new ArrayList<>();
    }

    public static DBInMemory getInstance() {
        if (instance == null) {
            instance = new DBInMemory();
        }
        return instance;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public List<Book> getBooks() {
        return books;
    }
}
