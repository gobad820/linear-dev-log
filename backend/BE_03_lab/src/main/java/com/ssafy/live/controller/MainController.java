package com.ssafy.live.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import com.ssafy.live.model.service.BasicSimpleService;
import com.ssafy.live.model.service.SimpleService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/main")
public class MainController extends HttpServlet implements ControllerHelper {

    private final SimpleService sService = BasicSimpleService.getService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = getActionParameter(request, response);
        switch (action) {
        // TODO: 02. index 요청을 index.jsp로 이동하자. forward vs redirect 차이는?
        // case "index"-> forward(request, response, "/index.jsp");
        case "index" -> redirect(request, response, "/index.jsp");

        // END
        // TODO: 06. gugu-form 요청을 받으면 "/gugu/gugu-form.jsp"를 연결하세요.
        case "gugu-form" -> forward(request, response, "/gugu/gugu-form.jsp");
        // END
        // TODO: 08. gugu 요청을 받으면 동작할 gugu 메서드 호출하세요.
        case "gugu" -> gugu(request, response);
        // END
        // 오류 처리 방식: 잘못된 action 요청이 들어오면 404 에러를 발생
        default -> response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404 처리
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = getActionParameter(req, resp);
        switch (action) {
            case "gugu" -> gugu(req, resp);
        }
    }

    private void gugu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // TODO: 09. 다음 요청 사항을 만족시키세요.
            // 01. 파라터 추출 - 추출 오류 시 예외 처리 - TODO_11에서 처리
            int dan = Integer.parseInt(request.getParameter("dan"));
            // 02. 비지니스 로직 전문가인 SimpleService의 getGugu()를 호출해서 구 구단을 가져오세요.
            List<String> result = sService.getGugu(dan);
            // 03. 결과를 jsp에게 전달하기 위해 필요한 request 스코프에 저장하세요.
            request.setAttribute("result", result);
            // 04. /gugu/gugu-result.jsp를 호출해서 결과를 표현하세요.(forward vs redirect 차이는?)
            // request.setAttribute를 사용했으므로 반드시 forward를 사용해야 합니다.
            forward(request, response, "/gugu/gugu-result.jsp");

            // END
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // TODO: 11. NumberFormatException 발생 시 로깅하고 alertMsg attribute를 request에 추가해서
            // 다시 /gugu/gugu-form.jsp를 호출하세요.
            request.setAttribute("alertMsg", "숫자를 입력하세요.");
            forward(request, response, "/gugu/gugu-form.jsp");
            // END
        } catch (RuntimeException e) {
            e.printStackTrace();
            // TODO: 13. RuntimeException 발생 시 logging 하고 구글에
            // 문의(https://www.google.com/search?q=)하자. (한글은 URL 인코딩 필요)
//            String url = "https://www.google.com/search?q=" + URLEncoder.encode(e.getMessage(), "UTF-8");
            String query = URLEncoder.encode("구구단 " +request.getParameter("dan"),"UTF-8");
//            redirect(request, response, url);
            redirect(request, response, "http://www.google.com/search?q="+query);
            // END
        }
    }
}
