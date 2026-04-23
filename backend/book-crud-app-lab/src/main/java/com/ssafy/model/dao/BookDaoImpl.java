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
        // TODO: 05. 저장된 전체 도서 목록을 반환한다.
        return List.copyOf(books);
    }

    @Override
    public Book selectByIsbn(String isbn) {
        // TODO: 06. isbn과 일치하는 도서를 반환한다.
        //   - 목록을 순회하며 isbn이 같은 도서를 찾는다.
        //   - 없으면 null을 반환한다.
        for(Book book : books){
            if(book.getIsbn().equals(isbn)){
                return book;
            }
        }
        return new Book();
    }

    @Override
    public void insert(Book book) {
        // TODO: 07. 도서를 목록에 추가한다
        books.add(book);
    }

    @Override
    public void deleteByIsbn(String isbn) {
        // TODO: 08. isbn과 일치하는 도서를 목록에서 삭제한다.
        Book deletedBook = selectByIsbn(isbn);
        if(deletedBook.getIsbn() == null || deletedBook.getIsbn().isBlank()) {
            return;
        }
        books.remove(deletedBook);
    }
}
