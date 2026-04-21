package com.ssafy.live.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ControllerHelper {
	default String getActionParameter(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// action 파라미터를 추출하여 반환
		String action = request.getParameter("action");
		if (action == null || action.isBlank()) {
			action = "index";
		}
		System.out.println("action: " + action);
		return action;
	}

	// TODO: 01. redirect와 forward를 처리할 수 있는 utility method로 redirect, forward를
	// 작성하세요.
	// http로 시작하면 context를 개입시키지 않고 그렇지 않으면 개입시킨다.
	public default void redirect(HttpServletRequest request, HttpServletResponse response, String path)
			throws IOException {
		if (path.startsWith("http")) {
			response.sendRedirect(path);
		} else {
			response.sendRedirect(request.getContextPath() + path);
		}
	}

	public default void forward(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		RequestDispatcher disp = request.getRequestDispatcher(path);
		disp.forward(request, response);
	}

	// END
}
