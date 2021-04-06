package ua.com.zinchenko;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import ua.com.zinchenko.entity.Author;
import ua.com.zinchenko.entity.Book;
import ua.com.zinchenko.repository.impl.AuthorRepositoryImpl;
import ua.com.zinchenko.repository.impl.BookRepositoryImpl;
import ua.com.zinchenko.service.AuthorService;
import ua.com.zinchenko.service.BookService;
import ua.com.zinchenko.service.impl.AuthorServiceImpl;
import ua.com.zinchenko.service.impl.BookServiceImpl;

public class BookServiceTest {

    private final static BookService bookService = new BookServiceImpl(new BookRepositoryImpl());
    private final static String bookName = "testName";

    @After
    public void clean() {
        bookService.getAllBooks().clear();
    }

    @Test
    public void createTest() {
        bookService.createBook(new Book(bookName));
        Assert.assertNotNull(bookService.getByName(bookName));
    }

    @Test
    public void getByIdTest() {
        Book book = new Book(bookName);
        int bookId = book.getId();
        bookService.createBook(book);
        Assert.assertEquals(bookService.getBookById(bookId).getName(), book.getName());
    }

    @Test
    public void getAllTest() {
        for(int i = 0; i < 10; i++) {
            bookService.createBook(new Book(bookName + i));
        }
        Assert.assertEquals(bookService.getAllBooks().size(), 10);
    }

    @Test
    public void deleteTest() {
        for(int i = 0; i < 10; i++) {
            bookService.createBook(new Book(bookName + i));
        }
        for(int i = 0; i < 5; i++) {
            bookService.removeBook(bookService.getByName(bookName + i));
        }
        Assert.assertEquals(bookService.getAllBooks().size(), 5);
    }

    @Test
    public void updateTest() {
        for(int i = 0; i < 10; i++) {
            bookService.createBook(new Book(bookName + i));
        }
        Book book = bookService.getByName(bookName + 5);
        int bookId = book.getId();
        book.setName(bookName + "new");
        bookService.updateBook(book);
        Assert.assertEquals(bookService.getBookById(bookId).getName(), book.getName());
    }
}
