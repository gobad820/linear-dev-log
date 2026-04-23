package com.ssafy.model.dao;

import java.util.List;
import com.ssafy.model.dto.Book;

public interface BookDao {
    List<Book> selectAll();
    Book selectByIsbn(String isbn);
    void insert(Book book);
    void deleteByIsbn(String isbn);
}