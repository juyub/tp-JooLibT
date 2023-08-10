package borrow;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import dao.BorrowDAO;
import vo.BookVO;
import vo.BorrowVO;
import vo.UserVO;

public class BorrowBookController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

		String bookno = request.getParameter("bookno");
		String userno = request.getParameter("userno");
		
		BorrowVO vo = new BorrowVO();
		vo.setBookno(Integer.parseInt(bookno));
		vo.setUserno(Integer.parseInt(userno));
		
		BorrowDAO dao = new BorrowDAO();
		dao.borrowBook(vo);
		
		return "getBook.do";
	}
	
}
