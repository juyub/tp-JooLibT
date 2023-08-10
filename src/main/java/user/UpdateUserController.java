package user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import dao.UserDAO;
import vo.UserVO;

public class UpdateUserController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		String userno = request.getParameter("userno");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String borrown = request.getParameter("borrown");
		String role = request.getParameter("role");
		
		UserVO vo = new UserVO();
		vo.setUserno(Integer.parseInt(userno));
		vo.setPassword(password);
		vo.setPhone(phone);
		vo.setBorrown(Integer.parseInt(borrown));
		vo.setRole(role);
		
		UserDAO dao = new UserDAO();
		dao.updateUser(vo);
		
		return "getUserNo.do";
		
	}
}
