package ua.com.zinchenko;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import ua.com.zinchenko.entity.Author;
import ua.com.zinchenko.entity.Book;
import ua.com.zinchenko.repository.AuthorRepository;
import ua.com.zinchenko.repository.impl.AuthorRepositoryImpl;
import ua.com.zinchenko.service.AuthorService;
import ua.com.zinchenko.service.impl.AuthorServiceImpl;

public class AuthorServiceTest {

    private final static AuthorService authorService = new AuthorServiceImpl(new AuthorRepositoryImpl());
    private final static String authorName = "testName";
    private final static String authorSurname = "testSurname";

    @After
    public void clean() {
        authorService.getAllAuthors().clear();
    }

    @Test
    public void createTest() {
        authorService.createAuthor(new Author(authorName, authorSurname));
        Assert.assertNotNull(authorService.getByName(authorName, authorSurname));
    }

    @Test
    public void getByIdTest() {
        Author author = new Author(authorName, authorSurname);
        int authorId = author.getId();
        authorService.createAuthor(author);
        Assert.assertEquals(authorService.getAuthorById(authorId).getName(), author.getName());
    }

    @Test
    public void getAllTest() {
        for(int i = 0; i < 10; i++) {
            authorService.createAuthor(new Author(authorName + i, authorSurname + i));
        }
        Assert.assertEquals(authorService.getAllAuthors().size(), 10);
    }

    @Test
    public void deleteTest() {
        for(int i = 0; i < 10; i++) {
            authorService.createAuthor(new Author(authorName + i, authorSurname + i));
        }
        for(int i = 0; i < 5; i++) {
            authorService.removeAuthor(authorService.getByName(authorName + i, authorSurname + i));
        }
        Assert.assertEquals(authorService.getAllAuthors().size(), 5);
    }

    @Test
    public void updateTest() {
        for(int i = 0; i < 10; i++) {
            authorService.createAuthor(new Author(authorName + i, authorSurname + i));
        }
        Author author = authorService.getByName(authorName + 5, authorSurname + 5);
        int authorId = author.getId();
        author.setSurname(authorSurname + "new");
        author.setName(authorName + "new");
        authorService.updateAuthor(author);
        Assert.assertEquals(authorService.getAuthorById(authorId).getName(), author.getName());
        Assert.assertEquals(authorService.getAuthorById(authorId).getSurname(), author.getSurname());
    }
}
