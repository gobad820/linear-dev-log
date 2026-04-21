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
 * <학습 목표>
 * 프론트 컨트롤러 패턴을 직접 구현해보며, 
 * URL 매핑, 요청 위임, 뷰 처리가 한 곳에서 이루어지는 구조를 이해합니다.
 */
@WebServlet(name = "frontControllerServlet", urlPatterns = "/front/*")
public class FrontControllerServlet extends HttpServlet {

    private Map<String, Controller> controllerMap = new HashMap<>();

    public FrontControllerServlet() {
        // TODO 2: controllerMap에 URL 경로와 그에 대응하는 하위 컨트롤러 객체를 등록하세요.
        // 예시: "/front/member/save" -> MemberController
        // 예시: "/front/board/list" -> BoardController
        
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServlet.service()");

        // 1. 요청 URI 분석
        String requestURI = request.getRequestURI();

        // 2. 적절한 핸들러(Controller) 찾기 (HandlerMapping 역할)
        // TODO 3: 등록해둔 controllerMap에서 현재 요청 URI에 맞는 Controller를 찾아 꺼내세요.
        Controller controller = null; // 이 부분을 수정하세요.
        
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 3. 실제 컨트롤러 로직 실행 (HandlerAdapter 역할 위임)
        // TODO 4: 찾은 controller의 process() 메서드를 호출하고, 반환된 논리적 뷰 이름을 변수에 저장하세요.
        String viewName = null; // 이 부분을 수정하세요.

        // 4. View 전환 처리 (ViewResolver 역할 시뮬레이션)
        if (viewName != null) {
            // "redirect:" 접두사 처리 등 공통 로직을 한 곳에서 몰아서 처리
            if (viewName.startsWith("redirect:")) {
                response.sendRedirect(viewName.substring("redirect:".length()));
            } else {
                // TODO 5: 반환받은 논리적 뷰 이름(viewName)을 활용하여 실제 JSP가 있는 물리적 경로를 완성하세요.
                // 물리적 경로는 "/WEB-INF/views/[논리적뷰이름].jsp" 형식입니다.
                String viewPath = ""; // 이 부분을 수정하세요.
                
                // TODO 6: 완성된 물리적 경로를 사용하여 forward를 수행하세요.
                // 예: request.getRequestDispatcher(경로).forward(request, response);
                
            }
        }
    }
}
