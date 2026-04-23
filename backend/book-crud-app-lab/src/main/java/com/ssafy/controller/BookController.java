package com.ssafy.controller;

import java.io.IOException;
import java.util.List;

import com.ssafy.model.dto.Book;
import com.ssafy.model.service.BookService;
import com.ssafy.model.service.BookServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/books")
public class BookController extends HttpServlet implements ControllerHelper {

    private final BookService bookService = BookServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = getActionParameter(request, response);
        System.out.println(action);
        switch (action) {
            case "index"       -> forward(request, response, "/index.jsp");
            case "list"        -> list(request, response);
            case "detail"      -> detail(request, response);
            case "regist-form" -> forward(request, response, "/book/regist.jsp");
            case "delete"      -> delete(request, response);
            default            -> response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = getActionParameter(request, response);
        switch (action) {
            case "regist" -> regist(request, response);
            default       -> response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    // 도서 전체 조회
    private void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Book> bookList = bookService.getBookList();
        request.setAttribute("bookList", bookList);
        forward(request, response, "/book/list.jsp");
    }

    // 도서 상세 조회
    private void detail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String isbn = request.getParameter("isbn");
        
        Book book = bookService.getBook(isbn);
        
        request.setAttribute("book", book);
        forward(request, response, "/book/detail.jsp");
    }

    // 도서 등록
    private void regist(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String isbn   = request.getParameter("isbn");
        String title  = request.getParameter("title");
        String author = request.getParameter("author");
        int price     = Integer.parseInt(request.getParameter("price"));

        Book book = new Book(isbn, title, author, price);
        System.out.println("등록 도서: " + book);

        bookService.registBook(book);
        redirect(request, response, "/books?action=list");
    }

    // 도서 삭제
    private void delete(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String isbn = request.getParameter("isbn");
        
        bookService.removeBook(isbn);
        
        redirect(request, response, "/books?action=list");
    }
}