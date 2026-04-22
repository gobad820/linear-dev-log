package mvc.study;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * SSA-54 학습 계획 (인터페이스 설계)
 * 모든 하위 컨트롤러가 반드시 구현해야 할 규격을 정의합니다.
 * Spring MVC의 Handler 구조와 매핑되는 개념입니다.
 */
public interface Controller {
    // TODO 1: 요청을 처리하고, View(JSP 등)의 논리적 경로(String)를 반환하는 process 메서드를 선언하세요.
    // 매개변수로는 HttpServletRequest와 HttpServletResponse를 받습니다.
    String process(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException;
    
}
