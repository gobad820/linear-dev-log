package com.ssafy.model.dao;

import java.util.List;
import com.ssafy.model.dto.Book;
import com.ssafy.model.dto.Member;

public interface BookDao {
    List<Book> selectAll();
    Book selectByIsbn(String isbn);
    void insert(Book book);
    void deleteByIsbn(String isbn);

    // 로그인
    Member selectMemberById(String id, String password);
}