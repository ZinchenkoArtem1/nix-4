package ua.com.zinchenko.controller;

import ua.com.zinchenko.entity.Author;
import ua.com.zinchenko.entity.Book;
import ua.com.zinchenko.service.AuthorService;
import ua.com.zinchenko.service.BookService;

import java.util.List;
import java.util.Scanner;

public class AuthorController {

    private final BookService bookService;
    private final AuthorService authorService;

    public AuthorController(BookService bookService, AuthorService authorService) {
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
                    printAuthor(getAuthorById());
                    break;
                case 2:
                    printAuthor(getAuthorByName());
                    break;
                case 3:
                    printAuthors(getAuthorsByBook());
                    break;
                case 4:
                    createAuthor();
                    break;
                case 5:
                    deleteAuthor();
                    break;
                case 6:
                    printAuthors(authorService.getAllAuthors());
                    break;
                case 7:
                    isWork = false;
                    break;
            }
        }
    }

    private void loadMenu() {
        System.out.println("Choose task:");
        System.out.println("1. Get author by id");
        System.out.println("2. Get author by name");
        System.out.println("3. Get author by book");
        System.out.println("4. Create author");
        System.out.println("5. Delete author");
        System.out.println("6. Get all authors");
        System.out.println("7. Exit application");
    }

    private void printAuthor(Author author) {
        System.out.println("---------------------");
        System.out.println("Author id:" + author.getId());
        System.out.println("Author name:" + author.getName());
        System.out.println("Author surname:" + author.getSurname());
        System.out.println("---------------------");
    }

    private void printAuthors(List<Author> authors) {
        for (Author author : authors) {
            printAuthor(author);
        }
    }

    private Author getAuthorById() {
        System.out.println("Input id of author:");
        int authorId = new Scanner(System.in).nextInt();
        return authorService.getAuthorById(authorId);
    }

    private Author getAuthorByName() {
        System.out.println("Input name of author:");
        String authorName = new Scanner(System.in).next();
        System.out.println("Input surname of author:");
        String authorSurname = new Scanner(System.in).next();
        return authorService.getByName(authorName, authorSurname);
    }

    private List<Author> getAuthorsByBook() {
        System.out.println("Input id of book:");
        int bookId = new Scanner(System.in).nextInt();
        return authorService.getByBook(bookService.getBookById(bookId));
    }

    private void createAuthor() {
        System.out.println("Input name of author:");
        String authorName = new Scanner(System.in).next();
        System.out.println("Input surname of author:");
        String authorSurname = new Scanner(System.in).next();
        Author author = new Author();
        author.setName(authorName);
        author.setSurname(authorSurname);
        authorService.createAuthor(author);
    }

    private void deleteAuthor() {
        System.out.println("Input id of author:");
        int authorId = new Scanner(System.in).nextInt();
        authorService.removeAuthor(authorService.getAuthorById(authorId));
    }
}
