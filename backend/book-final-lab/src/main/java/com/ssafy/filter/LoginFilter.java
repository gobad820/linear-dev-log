package com.ssafy.filter;

import java.io.IOException;
import java.net.URLEncoder;

import com.ssafy.model.dto.Member;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/books")
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
        // TODO: 09. 필터 처리 로직을 구현한다. //   힌트:
        //   - response.sendRedirect(url)로 redirect한다.
        //   - request.getSession()으로 세션을 가져온다.
        //   - getAttribute()로 속성을 읽는다.
        //   - setAttribute()로 속성을 설정한다.

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String action = req.getParameter("action");

		if(action.equals("list") || action.equals("detail")){
			chain.doFilter(req,res);
			return;
		}


		HttpSession session = req.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		if(loginMember == null){
			session.setAttribute("alertMsg", "로그인이 필요합니다.");
			res.sendRedirect(req.getContextPath() + "/");
			return;
		}

		chain.doFilter(req,res);
	}



}