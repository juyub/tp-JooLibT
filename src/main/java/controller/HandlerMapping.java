package controller;

import java.util.HashMap;
import java.util.Map;

import board.AddBoardController;
import board.AddReplyController;
import board.DeleteBoardController;
import board.GetBoardController;
import board.GetBoardListController;
import board.SearchBoardController;
import board.UpdateBoardController;
import book.AddBookController;
import book.DeleteBookController;
import book.GetBookController;
import book.GetBookListController;
import book.SearchBookController;
import book.UpdateBookController;
import borrow.BorrowBookController;
import borrow.GetBorrowListController;
import borrow.GetBorrowUserController;
import borrow.ReturnBookController;
import comment.AddCommentController;
import comment.DeleteCommentController;
import comment.UpdateCommentController;
import page.AddUserPageController;
import page.GoUserListPageController;
import page.IndexPageController;
import page.LoginPageController;
import page.MovieDetailPageController;
import page.MovieMainPageController;
import user.AddUserController;
import user.DeleteUserController;
import user.GetUserListController;
import user.GetUserMyController;
import user.GetUserNoController;
import user.LoginController;
import user.LogoutController;
import user.SearchUserController;
import user.UpdateUserController;

public class HandlerMapping {

	private Map<String, Controller> mappings;
	
	public HandlerMapping() {
		mappings = new HashMap<String, Controller>();
		mappings.put("/addUser.do", new AddUserController());
		mappings.put("/login.do", new LoginController());
		mappings.put("/logout.do", new LogoutController());
		mappings.put("/getUserList.do", new GetUserListController());
		mappings.put("/getUserNo.do", new GetUserNoController());
		mappings.put("/updateUser.do", new UpdateUserController());
		mappings.put("/deleteUser.do", new DeleteUserController());
		mappings.put("/getUserMy.do", new GetUserMyController());
		mappings.put("/searchUser.do", new SearchUserController());
		
		mappings.put("/addBook.do", new AddBookController());
		mappings.put("/getBookList.do", new GetBookListController());
		mappings.put("/getBook.do", new GetBookController());
		mappings.put("/searchBook.do", new SearchBookController());
		mappings.put("/deleteBook.do", new DeleteBookController());
		mappings.put("/updateBook.do", new UpdateBookController());
		
		mappings.put("/borrowBook.do", new BorrowBookController());
		mappings.put("/getBorrowList.do", new GetBorrowListController());
		mappings.put("/getBorrowUser.do", new GetBorrowUserController());
		mappings.put("/returnBook.do", new ReturnBookController());
		
		mappings.put("/loginPage.do", new LoginPageController());
		mappings.put("/indexPage.do", new IndexPageController());
		mappings.put("/addUserPage.do", new AddUserPageController());
		mappings.put("/goUserListPage.do", new GoUserListPageController());
		mappings.put("/movieMain.do", new MovieMainPageController());
		mappings.put("/movieDetail.do", new MovieDetailPageController());
//		mappings.put("/goGetBookPage.do", new GoGetBookPageController());
//		mappings.put("/goBorrowListPage.do", new GoBorrowListPageController());
		
		mappings.put("/addBoard.do", new AddBoardController());
		mappings.put("/getBoardList.do", new GetBoardListController());
		mappings.put("/getBoard.do", new GetBoardController());
		mappings.put("/updateBoard.do", new UpdateBoardController());
		mappings.put("/deleteBoard.do", new DeleteBoardController());
		mappings.put("/searchBoard.do", new SearchBoardController());
		
		mappings.put("/addReply.do", new AddReplyController());
		
		mappings.put("/addComment.do", new AddCommentController());
		mappings.put("/updateComment.do", new UpdateCommentController());
		mappings.put("/deleteComment.do", new DeleteCommentController());
		
	}
	
	public Controller getController(String path) {
		return mappings.get(path);
	}
	
}
