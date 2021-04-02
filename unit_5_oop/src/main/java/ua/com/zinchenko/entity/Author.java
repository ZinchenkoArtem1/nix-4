package ua.com.zinchenko.entity;

import java.util.ArrayList;
import java.util.List;

public class Author extends BaseEntity<Integer>{

    private static Integer idCounter;
    private String name;
    private String surname;
    private List<Book> books;

    public Author() {
        super(idCounter++);
        books = new ArrayList<>();
    }

    public Author(String name, String surname) {
        super(idCounter++);
        this.name = name;
        this.surname = surname;
        this.books = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
