package user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import dao.UserDAO;
import vo.UserVO;

public class GetUserMyController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("login");
		
//		String userno = request.getParameter("userno");
		
		UserVO vo = new UserVO();
		vo.setUserno(user.getUserno());
//		vo.setUserno(Integer.parseInt(userno));
		
		UserDAO dao = new UserDAO();
		UserVO user1 = dao.getUserNo(vo);
		
		session.setAttribute("user", user1);
		request.setAttribute("user", user1);
		
		return "getUserMy.jsp";
	}

	
	
}
