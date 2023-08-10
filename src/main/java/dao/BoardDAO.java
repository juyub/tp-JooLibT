package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import common.JDBCUtil;
import vo.BoardVO;

public class BoardDAO {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	// 모든 게시글 조회
	public List<BoardVO> selectAllBoard(int pageNo, int pageSize) {
		int start = (pageNo - 1) * pageSize + 1;
		int end = pageNo * pageSize;

		List<BoardVO> boardList = new ArrayList<BoardVO>();
		String query = " SELECT * " + " FROM ( "
				+ " SELECT LEVEL, boardNO, parentNO, title, content, REGtime, userid, hit, ROWNUM AS rnum "
				+ " FROM tjl_board " + " START WITH parentNO = 0 " + " CONNECT BY PRIOR boardNO = parentNO "
				+ " ORDER SIBLINGS BY boardNO DESC " + " ) " + " WHERE rnum BETWEEN ? AND ? ";

		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, start);
			stmt.setInt(2, end);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int level = rs.getInt("level");
				int boardNO = rs.getInt("boardNO");
				int parentNO = rs.getInt("parentNO");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String userid = rs.getString("userid");
				Timestamp regtime = rs.getTimestamp("regtime");
				int hit = rs.getInt("hit");

				BoardVO board = new BoardVO();
				board.setLevel(level);
				board.setBoardNO(boardNO);
				board.setParentNO(parentNO);
				board.setTitle(title);
				board.setContent(content);
				board.setUserid(userid);
				board.setRegtime(regtime);
				board.setHit(hit);

