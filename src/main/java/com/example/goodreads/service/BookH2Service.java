package com.example.goodreads.service;

import com.example.goodreads.repository.BookRepository;
import com.example.goodreads.model.Book;
import com.example.goodreads.model.BookRowMapper; 
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
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
        try{
            Book book = db.queryForObject("select * from book where id =?",new BookRowMapper(),bookId);
            return book;
        } 
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        
    }

    @Override
    public Book addBook(Book book) {
        db.update("insert into book(name,imageUrl) values(?,?)",book.getName(),book.getImageUrl());
        Book saved = db.queryForObject("select * from book where name=? AND imageUrl=?",
        new BookRowMapper(),book.getName(),book.getImageUrl());
        return saved;
    }

    @Override
    public Book updateBook(int bookId, Book book) { 
        if(book.getName()!=null){
            db.update("update book set name=?,imageUrl=? where id=?",book.getName(),book.getImageUrl(),bookId);
        }
        return getBookById(bookId);
    }

    @Override
    public void deleteBook(int bookId) {
        // implementation
    }
}
