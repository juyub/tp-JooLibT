package user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import dao.UserDAO;
import vo.UserVO;


public class GetUserListController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		UserVO vo = new UserVO();
		UserDAO dao = new UserDAO();
		List<UserVO> userList = dao.getUserList(vo);
				
		request.setAttribute("userList", userList);
		
		return "getUserList.jsp";
	}
	
	

}
