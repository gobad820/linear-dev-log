package com.ssafy.model.service;

import java.util.List;
import com.ssafy.model.dto.Book;
import com.ssafy.model.dto.Member;

public interface BookService {
    List<Book> getBookList();
    Book getBook(String isbn);
    void registBook(Book book);
    void removeBook(String isbn);

    // 로그인
    Member login(String id, String password);
}