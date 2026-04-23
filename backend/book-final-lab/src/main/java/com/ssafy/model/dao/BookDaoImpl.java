package com.ssafy.model.dao;

import java.util.ArrayList;
import java.util.List;
import com.ssafy.model.dto.Book;
import com.ssafy.model.dto.Member;

public class BookDaoImpl implements BookDao {

    private static BookDaoImpl instance = new BookDaoImpl();
    private List<Book> books = new ArrayList<>();
    private List<Member> members = new ArrayList<>();

    private BookDaoImpl() {
        // 도서 더미 데이터
        books.add(new Book("9788966261208", "객체지향의 사실과 오해", "조영호", 22000));
        books.add(new Book("9788935212699", "클린 코드", "로버트 마틴", 33000));
        books.add(new Book("9788968481970", "자바의 정석", "남궁성", 42000));

        // 회원 더미 데이터
        members.add(new Member("ssafy", "1234", "김싸피"));
        members.add(new Member("admin", "admin", "관리자"));
    }

    public static BookDaoImpl getInstance() {
        // TODO: 16. instance 을(를) 조회한다.
        return instance;
    }

    @Override
    public List<Book> selectAll() {
        // TODO: 17. all 을(를) 조회한다.
        return List.copyOf(books);
    }

    @Override
    public Book selectByIsbn(String isbn) {
        // TODO: 18. by isbn 을(를) 조회한다.
        for(Book book: books){
            if(book.getIsbn().equals(isbn)){
                return book;
            }
        }
        return null;
    }

    @Override
    public void insert(Book book) {
        // TODO: 19.  을(를) 저장한다.
        books.add(book);
    }

    @Override
    public void deleteByIsbn(String isbn) {
        // TODO: 20. by isbn 을(를) 삭제한다.
        Book deletedBook = selectByIsbn(isbn);
        if(deletedBook == null){
            return;
        }
        books.remove(deletedBook);
    }

    @Override
    public Member selectMemberById(String id, String password) {
        // TODO: 21. member by id 을(를) 조회한다.
        for(Member member : members){
            if(member.getId().equals(id)){
               return member;
            }
        }
        return null;
    }
}