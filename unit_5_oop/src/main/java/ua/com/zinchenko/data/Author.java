package ua.com.zinchenko.data;

import java.util.LinkedList;
import java.util.List;

public class Author {

    private String name;
    private String surname;
    private List<Book> books;

    public Author() {
        books = new LinkedList<>();
    }

    public Author(String name, String surname, List<Book> books) {
        this.name = name;
        this.surname = surname;
        this.books = books;
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
