package com.ssafy.live.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class XSSFilter
 */
@WebFilter("/*")
public class XSSFilter extends HttpFilter implements Filter {

    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(new XssRequestWrapper(request), response);
    }

    class XssRequestWrapper extends HttpServletRequestWrapper {

        public XssRequestWrapper(HttpServletRequest request) {
            super(request);
        }

        @Override
        public String getParameter(String name) {
            return cleanXss(super.getParameter(name));
        }

        private String cleanXss(String value) {
            if (value == null) {
                return null;
            }
            return value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        }
    }
}
