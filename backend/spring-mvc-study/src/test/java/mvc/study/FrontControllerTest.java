package mvc.study;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * SSA-54 TODO 검증용 자동 채점기
 * 
 * 터미널에서 `mvn test` 명령어를 입력하면, 
 * 내가 작성한 코드가 요구사항을 만족하는지 자동으로 채점해줍니다!
 */
public class FrontControllerTest {

    @Test
    @DisplayName("TODO 7, 8: MemberController가 파라미터를 읽고 뷰 이름을 반환하는지 확인")
    void testMemberController() throws Exception {
        // 1. 가짜(Mock) 객체 생성
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        
        // 2. 가짜 request가 받을 파라미터 셋팅
        when(request.getParameter("username")).thenReturn("ssafy_student");

        // 3. 테스트할 컨트롤러 실행
        Controller controller = new MemberController();
        String viewName = controller.process(request, response);

        // 4. 결과 채점 (assert)
        // TODO 7: request.setAttribute("member", "ssafy_student")가 1번 이상 호출되었는가?
        verify(request, atLeastOnce()).setAttribute("member", "ssafy_student");
        
        // TODO 8: 반환된 논리적 뷰 이름이 "save-result"인가?
        assertEquals("save-result", viewName, "MemberController는 'save-result' 뷰 이름을 반환해야 합니다.");
    }

    @Test
    @DisplayName("TODO 9: BoardController가 뷰 이름을 반환하는지 확인")
    void testBoardController() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        
        Controller controller = new BoardController();
        String viewName = controller.process(request, response);

        assertEquals("board-list", viewName, "BoardController는 'board-list' 뷰 이름을 반환해야 합니다.");
    }
}
