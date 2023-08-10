package board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import dao.BoardDAO;
import vo.BoardVO;

public class SearchBoardController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

		int pageNo;
		String pageNoParam = request.getParameter("pageNo");
		if (pageNoParam != null) {
		    pageNo = Integer.parseInt(pageNoParam);
		} else {
		    pageNo = 1; // 기본값으로 1을 설정
		}
		
	    int pageSize = 10;
		
		String search = request.getParameter("search");
		
		BoardDAO dao = new BoardDAO();
		List<BoardVO> searchList = dao.searchBoard(search, pageNo, pageSize);
		
		request.setAttribute("searchList", searchList);
		
		int totalBoardCount = dao.selectBoardCount(); // 게시글의 총 개수를 가져옴

        // 페이징 처리를 위한 정보 계산
        int totalPageCount = (int) Math.ceil((double) totalBoardCount / pageSize);
        int startPage = (int) Math.floor((double) (pageNo - 1) / 10) * 10 + 1;
        int endPage = Math.min(startPage + 9, totalPageCount);

        request.setAttribute("totalPageCount", totalPageCount);
        request.setAttribute("startPage", startPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("currentPage", pageNo);
		
		return "/board/searchList.jsp";
	}

}
