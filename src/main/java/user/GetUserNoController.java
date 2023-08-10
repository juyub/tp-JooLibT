package user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import dao.UserDAO;
import vo.UserVO;

public class GetUserNoController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

//		HttpSession session = request.getSession();
//		UserVO user = (UserVO) session.getAttribute("user");
		
		String userno = request.getParameter("userno");
		
		UserVO vo = new UserVO();
//		vo.setUserno(user.getUserno());
		vo.setUserno(Integer.parseInt(userno));
		
		UserDAO dao = new UserDAO();
		UserVO user = dao.getUserNo(vo);
		
		request.setAttribute("user", user);
		
		return "getUserNo.jsp";
	}

	
}
