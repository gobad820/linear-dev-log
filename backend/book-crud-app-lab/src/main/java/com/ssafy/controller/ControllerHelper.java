package com.ssafy.controller;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ControllerHelper {

    default String getActionParameter(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        if (action == null || action.isBlank()) {
            action = "index";
        }
        return action;
    }

    default void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
        // TODO: 09. 클라이언트를 contextPath + path 로 리다이렉트한다.
        response.sendRedirect(request.getContextPath() + path);
    }

    default void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        // TODO: 10. RequestDispatcher를 사용해 path로 포워드한다.
        request.getRequestDispatcher(path).forward(request, response);
    }
}
