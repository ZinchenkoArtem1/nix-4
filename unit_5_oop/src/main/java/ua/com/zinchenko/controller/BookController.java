package ua.com.zinchenko.controller;

import ua.com.zinchenko.entity.Author;
import ua.com.zinchenko.entity.Book;
import ua.com.zinchenko.service.AuthorService;
import ua.com.zinchenko.service.BookService;

import java.util.List;
import java.util.Scanner;

public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
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
                    printBook(getBookById());
                    break;
                case 2:
                    printBook(getBookByName());
                    break;
                case 3:
                    printBooks(getBookByAuthor());
                    break;
                case 4:
                    createBook();
                    break;
                case 5:
                    deleteBook();
                    break;
                case 6:
                    updateBook();
                    break;
                case 7:
                    printBooks(bookService.getAllBooks());
                    break;
                case 8:
                    isWork = false;
                    break;
            }
        }
    }

    private void loadMenu() {
        System.out.println("Choose task:");
        System.out.println("1. Get book by id");
        System.out.println("2. Get book by name");
        System.out.println("3. Get book by author");
        System.out.println("4. Create book");
        System.out.println("5. Delete book");
        System.out.println("6. Update book");
        System.out.println("7. Get all books");
        System.out.println("8. Exit application");
    }

    private void printBook(Book book) {
        System.out.println("---------------------");
        System.out.println("Book id:" + book.getId());
        System.out.println("Book name:" + book.getName());
        System.out.println("---------------------");
    }

    private void printBooks(List<Book> books) {
        for (Book book: books) {
            printBook(book);
        }
    }

    private Book getBookById() {
        System.out.println("Input id of book:");
        int bookId = new Scanner(System.in).nextInt();
        return bookService.getBookById(bookId);
    }

    private Book getBookByName() {
        System.out.println("Input name of book:");
        String bookName = new Scanner(System.in).next();
        return bookService.getByName(bookName);
    }

    private List<Book> getBookByAuthor() {
        System.out.println("Input id of author:");
        int authorId = new Scanner(System.in).nextInt();
        return bookService.getByAuthor(authorService.getAuthorById(authorId));
    }

    private void createBook() {
        System.out.println("Input book name:");
        String bookName = new Scanner(System.in).next();
        System.out.println("Input author id for add to book authors");
        int authorId = new Scanner(System.in).nextInt();
        Author author = authorService.getAuthorById(authorId);
        Book book = new Book();
        book.setName(bookName);
        book.getAuthors().add(author);
        bookService.createBook(book);
    }

    private void deleteBook() {
        System.out.println("Input id of book:");
        int bookId = new Scanner(System.in).nextInt();
        bookService.removeBook(bookService.getBookById(bookId));
    }

    private void updateBook() {
        System.out.println("Input id of book for update:");
        int bookId = new Scanner(System.in).nextInt();
        System.out.println("Input author id for add to book authors");
        int authorId = new Scanner(System.in).nextInt();
        Book book = bookService.getBookById(bookId);
        book.getAuthors().add(authorService.getAuthorById(authorId));
        bookService.updateBook(book);
    }
}
