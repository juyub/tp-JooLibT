package user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import dao.UserDAO;
import vo.UserVO;

public class SearchUserController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

		String searchBy = request.getParameter("searchBy");
		String search = request.getParameter("search");

		if (search == null) {
			return "getUserListPage.do";
		}

		UserVO vo = new UserVO();
		UserDAO dao = new UserDAO();
		List<UserVO> userList = null;
		
		if (searchBy.equals("name")) {
			vo.setName(search);
			userList = dao.searchUserName(vo);
		} else if (searchBy.equals("phone")) {
			vo.setPhone(search);
			userList = dao.searchUserPhone(vo);
		} 

		request.setAttribute("userList", userList);
		
		
		return "goUserListPage.do";
	}
	
}
