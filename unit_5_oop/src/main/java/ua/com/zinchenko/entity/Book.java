package ua.com.zinchenko.entity;

import java.util.ArrayList;
import java.util.List;

public class Book extends BaseEntity<Integer>{

    private static Integer idCounter;
    private String name;
    private List<Author> authors;

    private Book() {
        super(idCounter++);
        authors = new ArrayList<>();
    }

    public Book(String name) {
        super(idCounter++);
        this.name = name;
        authors = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
