package user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import dao.BorrowDAO;
import dao.UserDAO;
import vo.BorrowVO;
import vo.UserVO;

public class DeleteUserController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("login");
		String userno = request.getParameter("userno");
		
		UserVO vo = new UserVO();
		vo.setUserno(Integer.parseInt(userno));
//		vo.setUserno(user.getUserno());
		UserDAO dao = new UserDAO();

		BorrowVO vo1 = new BorrowVO();
		vo1.setUserno(Integer.parseInt(userno));
//		vo1.setUserno(user.getUserno());
		BorrowDAO dao1 = new BorrowDAO();
		List<BorrowVO> borrowUser = dao1.getBorrowingUser(vo1);

		if (!borrowUser.isEmpty()) {
			request.setAttribute("deleteFailed", true);
			return "getUserNo.do";
		} else {
			if (user.getRole().equals("admin")) {
				dao.deleteUser(vo);
				return "getUserList.do";
			} else if (user.getRole().equals("user")) {
				dao.deleteUser(vo);
				session.invalidate();
			}
		}

		return "indexPage.do";

	}

}
