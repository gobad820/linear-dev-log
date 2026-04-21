package mvc.study;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * SSA-54 하위 컨트롤러 실습
 * Board 관련 로직 수행
 */
public class BoardController implements Controller {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("BoardController.process() 호출");
        
        // 게시판 조회 로직 가정
        request.setAttribute("boardList", "게시글 목록 데이터");
        
        return "board-list";
    }
}
