package comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

public class DeleteCommentController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

		String boardNO = request.getParameter("boardNO");
		String commentNO = request.getParameter("commentNO");
		
		CommentVO vo = new CommentVO();
		vo.setCommentNO(Integer.parseInt(commentNO));
		
		CommentDAO dao = new CommentDAO();
		dao.deleteComment(vo);
		
//		return "/board/getBoard.jsp";
		return "getBoard.do?boardNO="+ boardNO;
//		return "redirect:getBoard.do?boardNO="+boardNO;
	}

}
