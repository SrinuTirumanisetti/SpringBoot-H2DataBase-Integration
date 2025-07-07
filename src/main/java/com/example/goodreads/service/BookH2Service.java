package com.example.goodreads.service;

import com.example.goodreads.repository.BookRepository;
import com.example.goodreads.model.Book;
import com.example.goodreads.model.BookRowMapper; 
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;

@Service
public class BookH2Service implements BookRepository {

    @Autowired
    private JdbcTemplate db;

    @Override
    public ArrayList<Book> getBooks() {
        List<Book> bookList = db.query("select * from book", new BookRowMapper());
        return new ArrayList<>(bookList); 
    }

    @Override
    public Book getBookById(int bookId) {  
        return new Book(3, "sample", "sample.png");
    }

    @Override
    public Book addBook(Book book) {
        return new Book(3, "sample", "sample.png");
    }

    @Override
    public Book updateBook(int bookId, Book book) { 
        return new Book(3, "sample", "sample.png");
    }

    @Override
    public void deleteBook(int bookId) {
        // implementation
    }
}
