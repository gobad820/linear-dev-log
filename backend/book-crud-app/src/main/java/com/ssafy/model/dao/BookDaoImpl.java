package com.ssafy.model.dao;

import java.util.ArrayList;
import java.util.List;
import com.ssafy.model.dto.Book;

public class BookDaoImpl implements BookDao {

    private static BookDaoImpl instance = new BookDaoImpl();
    private List<Book> books = new ArrayList<>();

    private BookDaoImpl() {
        // 초기 더미 데이터
        books.add(new Book("9788966261208", "객체지향의 사실과 오해", "조영호", 22000));
        books.add(new Book("9788935212699", "클린 코드", "로버트 마틴", 33000));
        books.add(new Book("9788968481970", "자바의 정석", "남궁성", 42000));
    }

    public static BookDaoImpl getInstance() {
        return instance;
    }

    @Override
    public List<Book> selectAll() {
        return books;
    }

    @Override
    public Book selectByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public void insert(Book book) {
        books.add(book);
    }


    @Override
    public void deleteByIsbn(String isbn) {
        books.removeIf(book -> book.getIsbn().equals(isbn));
    }
}