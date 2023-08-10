package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.JDBCUtil;
import vo.BookVO;
import vo.UserVO;


public class BookDAO {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

    // 도서 정보 추가 메서드
	private static String BOOK_ADD =
			" insert into books " +
		    " (title, author, publisher, publicationyear, isbn, category, totaln, availablen) " +
			" values(?,?,?,?,?,?,?,?) ";
	
	public void addBook(BookVO vo) {
	    try {
	        conn = JDBCUtil.getConnection();
	        stmt = conn.prepareStatement(BOOK_ADD);

	        stmt.setString(1, vo.getTitle());
	        stmt.setString(2, vo.getAuthor());
	        stmt.setString(3, vo.getPublisher());
	        stmt.setInt(4, vo.getPublicationyear());
	        stmt.setString(5, vo.getIsbn());
	        stmt.setString(6, vo.getCategory());
	        stmt.setInt(7, vo.getTotaln());
	        stmt.setInt(8, vo.getAvailablen());

	        stmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        JDBCUtil.close(stmt, conn);
	    }
	}
	
    // 전체 도서 조회 메서드
	private static String BOOK_LIST =
			" select * from books order by bookno desc";
	
	public List<BookVO> getBookList(BookVO vo){
		List<BookVO> bookList = new ArrayList<BookVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOOK_LIST);
			rs = stmt.executeQuery();
			while(rs.next()) {
				BookVO book = new BookVO();
				book.setBookno(rs.getInt("bookno"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
				book.setPublicationyear(rs.getInt("publicationyear"));
				book.setIsbn(rs.getString("isbn"));
				book.setCategory(rs.getString("category"));
				book.setTotaln(rs.getInt("totaln"));
				book.setAvailablen(rs.getInt("availablen"));
				bookList.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return bookList;
	}

	// 도서 상세 보기 메서드
	private static String BOOK_GET =
			" select * from books where bookno = ? ";
	
	public BookVO getBook(BookVO vo) {
		BookVO book = null; 
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOOK_GET);
			stmt.setInt(1, vo.getBookno());
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				book = new BookVO();
				book.setBookno(rs.getInt("bookno"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
				book.setPublicationyear(rs.getInt("publicationyear"));
				book.setIsbn(rs.getString("isbn"));
				book.setCategory(rs.getString("category"));
				book.setTotaln(rs.getInt("totaln"));
				book.setAvailablen(rs.getInt("availablen"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return book;
	}
	
	// 도서 검색 - 제목
	private static String BOOK_SEARCH_TITLE =
			" select * from books " +
			" WHERE title LIKE '%' || ? || '%' " +
			" ORDER BY bookno DESC ";	
	
	public List<BookVO> searchBookTitle(BookVO vo) {
		List<BookVO> bookList = new ArrayList<BookVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOOK_SEARCH_TITLE);
			stmt.setString(1, vo.getTitle());
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				BookVO book = new BookVO();
				book.setBookno(rs.getInt("bookno"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
				book.setPublicationyear(rs.getInt("publicationyear"));
				book.setIsbn(rs.getString("isbn"));
				book.setCategory(rs.getString("category"));
				book.setTotaln(rs.getInt("totaln"));
				book.setAvailablen(rs.getInt("availablen"));
				bookList.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return bookList;
	}
	
	// 도서 검색 - 저자
	private static String BOOK_SEARCH_AUTHOR =
			" select * from books " +
			" WHERE author LIKE '%' || ? || '%' " +
			" ORDER BY bookno DESC ";	
	
	public List<BookVO> searchBookAuthor(BookVO vo) {
		List<BookVO> bookList = new ArrayList<BookVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOOK_SEARCH_AUTHOR);
			stmt.setString(1, vo.getAuthor());
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				BookVO book = new BookVO();
				book.setBookno(rs.getInt("bookno"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
				book.setPublicationyear(rs.getInt("publicationyear"));
				book.setIsbn(rs.getString("isbn"));
				book.setCategory(rs.getString("category"));
				book.setTotaln(rs.getInt("totaln"));
				book.setAvailablen(rs.getInt("availablen"));
				bookList.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return bookList;
	}

    // 도서 정보 수정 메서드
	private static String BOOK_UPDATE = 
			  " update books "
			+ " set title=?, author=?, publisher=?, publicationyear=?,"
			+     " isbn=?, category=?, totaln=?, availablen=? "
			+ " where bookno=? ";

	public void updateBook(BookVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOOK_UPDATE);
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getAuthor());
			stmt.setString(3, vo.getPublisher());
			stmt.setInt(4, vo.getPublicationyear());
			stmt.setString(5, vo.getIsbn());
			stmt.setString(6, vo.getCategory());
			stmt.setInt(7, vo.getTotaln());
			stmt.setInt(8, vo.getAvailablen());
			stmt.setInt(9, vo.getBookno());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
    // 도서 정보 삭제 메서드
	private static String BOOK_DElETE =
			" delete books " +
			" where bookno = ? ";
	
	public void deleteBook(BookVO vo) throws Exception {
		try {
			conn = JDBCUtil.getConnection();
	        stmt = conn.prepareStatement(BOOK_DElETE);
	        stmt.setInt(1, vo.getBookno());
			stmt.executeUpdate();
		} catch (Exception e) {
//			e.printStackTrace();
			throw new Exception("도서 정보 삭제 중 오류가 발생했습니다.", e);
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
}























