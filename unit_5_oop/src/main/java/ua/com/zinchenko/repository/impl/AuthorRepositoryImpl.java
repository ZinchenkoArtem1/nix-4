package ua.com.zinchenko.repository.impl;

import ua.com.zinchenko.db.DBInMemory;
import ua.com.zinchenko.entity.Author;
import ua.com.zinchenko.entity.Book;
import ua.com.zinchenko.repository.AuthorRepository;

import java.util.List;

public class AuthorRepositoryImpl extends GenericRepositoryImpl<Author, Integer> implements AuthorRepository {

    public AuthorRepositoryImpl() {
        super(Author.class);
    }

}
