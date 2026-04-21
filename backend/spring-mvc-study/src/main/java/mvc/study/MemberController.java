package mvc.study;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * SSA-54 하위 컨트롤러 실습
 * FrontController로부터 호출을 받아 실제 비즈니스 로직(회원가입 등)만 수행합니다.
 * 포워딩이나 뷰 관련 공통 처리를 생략하고 논리 이름만 넘깁니다.
 */
public class MemberController implements Controller {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MemberController.process() 호출");
        
        // 여기서 파라미터 추출 및 비즈니스 로직 수행
        String username = request.getParameter("username");
        request.setAttribute("member", username);
        
        // 논리적 뷰 이름만 반환 (FrontController가 실제 위치로 포워딩 처리)
        return "save-result";
    }
}
