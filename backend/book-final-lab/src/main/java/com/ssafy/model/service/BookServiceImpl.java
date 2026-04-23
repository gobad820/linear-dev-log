package com.ssafy.model.service;

import java.util.List;
import com.ssafy.model.dao.BookDao;
import com.ssafy.model.dao.BookDaoImpl;
import com.ssafy.model.dto.Book;
import com.ssafy.model.dto.Member;

public class BookServiceImpl implements BookService {

    private static BookServiceImpl instance = new BookServiceImpl();
    private final BookDao bookDao = BookDaoImpl.getInstance();

    private BookServiceImpl() {}

    public static BookServiceImpl getInstance() {
        // TODO: 10. instance 을(를) 조회한다.
        return instance;
    }

    @Override
    public List<Book> getBookList() {
        // TODO: 11. book list 을(를) 조회한다.
        return bookDao.selectAll();
    }

    @Override
    public Book getBook(String isbn) {
        // TODO: 12. book 을(를) 조회한다.
        return bookDao.selectByIsbn(isbn);
    }

    @Override
    public void registBook(Book book) {
        // TODO: 13. registBook 메서드를 구현한다.
        bookDao.insert(book);
    }

    @Override
    public void removeBook(String isbn) {
        // TODO: 14. book 을(를) 삭제한다.
        bookDao.deleteByIsbn(isbn);
    }

    @Override
    public Member login(String id, String password) {
        // TODO: 15. login 메서드를 구현한다.
        Member member = new Member();
        member.setId(id);
        member.setPassword(password);
        return  member;
    }
}