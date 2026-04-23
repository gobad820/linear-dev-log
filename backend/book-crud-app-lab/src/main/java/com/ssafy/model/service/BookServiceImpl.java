package com.ssafy.model.service;

import java.util.List;
import com.ssafy.model.dao.BookDao;
import com.ssafy.model.dao.BookDaoImpl;
import com.ssafy.model.dto.Book;

public class BookServiceImpl implements BookService {

    private static BookServiceImpl instance = new BookServiceImpl();
    private final BookDao bookDao = BookDaoImpl.getInstance();

    private BookServiceImpl() {}

    public static BookServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<Book> getBookList() {
        // TODO: 01. 전체 도서 목록을 DAO에서 조회하여 반환한다.
        return bookDao.selectAll();
    }

    @Override
    public Book getBook(String isbn) {
        // TODO: 02. isbn으로 특정 도서를 DAO에서 조회하여 반환한다.
        return bookDao.selectByIsbn(isbn) ;
    }


    @Override
    public void registBook(Book book) {
        // TODO: 03. 도서를 DAO에 등록한다.
        bookDao.insert(book);
    }

    @Override
    public void removeBook(String isbn) {
        // TODO: 04. isbn으로 도서를 DAO에서 삭제한다.
        bookDao.deleteByIsbn(isbn);
    }
}
