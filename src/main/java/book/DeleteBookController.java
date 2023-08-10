package book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import dao.BookDAO;
import vo.BookVO;

public class DeleteBookController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

		String bookno = request.getParameter("bookno");
		
		BookVO vo = new BookVO();
		vo.setBookno(Integer.parseInt(bookno));		
		
		BookDAO dao = new BookDAO();
		
		try {
            dao.deleteBook(vo);
            request.setAttribute("message", "도서 정보가 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            request.setAttribute("message", e.getMessage());
        }
						
		return "deleteBookResult.jsp";
		
//		dao.deleteBook(vo);
		
//		return "getBookList.do";
	}

}
