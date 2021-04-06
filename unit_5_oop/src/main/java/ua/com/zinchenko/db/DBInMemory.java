package ua.com.zinchenko.db;

import ua.com.zinchenko.entity.Author;
import ua.com.zinchenko.entity.BaseEntity;
import ua.com.zinchenko.entity.Book;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DBInMemory {

    private final List<Book> books;
    private final List<Author> authors;

    private static DBInMemory instance;

    private DBInMemory() {
        books = new ArrayList<>();
        authors = new ArrayList<>();
    }

    public static DBInMemory getInstance() {
        if (instance == null) {
            instance = new DBInMemory();
        }
        return instance;
    }

    public <TEntity extends BaseEntity<TKey>, TKey> List<TEntity> getEntities(Class<TEntity> type) {
        if (type.equals(Author.class)) {
            return (ArrayList<TEntity>) authors;
        } else if (type.equals(Book.class)) {
            return (ArrayList<TEntity>) books;
        }
        return null;
    }
}


