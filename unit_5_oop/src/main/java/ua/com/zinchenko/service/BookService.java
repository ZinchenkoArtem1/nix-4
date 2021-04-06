package ua.com.zinchenko.service;

import ua.com.zinchenko.entity.Author;
import ua.com.zinchenko.entity.Book;

import java.util.List;

public interface BookService {

    Book getBookById(int id);

    List<Book> getAllBooks();

    void removeBook(Book Book);

    void updateBook(Book Book);

    void createBook(Book Book);

    List<Book> getByAuthor(Author author);

    Book getByName(String name);
}
