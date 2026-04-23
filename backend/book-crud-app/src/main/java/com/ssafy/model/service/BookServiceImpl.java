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
        return bookDao.selectAll();
    }

    @Override
    public Book getBook(String isbn) {
        return bookDao.selectByIsbn(isbn);
    }

    @Override
    public void registBook(Book book) {
        bookDao.insert(book);
    }

    @Override
    public void removeBook(String isbn) {
        bookDao.deleteByIsbn(isbn);
    }
}