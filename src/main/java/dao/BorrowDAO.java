package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.JDBCUtil;
import vo.BookVO;
import vo.BorrowVO;
import vo.UserVO;

public class BorrowDAO {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	// 대출 메서드
	public void borrowBook(BorrowVO vo) {
		insertBorrowInfo(vo);
		updateBookAvailablen(vo);
		updateUserBorrown(vo);
	}

	// 대출 정보를 추가하는 메서드
	private void insertBorrowInfo(BorrowVO vo) {
		String query = "INSERT INTO borrows (userno, bookno, borrowdate, duedate) "
				+ "VALUES (?, ?, SYSDATE, SYSDATE + 7)";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {

			pstmt.setInt(1, vo.getUserno());
			pstmt.setInt(2, vo.getBookno());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 도서 재고를 -1 메서드
	private void updateBookAvailablen(BorrowVO vo) {
		String query = "UPDATE books SET availablen = availablen - 1 WHERE bookno = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {

			pstmt.setInt(1, vo.getBookno());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 대출 가능 권수 -1 메서드
	private void updateUserBorrown(BorrowVO vo) {
		String query = "UPDATE users SET borrown = borrown - 1 WHERE userno = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {

			pstmt.setInt(1, vo.getUserno());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 대출 정보 전체 조회 메서드 - title, username 출력
	public List<BorrowVO> getBorrowList(BorrowVO vo) {
		List<BorrowVO> borrowList = new ArrayList<BorrowVO>();
		String query = 
				  " SELECT b.borrowno, u.userid AS username, bo.title, b.borrowdate, b.duedate, b.returndate,b.userno,b.bookno "
				+ " FROM borrows b "
				+ " JOIN users u ON b.userno = u.userno "
				+ " JOIN books bo ON b.bookno = bo.bookno "
				+ " order by borrowdate desc ";

		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				BorrowVO borrow = new BorrowVO();
				borrow.setBorrowno(rs.getInt("borrowno"));
				borrow.setUserno(rs.getInt("userno"));
				borrow.setBookno(rs.getInt("bookno"));
				borrow.setUsername(rs.getString("username"));
				borrow.setBooktitle(rs.getString("title"));
				borrow.setBorrowdate(rs.getDate("borrowdate"));
				borrow.setDuedate(rs.getDate("duedate"));
				borrow.setReturndate(rs.getDate("returndate"));
				borrowList.add(borrow);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return borrowList;
	}

	// 대출 정보 조회 메서드 - bookno, userno 출력
	public BorrowVO getBorrow(BorrowVO vo) {
		BorrowVO borrow = null;

		String query = " SELECT * from borrows where userno = ? and bookno = ? ";

		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, vo.getUserno());
			stmt.setInt(2, vo.getBookno());

			rs = stmt.executeQuery();
			while (rs.next()) {
				borrow = new BorrowVO();
				borrow.setBorrowno(rs.getInt("borrowno"));
				borrow.setUserno(rs.getInt("userno"));
				borrow.setBookno(rs.getInt("bookno"));
				borrow.setBorrowdate(rs.getDate("borrowdate"));
				borrow.setDuedate(rs.getDate("duedate"));
				borrow.setReturndate(rs.getDate("returndate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return borrow;
	}

	// 대출 정보 조회 - userno로 조회, 도서명 출력
	public List<BorrowVO> getBorrowUserNo(BorrowVO vo) {
		List<BorrowVO> borrowUser = new ArrayList<BorrowVO>();
		String query = " SELECT borrows.borrowno, borrows.userno, "
				     +        " books.title AS book_title, borrows.borrowdate, borrows.duedate, borrows.returndate "
				     + " FROM borrows "
				     + " JOIN books ON borrows.bookno = books.bookno "
				     + " WHERE borrows.userno = ? "
				     + " order by returndate desc " ;

		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, vo.getUserno()); // Set the userNo parameter
			rs = stmt.executeQuery();

			while (rs.next()) {
				BorrowVO borrow = new BorrowVO();
				borrow.setBorrowno(rs.getInt("borrowno"));
				borrow.setUserno(rs.getInt("userno"));
				borrow.setBooktitle(rs.getString("book_title"));
				borrow.setBorrowdate(rs.getDate("borrowdate"));
				borrow.setDuedate(rs.getDate("duedate"));
				borrow.setReturndate(rs.getDate("returndate"));
				borrowUser.add(borrow);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}

		return borrowUser;
	}
	
	// 대출 정보 조회 - userno로 조회, 미반납
	public List<BorrowVO> getBorrowingUser(BorrowVO vo) {
		List<BorrowVO> borrowUser = new ArrayList<BorrowVO>();
		String query = 
				  " SELECT * FROM borrows "
				+ " WHERE userno = ? " ;

		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, vo.getUserno()); // Set the userNo parameter
			rs = stmt.executeQuery();

			while (rs.next()) {
				BorrowVO borrow = new BorrowVO();
				borrow.setBorrowno(rs.getInt("borrowno"));
				borrow.setUserno(rs.getInt("userno"));
				borrow.setBookno(rs.getInt("bookno"));
				borrow.setBorrowdate(rs.getDate("borrowdate"));
				borrow.setDuedate(rs.getDate("duedate"));
				borrow.setReturndate(rs.getDate("returndate"));
				borrowUser.add(borrow);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}

		return borrowUser;
	}

	// 대출 정보 수정 메서드

	// 대출 정보 삭제 메서드

	// 반납 메서드
	public void returnBook(BorrowVO vo) {
		deleteBorrowInfo(vo);
		addBookAvailablen(vo);
		addUserBorrown(vo);
	}

	// 대출 정보를 삭제하는 메서드
	private void deleteBorrowInfo(BorrowVO vo) {
		String query = " delete borrows WHERE borrowno = ? ";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {

			pstmt.setInt(1, vo.getBorrowno());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 도서 재고를 +1 메서드
	private void addBookAvailablen(BorrowVO vo) {
		String query = "UPDATE books SET availablen = availablen + 1 WHERE bookno = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {

			pstmt.setInt(1, vo.getBookno());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 대출 가능 권수 +1 메서드
	private void addUserBorrown(BorrowVO vo) {
		String query = "UPDATE users SET borrown = borrown + 1 WHERE userno = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {

			pstmt.setInt(1, vo.getUserno());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
