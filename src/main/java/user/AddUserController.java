package user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import dao.UserDAO;
import vo.UserVO;

public class AddUserController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		
		UserVO vo = new UserVO();
		vo.setUserid(id);
		vo.setPassword(password);
		vo.setName(name);
		vo.setPhone(phone);
		
		UserDAO dao = new UserDAO();
		dao.addUser(vo);
		
		return "index.jsp";
	}

}
