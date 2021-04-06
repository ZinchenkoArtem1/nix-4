package ua.com.zinchenko.repository.impl;

import ua.com.zinchenko.entity.Book;

import ua.com.zinchenko.repository.BookRepository;

public class BookRepositoryImpl extends GenericRepositoryImpl<Book, Integer> implements BookRepository{

    public BookRepositoryImpl() {
        super(Book.class);
    }
}
