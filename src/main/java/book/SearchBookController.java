package book;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import controller.Controller;
import dao.BookDAO;
import vo.BookVO;

public class SearchBookController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

		String searchBy = request.getParameter("searchBy");
		String search = request.getParameter("search");

		if (search == null) {
			return "getBookListPage.do";
		}

		BookVO vo = new BookVO();
		BookDAO dao = new BookDAO();
		List<BookVO> bookList = null;
		
		if (searchBy.equals("title")) {
			vo.setTitle(search);
			bookList = dao.searchBookTitle(vo);
		} else if (searchBy.equals("author")) {
			vo.setAuthor(search);
			bookList = dao.searchBookAuthor(vo);
		} 

		request.setAttribute("bookList", bookList);

		return "getBookList.jsp";
	}
}
