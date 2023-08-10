package comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;

public class AddCommentController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		int boardNO = Integer.parseInt(request.getParameter("boardNO"));
        String content = request.getParameter("content");
        String id = request.getParameter("id");
		
//      HttpSession session = request.getSession(); MemberVO login = (MemberVO)
//      session.getAttribute("login");
//		int memberNO = login.getMemberNO();

        CommentVO vo = new CommentVO();       
        vo.setBoardNO(boardNO);
        vo.setContent(content);
        vo.setUserid(id);
        
        CommentDAO dao = new CommentDAO();
        dao.addComment(vo);

//        return "/board/getBoard.jsp";
        return "getBoard.do?boardNO="+ boardNO;
//        return "redirect:getBoard.do?boardNO=" + boardNO;
	}

}
