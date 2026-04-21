package com.ssafy;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/score")
public class ScoreServlet extends HttpServlet {

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
			default -> {
				response.getWriter().println("<h3>action 파라미터를 확인하세요</h3>");
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// 1. 요청 파라미터 추출
	// 2. 요청 처리: 평균 계산
	// 3. 결과를 바탕으로 응답 생성
	private void average(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		int kor  = Integer.parseInt(request.getParameter("kor"));
		int eng  = Integer.parseInt(request.getParameter("eng"));
		int math = Integer.parseInt(request.getParameter("math"));

		double average = (kor + eng + math) / 3.0;

		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h2>평균 점수</h2>");
		out.println("<p>국어: " + kor  + "점</p>");
		out.println("<p>영어: " + eng  + "점</p>");
		out.println("<p>수학: " + math + "점</p>");
		out.println("<p>평균: " + String.format("%.1f", average) + "점</p>");
		out.println("</body></html>");
	}

	// 1. 요청 파라미터 추출
	// 2. 요청 처리: 평균 계산 후 학점 산출
	// 3. 결과를 바탕으로 응답 생성
	private void grade(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		int kor  = Integer.parseInt(request.getParameter("kor"));
		int eng  = Integer.parseInt(request.getParameter("eng"));
		int math = Integer.parseInt(request.getParameter("math"));

		double average = (kor + eng + math) / 3.0;

		String grade;
		if      (average >= 90) grade = "A";
		else if (average >= 80) grade = "B";
		else if (average >= 70) grade = "C";
		else if (average >= 60) grade = "D";
		else                    grade = "F";

		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h2>학점</h2>");
		out.println("<p>국어: " + kor  + "점</p>");
		out.println("<p>영어: " + eng  + "점</p>");
		out.println("<p>수학: " + math + "점</p>");
		out.println("<p>평균: " + String.format("%.1f", average) + "점</p>");
		out.println("<p>학점: " + grade + "</p>");
		out.println("</body></html>");
	}

	// 1. 요청 파라미터 추출
	// 2. 요청 처리: 평균 계산 후 합불 판정
	// 3. 결과를 바탕으로 응답 생성
	private void pass(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		int kor  = Integer.parseInt(request.getParameter("kor"));
		int eng  = Integer.parseInt(request.getParameter("eng"));
		int math = Integer.parseInt(request.getParameter("math"));

		double average = (kor + eng + math) / 3.0;
		String result  = average >= 60 ? "합격" : "불합격";

		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h2>합불 판정</h2>");
		out.println("<p>국어: " + kor  + "점</p>");
		out.println("<p>영어: " + eng  + "점</p>");
		out.println("<p>수학: " + math + "점</p>");
		out.println("<p>평균: " + String.format("%.1f", average) + "점</p>");
		out.println("<p>결과: " + result + "</p>");
		out.println("</body></html>");
	}
}