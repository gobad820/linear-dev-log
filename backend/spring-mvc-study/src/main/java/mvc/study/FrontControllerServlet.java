package mvc.study;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * SSA-54 학습 실습 (FrontController 구현)
 * 공통 진입점: 앱의 모든 "/front/*" 요청을 이 서블릿이 먼저 가로챕니다.
 * Spring의 DispatcherServlet과 동일한 역할을 수행합니다.
 *
 * <완료 기준 만족 여부 확인>
 * - Front Controller 패턴의 필요성 이해: 모든 요청의 공통 처리(예: 인코딩, 포워딩, 로깅)를 여기서 한 번에 해결.
 * - DispatcherServlet 구조와 매핑: Map 기반 핸들러 매핑 체계가 실제 Spring의 HandlerMapping을 흉내낸 것임을 인지.
 */
@WebServlet(name = "frontControllerServlet", urlPatterns = "/front/*")
public class FrontControllerServlet extends HttpServlet {

    private Map<String, Controller> controllerMap = new HashMap<>();

    public FrontControllerServlet() {
        // 실제 Spring에서는 HandlerMapping이 수행하는 URL -> Controller 연결 작업입니다.
        controllerMap.put("/front/member/save", new MemberController());
        controllerMap.put("/front/board/list", new BoardController());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServlet.service()");

        // 1. 요청 URI 분석
        String requestURI = request.getRequestURI();

        // 2. 적절한 핸들러(Controller) 찾기 (HandlerMapping 역할)
        Controller controller = controllerMap.get(requestURI);
        
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 3. 실제 컨트롤러 로직 실행 (HandlerAdapter 역할 위임)
        String viewName = controller.process(request, response);

        // 4. View 전환 처리 (ViewResolver 역할 시뮬레이션)
        if (viewName != null) {
            // "redirect:" 접두사 처리 등 공통 로직을 한 곳에서 몰아서 처리
            if (viewName.startsWith("redirect:")) {
                response.sendRedirect(viewName.substring("redirect:".length()));
            } else {
                String viewPath = "/WEB-INF/views/" + viewName + ".jsp";
                request.getRequestDispatcher(viewPath).forward(request, response);
            }
        }
    }
}
