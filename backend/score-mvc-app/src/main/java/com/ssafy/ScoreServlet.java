package com.ssafy;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/score")
public class ScoreServlet extends HttpServlet {

    private ScoreService scoreService = new ScoreServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        String action = request.getParameter("action");
        if (action == null) action = "";

        switch (action) {
        case "average" -> average(request, response);
        case "grade"   -> grade(request, response);
        case "pass"    -> pass(request, response);
        default -> response.getWriter().println("<h3>action 파라미터를 확인하세요</h3>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void average(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int kor  = Integer.parseInt(request.getParameter("kor"));
        int eng  = Integer.parseInt(request.getParameter("eng"));
        int math = Integer.parseInt(request.getParameter("math"));

        double average = scoreService.average(kor, eng, math);

        System.out.println("average() 실행");

        request.setAttribute("kor", kor);
        request.setAttribute("eng", eng);
        request.setAttribute("math", math);
        request.setAttribute("average", average);

        request.getRequestDispatcher("/average.jsp").forward(request, response);
    }

    private void grade(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int kor  = Integer.parseInt(request.getParameter("kor"));
        int eng  = Integer.parseInt(request.getParameter("eng"));
        int math = Integer.parseInt(request.getParameter("math"));

        String grade = scoreService.grade(kor, eng, math);

        System.out.println("grade() 실행");

        request.setAttribute("kor", kor);
        request.setAttribute("eng", eng);
        request.setAttribute("math", math);
        request.setAttribute("grade", grade);

        request.getRequestDispatcher("/grade.jsp").forward(request, response);
    }

    private void pass(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int kor  = Integer.parseInt(request.getParameter("kor"));
        int eng  = Integer.parseInt(request.getParameter("eng"));
        int math = Integer.parseInt(request.getParameter("math"));

        String result = scoreService.pass(kor, eng, math);

        System.out.println("pass() 실행");

        request.setAttribute("kor", kor);
        request.setAttribute("eng", eng);
        request.setAttribute("math", math);
        request.setAttribute("pass", result);

        request.getRequestDispatcher("/pass.jsp").forward(request, response);
    }
}
