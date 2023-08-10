package board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comment.CommentDAO;
import comment.CommentVO;
import controller.Controller;
import dao.BoardDAO;
import vo.BoardVO;

public class GetBoardController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		String boardNO = request.getParameter("boardNO");
		
		BoardVO vo = new BoardVO();
		vo.setBoardNO(Integer.parseInt(boardNO));
		
		BoardDAO dao= new BoardDAO();
		BoardVO board = dao.selectBoardNo(vo);
		
        dao.updateHit(board.getBoardNO());
	    
	    request.setAttribute("board",board);
		
		CommentDAO dao1= new CommentDAO();
		List<CommentVO> commentList = dao1.getCommentListByBoardNO(Integer.parseInt(boardNO));

	    request.setAttribute("commentList", commentList);
		
		return "/board/getBoard.jsp";
//	    return "redirect:getBoard.do?boardNO=" + boardNO;
	}

}
