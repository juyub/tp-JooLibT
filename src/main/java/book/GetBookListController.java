package book;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import controller.Controller;
import dao.BookDAO;
import vo.BookVO;

public class GetBookListController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

		BookVO vo = new BookVO();
		BookDAO dao = new BookDAO();
		List<BookVO> bookList = dao.getBookList(vo);
				
		request.setAttribute("bookList", bookList);
		
		return "getBookList.jsp";
	}
	
}
