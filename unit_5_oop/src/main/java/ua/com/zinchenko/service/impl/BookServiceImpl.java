package ua.com.zinchenko.service.impl;

import org.apache.log4j.Logger;
import ua.com.zinchenko.entity.Author;
import ua.com.zinchenko.entity.Book;
import ua.com.zinchenko.repository.AuthorRepository;
import ua.com.zinchenko.repository.BookRepository;
import ua.com.zinchenko.repository.impl.BookRepositoryImpl;
import ua.com.zinchenko.service.AuthorService;
import ua.com.zinchenko.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final Logger logger = Logger.getLogger(BookServiceImpl.class.getName());

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book getBookById(int id) {
        logger.info("Start of finding book by id");
        Book book = bookRepository.read(id).orElse(null);
        if(book == null) {
            logger.error(String.format("Did not found book by id {} {}", book.getId()));
            throw new RuntimeException("Can`t find book by id: " + id);
        } else {
            logger.info("Returning book");
            return book;
        }
    }

    boolean isBookExistByName(String bookName) {
        return !bookRepository.findBy(b -> b.getName().equals(bookName)).isEmpty();
    }

    @Override
    public List<Book> getAllBooks() {
        logger.info("Start of finding all books");
        List<Book> books = bookRepository.readAll();
        if(books.isEmpty()) {
            logger.error("Did not found any book");
            throw new RuntimeException("Did not found any book");
        } else {
            logger.info("Returning all books");
            return books;
        }
    }

    @Override
    public void removeBook(Book book) {
        logger.info("Start book remove");
        try {
            bookRepository.remove(book);
        } catch (RuntimeException e) {
            logger.error("Book with id: " + book.getId() + " is not exist");
            throw new RuntimeException(e);
        }
        logger.info("Finish book remove");
    }

    @Override
    public void updateBook(Book book) {
        logger.info("Start book update");
        try {
            bookRepository.update(book);
        } catch (RuntimeException e) {
            logger.error("Book with id: " + book.getId() + " is not exist");
            throw new RuntimeException(e);
        }
        logger.info("Finish book update");
    }

    @Override
    public void createBook(Book book) {
        logger.info("Start book create");
        String bookName = book.getName();
        if (isBookExistByName(bookName)) {
            logger.error("Book with name: " + bookName + " is exist");
            throw new RuntimeException("book exist by name: " + bookName);
        }
        book.getAuthors().forEach(a -> a.getBooks().add(book));
        bookRepository.create(book);
        logger.info("Finish book create");
    }

    @Override
    public List<Book> getByAuthor(Author author) {
        logger.info("Start books search");
        List<Book> books = bookRepository.findBy(b -> b.getAuthors().contains(author));
        if(books.isEmpty()) {
            logger.error("Don't exist books with author: " + author.getName());
            throw new RuntimeException("Don't exist books with author: " + author.getName());
        }
        logger.info("Finish books search");
        return books;
    }

    @Override
    public Book getByName(String name) {
        logger.info("Start books search by name");
        List<Book> books = bookRepository.findBy(b -> b.getName().equals(name));
        if(books.isEmpty()) {
            logger.error("Don't exist books with name: " + name);
            throw new RuntimeException("Don't exist books with name: " + name);
        }
        logger.info("Finish books search by name");
        return books.get(0);
    }
}
