package com.ssafy.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ssafy.model.service.TempService;
import com.ssafy.model.service.TempServiceImpl;

/**
 * Servlet implementation class TempController
 */
@WebServlet("/temp")
public class TempController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	TempService tempService = new TempServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TempController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if (action == null || action.isBlank()) {
			action = "";
		}
		response.setContentType("text/html;charset=UTF-8");

		switch (action) {
		case "toFahrenheit" -> convertToFahrenheit(request, response);
		case "toCelsius" -> convertToCelsius(request, response);
		case "toKelvin" -> convertToKelvin(request, response);
		default -> response.getWriter().println("action 파라미터를 확인하세요");
		}
	}

	private void convertToKelvin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		double celsius = Double.parseDouble(request.getParameter("celsius"));
		double kelvin = tempService.toKelvin(celsius);
		request.setAttribute("celsius", celsius);
		request.setAttribute("kelvin", kelvin);
		request.getRequestDispatcher("/toKelvin.jsp").forward(request, response);
	}

	private void convertToCelsius(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		double fahrenheit = Double.parseDouble(request.getParameter("fahrenheit"));
		double celsius = tempService.toCelsius(fahrenheit);
		request.setAttribute("fahrenheit", fahrenheit);
		request.setAttribute("celsius", celsius);
		request.getRequestDispatcher("/toCelsius.jsp").forward(request, response);
	}

	private void convertToFahrenheit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		double celsius = Double.parseDouble(request.getParameter("celsius"));
		double fahrenheit = tempService.toFahrenheit(celsius);
		request.setAttribute("celsius", celsius);
		request.setAttribute("fahrenheit", fahrenheit);
		request.getRequestDispatcher("/toFahrenheit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
