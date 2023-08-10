package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import dao.BoardDAO;
import vo.BoardVO;
import vo.UserVO;

public class AddReplyController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
       
		// 원 게시물의 boardNO와 작성자 정보
        int boardNO = Integer.parseInt(request.getParameter("boardNO"));
        UserVO login = (UserVO) request.getSession().getAttribute("login");
        String id = login.getUserid();

        // 답글 제목과 내용
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        // BoardVO 객체에 답글 정보를 설정
        BoardVO vo = new BoardVO();
        vo.setParentNO(boardNO);
        vo.setTitle(title);
        vo.setContent(content);
        vo.setUserid(id);

        // BoardDAO를 사용하여 답글을 추가
        BoardDAO dao = new BoardDAO();
        dao.addReply(vo);

        return "getBoardList.do";
	}

}
