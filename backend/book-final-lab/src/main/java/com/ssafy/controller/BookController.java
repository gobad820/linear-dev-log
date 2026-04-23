package com.ssafy.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import com.ssafy.model.dto.Book;
import com.ssafy.model.dto.Member;
import com.ssafy.model.service.BookService;
import com.ssafy.model.service.BookServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet({"/auth", "/books"})
public class BookController extends HttpServlet implements ControllerHelper {

    private final BookService bookService = BookServiceImpl.getInstance();
    private final Logger logger = Logger.getLogger(BookController.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: 01. GET 요청을 처리한다.
        String action = getActionParameter(request, response);
        switch (action) {
            case "list" -> list(request, response);
            case "detail" -> detail(request, response);
            case "delete" -> delete(request, response);
            case "login-form" -> forward(request, response, "/login.jsp");
            default -> forward(request, response, "/");
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // TODO: 02. logout 메서드를 구현한다.
        //   힌트:
        //   - request.getSession()으로 세션을 가져온다.
        //   - session.invalidate()로 세션을 무효화한다.
        request.getSession().invalidate();
        forward(request, response, "/");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: 03. POST 요청을 처리한다.
        String action = getActionParameter(request, response);
        switch (action) {
            case "login" -> login(request, response);
            case "regist" -> regist(request, response);
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // TODO: 04. login 메서드를 구현한다.
        //   힌트:
        //   - request.getSession()으로 세션을 가져온다.
        //   - setAttribute()로 속성을 설정한다.
        HttpSession session = request.getSession();
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        Member member = bookService.login(id, password);
        if (member == null) {
            // 로그인 실패
            session.setAttribute("alertMsg", "아이디 또는 비밀번호가 틀렸습니다.");
            redirect(request, response, "/auth?action=login-form");
            return;
        }

        session.setAttribute("loginMember", member);
        redirect(request, response, "/books?action=list");
    }

    // 도서 전체 조회
    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: 05.  을(를) 조회한다.
        //   힌트:
        //   - setAttribute()로 속성을 설정한다.
        List<Book> bookList = bookService.getBookList();
        request.setAttribute("bookList", bookList);
        logger.info(bookList.toString());
        forward(request, response, "/book/list.jsp");
    }

    // 도서 상세 조회
    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: 06. detail 메서드를 구현한다.
        //   힌트:
        //   - setAttribute()로 속성을 설정한다.
        Book book = bookService.getBook(request.getParameter("isbn"));
        request.setAttribute("book", book);
        forward(request, response, "/book/detail.jsp");
    }

    // 도서 등록
    private void regist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // TODO: 07. regist 메서드를 구현한다.
        String isbn = request.getParameter("isbn");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String price = request.getParameter("price");

        bookService.registBook(new Book(isbn, title, author, Integer.parseInt(price)));
        redirect(request, response, "/");
    }

    // 도서 삭제
    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // TODO: 08.  을(를) 삭제한다.
        bookService.removeBook(request.getParameter("isbn"));
        logger.info(bookService.getBook(request.getParameter("isbn")) == null ? "BOOK DELETED" : "NOT DELETED");
        redirect(request, response, "/");
    }
}