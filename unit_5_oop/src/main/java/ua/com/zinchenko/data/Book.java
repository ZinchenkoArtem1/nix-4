package ua.com.zinchenko.data;

import java.util.LinkedList;
import java.util.List;

public class Book {

    private String name;
    private List<Author> authors;

    private Book() {
        authors = new LinkedList<>();
    }

    public Book(String name, List<Author> authors) {
        this.name = name;
        this.authors = authors;
    }
}
