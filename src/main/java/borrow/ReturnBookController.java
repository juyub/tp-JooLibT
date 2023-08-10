package borrow;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import dao.BorrowDAO;
import vo.BookVO;
import vo.BorrowVO;
import vo.UserVO;

public class ReturnBookController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		int borrowno = Integer.parseInt(request.getParameter("borrowno"));
		int bookno = Integer.parseInt(request.getParameter("bookno"));
		int userno = Integer.parseInt(request.getParameter("userno"));

		BorrowVO vo = new BorrowVO();
        vo.setBookno(bookno);
        vo.setUserno(userno);
        vo.setBorrowno(borrowno);

        BorrowDAO dao = new BorrowDAO();
        dao.returnBook(vo);

		return "getBorrowList.do";
	}

}
