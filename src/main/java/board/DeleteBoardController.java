package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.CommentDAO;
import controller.Controller;
import dao.BoardDAO;

public class DeleteBoardController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		String boardNO = request.getParameter("boardNO");
		
		CommentDAO dao1= new CommentDAO();
		dao1.deleteComment(Integer.parseInt(boardNO));
		
		BoardDAO dao = new BoardDAO();
		dao.deleteBoard(Integer.parseInt(boardNO));
		
//		return "/board/boardList.jsp";
		return "redirect:getBoardList.do";
	}

}
