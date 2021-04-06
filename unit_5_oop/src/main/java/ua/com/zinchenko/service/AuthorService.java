package ua.com.zinchenko.service;

import ua.com.zinchenko.entity.Author;
import ua.com.zinchenko.entity.Book;

import java.util.List;

public interface AuthorService {

    Author getAuthorById(int id);
    List<Author> getAllAuthors();
    void removeAuthor(Author author);
    void updateAuthor(Author author);
    void createAuthor(Author author);
    List<Author> getByBook(Book book);
    Author getByName(String name, String surname);
}
