package mvc.study;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * SSA-54 하위 컨트롤러 실습
 * FrontController로부터 호출을 받아 실제 비즈니스 로직(회원가입 등)만 수행합니다.
 * 포워딩이나 뷰 관련 공통 처리는 FrontController에 위임하고 논리적 뷰 이름만 넘깁니다.
 */
public class MemberController implements Controller {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MemberController.process() 호출");
        
        // TODO 7: request에서 클라이언트가 전달한 "username" 파라미터를 읽어오세요.
        // 그리고 request의 속성(Attribute) 이름 "member"에 추출한 값을 저장하세요.
        
        
        // TODO 8: 처리가 완료된 후 뷰 리졸버가 처리할 수 있도록, 
        // 논리적 뷰 이름인 "save-result"를 반환하도록 수정하세요.
        return null; // 이 부분을 수정하세요.
    }
}
