package comment;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

public class GetCommentListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

		int boardNO = Integer.parseInt(request.getParameter("boardNO"));

        CommentDAO dao= new CommentDAO();
		List<CommentVO> commentList = dao.getCommentListByBoardNO(boardNO);

        request.setAttribute("commentList", commentList);
        
		return "/board/commentList.jsp";
	}

}
