package ua.com.zinchenko;

import ua.com.zinchenko.repository.AuthorRepository;
import ua.com.zinchenko.repository.BookRepository;
import ua.com.zinchenko.repository.impl.AuthorRepositoryImpl;
import ua.com.zinchenko.repository.impl.BookRepositoryImpl;
import ua.com.zinchenko.service.AuthorService;
import ua.com.zinchenko.service.BookService;
import ua.com.zinchenko.service.impl.AuthorServiceImpl;
import ua.com.zinchenko.service.impl.BookServiceImpl;

public class App {
    public static void main(String[] args) {
        AuthorRepository authorRepository = new AuthorRepositoryImpl();
        BookRepository bookRepository = new BookRepositoryImpl();

        AuthorService authorService = new AuthorServiceImpl(authorRepository);
        BookService bookService = new BookServiceImpl(bookRepository);



    }
}
