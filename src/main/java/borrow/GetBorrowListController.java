package borrow;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import dao.BorrowDAO;
import vo.BorrowVO;

public class GetBorrowListController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

		BorrowVO vo = new BorrowVO();
		BorrowDAO dao = new BorrowDAO();
		List<BorrowVO> borrowList = dao.getBorrowList(vo);
				
		request.setAttribute("borrowList", borrowList);
		
		return "getBorrowList.jsp";
	}
	
}
