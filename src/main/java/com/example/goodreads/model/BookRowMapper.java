package com.example.goodreads.model;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.example.goodreads.model.Book;

public class BookRowMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Book(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("imageUrl")
        );
    }
}
