package ua.com.zinchenko;

import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import ua.com.zinchenko.entity.Author;
import ua.com.zinchenko.entity.Book;
import ua.com.zinchenko.repository.AuthorRepository;
import ua.com.zinchenko.repository.impl.AuthorRepositoryImpl;
import ua.com.zinchenko.repository.impl.BookRepositoryImpl;
import ua.com.zinchenko.service.AuthorService;
import ua.com.zinchenko.service.BookService;
import ua.com.zinchenko.service.impl.AuthorServiceImpl;
import ua.com.zinchenko.service.impl.BookServiceImpl;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommonTest {

    private static final AuthorService authorService = new AuthorServiceImpl(new AuthorRepositoryImpl());
    private static final BookService bookService = new BookServiceImpl(new BookRepositoryImpl());
    private static final String authorName = "authorName";
    private static final String authorSurname = "authorSurname";
    private static final String bookName = "bookName";

    @Test
    @Order(1)
    public void create() {
        for (int i = 0; i < 10; i++) {
            Author author = new Author();
            author.setName(authorName + i);
            author.setSurname(authorSurname + i);
            authorService.createAuthor(author);
        }
        Assertions.assertEquals(authorService.getAllAuthors().size(), 10);

        for (int i = 0; i < 10; i++) {
            Book book = new Book();
            book.setName(bookName + i);
            book.getAuthors().add(authorService.getAllAuthors().get(i));
            bookService.createBook(book);
        }
        Assertions.assertEquals(bookService.getAllBooks().size(), 10);
    }

    @Test
    @Order(2)
    public void update() {
        Author author = authorService.getByName(authorName + 0, authorSurname + 0);
        int authorId = author.getId();
        author.setName(authorName + 10);
        authorService.updateAuthor(author);

        author = authorService.getAuthorById(authorId);

        Assertions.assertEquals(authorName + 10, author.getName());
    }

    @Test
    @Order(3)
    public void findAuthorByBook() {
        Book book = bookService.getByName(bookName + 1);
        List<Author> authors = book.getAuthors();

        Assertions.assertEquals(authors.get(0).getName(), authorName + 1);
    }

    @Test
    @Order(3)
    public void findBookByAuthor() {
        Author author = authorService.getByName(authorName + 1, authorSurname + 1);
        List<Book> books = author.getBooks();

        Assertions.assertEquals(books.get(0).getName(), bookName + 1);
    }

    @Test
    @Order(4)
    public void delete() {
        Author author = authorService.getByName(authorName + 2, authorSurname + 2);
        authorService.removeAuthor(author);

        try {
            Author author1 = authorService.getByName(authorName + 2, authorSurname + 2);
            Assertions.fail();
        } catch (RuntimeException e) {
            Assertions.assertTrue(true);
        }
    }


}
