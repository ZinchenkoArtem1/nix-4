package ua.com.zinchenko;

import ua.com.zinchenko.controller.AuthorController;
import ua.com.zinchenko.controller.BookController;
import ua.com.zinchenko.repository.AuthorRepository;
import ua.com.zinchenko.repository.BookRepository;
import ua.com.zinchenko.repository.impl.AuthorRepositoryImpl;
import ua.com.zinchenko.repository.impl.BookRepositoryImpl;
import ua.com.zinchenko.service.AuthorService;
import ua.com.zinchenko.service.BookService;
import ua.com.zinchenko.service.impl.AuthorServiceImpl;
import ua.com.zinchenko.service.impl.BookServiceImpl;

import java.util.Scanner;

public class App {
    private final AuthorController authorController;
    private final BookController bookController;

    public App() {
        BookService bookService = new BookServiceImpl(new BookRepositoryImpl());
        AuthorService authorService = new AuthorServiceImpl(new AuthorRepositoryImpl());
        authorController = new AuthorController(bookService, authorService);
        bookController = new BookController(bookService, authorService);
    }
    public void run() {
        boolean isWork = true;
        int exp;
        Scanner scanner;
        while (isWork) {
            loadMenu();
            scanner = new Scanner(System.in);
            exp = scanner.nextInt();

            switch (exp) {
                case 1:
                   bookController.run();
                   break;
                case 2:
                    authorController.run();
                    break;
                case 3:
                    isWork = false;
                    break;
            }
        }
    }

    private void loadMenu() {
        System.out.println("Choose task:");
        System.out.println("1. Book db");
        System.out.println("2. Author db");
        System.out.println("3. Exit app");
    }
}
