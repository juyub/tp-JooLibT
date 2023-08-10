package board;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import dao.BoardDAO;
import vo.BoardVO;
import vo.UserVO;

public class AddBoardController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		UserVO login = (UserVO) session.getAttribute("login"); // 세션에서 user 객체를 
		String id = login.getUserid(); // writer를 ID로 
		
		String title = request.getParameter("title");
//		String memberNO = request.getParameter("memberNO");
		String content = request.getParameter("content");

		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setUserid(id);
		vo.setContent(content);
		
		BoardDAO dao = new BoardDAO();
		dao.addBoard(vo);
	
		return "getBoardList.do";
	}

}
