package book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import dao.BookDAO;
import vo.BookVO;

public class UpdateBookController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		String bookno = request.getParameter("bookno");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String publisher = request.getParameter("publisher");
		String publicationyear = request.getParameter("publicationyear");
		String isbn = request.getParameter("isbn");
		String category = request.getParameter("category");
		String totaln = request.getParameter("totaln");
		String availablen = request.getParameter("availablen");
		
		BookVO vo = new BookVO();
		vo.setBookno(Integer.parseInt(bookno));
		vo.setTitle(title);
		vo.setAuthor(author);
		vo.setPublisher(publisher);
		vo.setPublicationyear(Integer.parseInt(publicationyear));
		vo.setIsbn(isbn);
		vo.setCategory(category);
		vo.setTotaln(Integer.parseInt(totaln));
		vo.setAvailablen(Integer.parseInt(availablen));
		
		BookDAO dao = new BookDAO();
		dao.updateBook(vo);
		
		return "getBook.do";
	}
	
}