				boardList.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return boardList;
	}

	public int selectBoardCount() {
		int count = 0;
		String query = "SELECT COUNT(*) AS count FROM tjl_board";

		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return count;
	}

	// 게시글 추가
	public void addBoard(BoardVO vo) {
		try {
			conn = JDBCUtil.getConnection();

			// getNewArticleNO() 메서드를 여기에 정의하고 사용
			int boardNO = 0;
			String boardNOQuery = " SELECT seq_boardno.NEXTVAL FROM DUAL ";
			stmt = conn.prepareStatement(boardNOQuery);
			ResultSet rs = stmt.executeQuery(boardNOQuery);
			if (rs.next()) {
				boardNO = rs.getInt(1) + 1;
			}
			rs.close();
			stmt.close();

			int parentNO = 0;
			String title = vo.getTitle();
			String content = vo.getContent();
			String userid = vo.getUserid();

			String query = " INSERT INTO tjl_board (boardNO, parentNO, title, content, userid) "
					+ " VALUES (?, ? ,?, ?, ?) ";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, boardNO);
			stmt.setInt(2, parentNO);
			stmt.setString(3, title);
			stmt.setString(4, content);
			stmt.setString(5, userid);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
	}

	// 게시판 번호로 getBoard
	public BoardVO selectBoardNo(BoardVO vo) {
		BoardVO board = new BoardVO();
		try {
			conn = JDBCUtil.getConnection();
			String query = " SELECT boardNO, parentNO, title, content, userid, regtime " + " FROM tjl_board "
					+ " WHERE boardNO = ? ";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, vo.getBoardNO());
			ResultSet rs = stmt.executeQuery();
			rs.next();
			int boardNO = rs.getInt("boardNO");
			int parentNO = rs.getInt("parentNO");
			String title = rs.getString("title");
			String content = rs.getString("content");
			String userid = rs.getString("userid");
			Timestamp regtime = rs.getTimestamp("regtime");

			board.setBoardNO(boardNO);
			board.setParentNO(parentNO);
			board.setTitle(title);
			board.setContent(content);
			board.setUserid(userid);
			board.setRegtime(regtime);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return board;
	}

	// 조회수증가 메소드
	public void updateHit(int boardNO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JDBCUtil.getConnection();
			String query = "UPDATE tjl_board SET hit = hit + 1 WHERE boardNO = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNO);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
	}

	// 답글 등록
	public void addReply(BoardVO vo) {
		try {
			conn = JDBCUtil.getConnection();

			// 원 게시물의 boardNO 가져오기
			int parentNO = vo.getParentNO();

			// getNewArticleNO() 메서드를 여기에 정의하고 사용
			int boardNO = 0;
			String boardNOQuery = " SELECT seq_boardno.NEXTVAL FROM DUAL ";
			stmt = conn.prepareStatement(boardNOQuery);
			ResultSet rs = stmt.executeQuery(boardNOQuery);
			if (rs.next()) {
				boardNO = rs.getInt(1) + 1;
			}
			rs.close();
			stmt.close();

			String title = vo.getTitle();
			String content = vo.getContent();
			String userid = vo.getUserid();

			String query =
					" INSERT INTO tjl_board (boardNO, parentNO, title, content, userid) "
					+ " VALUES (?, ? ,?, ?, ?) ";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, boardNO);
			stmt.setInt(2, parentNO); // 원 게시물 부모 번호 설정
			stmt.setString(3, title);
			stmt.setString(4, content);
			stmt.setString(5, userid);
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}

	}

	// 게시글 수정
	public void updateBoard(BoardVO vo) {
		try {
			String sql = 
					" update tjl_board set title = ?, content = ? " +
							" where boardNO = ? ";
			
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getContent());
			stmt.setInt(3, vo.getBoardNO());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	// 게시글 삭제
	public void deleteBoard(int boardNO) {
		String sql = 
				" delete tjl_board " +
				" WHERE boardNO in ( " +
								 " SELECT boardNO FROM  tjl_board " +
								 " START WITH boardNO = ? " +
								 " CONNECT BY PRIOR  boardNO = parentNO " +
								 " ) ";

		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, boardNO);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	// 게시판 검색 depth + paging
	public List<BoardVO> searchBoard(String search, int pageNo, int pageSize) {
	    int start = (pageNo - 1) * pageSize + 1;
	    int end = pageNo * pageSize;

	    List<BoardVO> boardList = new ArrayList<>();

	    String query = "SELECT * "
	            + " FROM (SELECT a.*, ROWNUM rnum "
	            + "      FROM (SELECT level, boardNO, parentNO, title, content, regtime, userid, hit "
	            + "            FROM tjl_board "
	            + "            WHERE title = ? OR userid = ? "
	            + "            START WITH parentNO = 0 "
	            + "            CONNECT BY PRIOR boardNO = parentNO "
	            + "            ORDER SIBLINGS BY boardNO DESC) a "
	            + "      WHERE ROWNUM <= ?) "
	            + " WHERE rnum >= ? ";

	    try {
	        conn = JDBCUtil.getConnection();
	        stmt = conn.prepareStatement(query);
	        stmt.setString(1, search);
	        stmt.setString(2, search);
	        stmt.setInt(3, end);
	        stmt.setInt(4, start);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            int level = rs.getInt("level");
	            int boardNO = rs.getInt("boardNO");
	            int parentNO = rs.getInt("parentNO");
	            String title = rs.getString("title");
	            String content = rs.getString("content");
	            String userid = rs.getString("userid");
	            Timestamp regtime = rs.getTimestamp("regtime");
	            int hit = rs.getInt("hit");

	            BoardVO board = new BoardVO();
	            board.setLevel(level);
	            board.setBoardNO(boardNO);
	            board.setParentNO(parentNO);
	            board.setTitle(title);
	            board.setContent(content);
	            board.setUserid(userid);
	            board.setRegtime(regtime);
	            board.setHit(hit);

	            boardList.add(board);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        JDBCUtil.close(rs, stmt, conn);
	    }
	    return boardList;
	}
	
//	public List<BoardVO> searchBoard(String search) {
//
//		List<BoardVO> boardList = new ArrayList<BoardVO>();
//		String query = 
//				"select * from jb_board " + 
//				" WHERE title = ? OR id = ? ";
//		
//		try {
//			conn = JDBCUtil.getConnection();
//			stmt = conn.prepareStatement(query);
//			stmt.setString(1, search);
//			stmt.setString(2, search);
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				int boardNO = rs.getInt("boardNO");
//				int parentNO = rs.getInt("parentNO");
//				String title = rs.getString("title");
//				String content = rs.getString("content");
//				String id = rs.getString("id");
//				Timestamp regtime = rs.getTimestamp("regtime");
//				int hit = rs.getInt("hit");
//
//				BoardVO board = new BoardVO();
//				board.setBoardNO(boardNO);
//				board.setParentNO(parentNO);
//				board.setTitle(title);
//				board.setContent(content);
//				board.setId(id);
//				board.setRegtime(regtime);
//				board.setHit(hit);
//
//				boardList.add(board);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			JDBCUtil.close(rs, stmt, conn);
//		}
//		return boardList;
//	}

}
