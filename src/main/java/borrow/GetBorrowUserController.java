package borrow;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import dao.BorrowDAO;
import vo.BorrowVO;
import vo.UserVO;

public class GetBorrowUserController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("login");
		
//		String userno = request.getParameter("userno");
		
		BorrowVO vo = new BorrowVO();
		vo.setUserno(user.getUserno());
//		vo.setUserno(Integer.parseInt(userno));
		BorrowDAO dao = new BorrowDAO();
		List<BorrowVO> borrowUser = dao.getBorrowUserNo(vo);
				
		session.setAttribute("borrowUser", borrowUser);
		
		return "getBorrowUser.jsp";
	}

}
