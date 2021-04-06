package ua.com.zinchenko.service.impl;

import org.apache.log4j.Logger;
import ua.com.zinchenko.entity.Author;
import ua.com.zinchenko.entity.Book;
import ua.com.zinchenko.repository.AuthorRepository;
import ua.com.zinchenko.service.AuthorService;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final Logger logger = Logger.getLogger(BookServiceImpl.class);

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getAuthorById(int id) {
        logger.info("Start of finding author by id");
        Author author = authorRepository.read(id).orElse(null);
        if(author == null) {
            logger.error("Did not found author by id");
            throw new RuntimeException("Can`t find author by id: " + id);
        } else {
            logger.info("Returning author");
            return author;
        }
    }

    @Override
    public List<Author> getAllAuthors() {
        logger.info("Start of finding all authors");
        List<Author> authors = authorRepository.readAll();
        if(authors.isEmpty()) {
            logger.error("Did not found any author");
            throw new RuntimeException("Did not found any author");
        } else {
            logger.info("Returning all authors");
            return authors;
        }
    }

    @Override
    public void removeAuthor(Author author) {
        logger.info("Start author remove");
        try {
            authorRepository.remove(author);
        } catch (RuntimeException e) {
            logger.error("Author with id: " + author.getId() + " is not exist");
            throw new RuntimeException(e);
        }
        logger.info("Finish author remove");
    }

    @Override
    public void updateAuthor(Author author) {
        logger.info("Start author update");
        try {
            authorRepository.update(author);
        } catch (RuntimeException e) {
            logger.error("Author with id: " + author.getId() + " is not exist");
            throw new RuntimeException(e);
        }
        logger.info("Finish author update");
    }

    @Override
    public void createAuthor(Author author) {
        logger.info("Start author create");
        String authorName = author.getName();
        String authorSurname = author.getSurname();
        if (isAuthorExistByNameSurname(authorName, authorSurname)) {
            logger.error("Author with name: " + authorName
                    + " and surname: " + authorSurname +
                    " is exist");
            throw new RuntimeException("author exist by name: " + authorName
                    + " and surname: " + authorSurname);
        }
        authorRepository.create(author);
        logger.info("Finish author create");
    }

    @Override
    public List<Author> getByBook(Book book) {
        logger.info("Start authors search by book");
        List<Author> authors = authorRepository.findBy(b -> b.getBooks().contains(book));
        if(authors.isEmpty()) {
            logger.error("Don't exist author with book: " + book.getName());
            throw new RuntimeException("Don't exist authors with book: " + book.getName());
        }
        logger.info("Finish authors search by book");
        return authors;
    }

    @Override
    public Author getByName(String name, String surname) {
        logger.info("Start authors search by name");
        List<Author> authors = authorRepository
                .findBy(a -> a.getName().equals(name)
                        && a.getSurname().equals(surname));
        if(authors.isEmpty()) {
            logger.error("Don't exist author with name: " + name + " " + surname);
            throw new RuntimeException("Don't exist author with name: " + name + " " + surname);
        }
        logger.info("Finish authors search by name");
        return authors.get(0);
    }

    private boolean isAuthorExistByNameSurname(String name, String surname) {
        return !authorRepository
                .findBy(a -> a.getName().equals(name)
                        && a.getSurname().equals(surname))
                .isEmpty();
    }
}
