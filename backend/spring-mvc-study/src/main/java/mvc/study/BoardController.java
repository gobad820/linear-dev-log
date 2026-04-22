package mvc.study;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * SSA-54 하위 컨트롤러 실습
 * Board 관련 요청 수행을 담당하는 컨트롤러입니다.
 */
public class BoardController implements Controller {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("BoardController.process() 호출");
        
        // 게시판 조회 등 로직 수행 가정
        request.setAttribute("boardList", "게시글 목록 데이터 (데이터베이스 연동 가정)");
        
        // TODO 9: 게시판 목록 화면을 출력할 수 있도록, 
        // 뷰의 논리적 이름인 "board-list"를 반환하게 수정하세요.
        return "board-list"; // 이 부분을 수정하세요.
    }
}
