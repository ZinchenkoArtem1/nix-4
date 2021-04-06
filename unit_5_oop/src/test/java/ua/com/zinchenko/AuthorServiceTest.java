package ua.com.zinchenko;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ua.com.zinchenko.entity.Author;
import ua.com.zinchenko.entity.Book;
import ua.com.zinchenko.repository.AuthorRepository;
import ua.com.zinchenko.repository.impl.AuthorRepositoryImpl;
import ua.com.zinchenko.service.AuthorService;
import ua.com.zinchenko.service.impl.AuthorServiceImpl;

public class AuthorServiceTest {

    private final AuthorRepository authorRepository = new AuthorRepositoryImpl();
    private  final AuthorService authorService = new AuthorServiceImpl(authorRepository);

    @Test
    public void create() {
        Author testAuthor = new Author();
        testAuthor.setName("Name1");
        testAuthor.setSurname("Surname1");
        authorService.createAuthor(testAuthor);
        Author authorFromBd = authorService.getAuthorById(0);
        Assert.assertEquals(testAuthor, authorFromBd);
    }
}
