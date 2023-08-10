package comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.JDBCUtil;

public class CommentDAO {
	
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	public List<CommentVO> getCommentListByBoardNO(int boardNO) {
	    List<CommentVO> commentList = new ArrayList<CommentVO>();
	    String query =
	        " SELECT * " +
	        " FROM tjl_comment " +
	        " WHERE boardNO = ? ";

	    try {
	        conn = JDBCUtil.getConnection();
	        stmt = conn.prepareStatement(query);
	        stmt.setInt(1, boardNO);

	        rs = stmt.executeQuery();
	        while (rs.next()) {
	            CommentVO comment = new CommentVO();
	            comment.setCommentNO(rs.getInt("commentNO"));
	            comment.setBoardNO(rs.getInt("boardNO"));
	            comment.setUserid(rs.getString("userid"));
	            comment.setContent(rs.getString("CONTENT"));
	            comment.setRegtime(rs.getTimestamp("regtime"));
	            commentList.add(comment);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        JDBCUtil.close(rs, stmt, conn);
	    }
	    return commentList;
	}

	// 댓글 등록
	public void addComment(CommentVO vo) {
	    
	    try {
	        String sql =
	        		" INSERT INTO tjl_comment (commentNO, boardNO, userid, content) " +
	        		" VALUES (seq_commentNO.NEXTVAL,?, ?, ?) ";
	        conn = JDBCUtil.getConnection();
	        stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, vo.getBoardNO());
	        stmt.setString(2, vo.getUserid());
	        stmt.setString(3, vo.getContent());

	        stmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	    	JDBCUtil.close(rs, stmt, conn);
	    }
	}

	// 댓글 수정
	public void updateComment(CommentVO vo) {
		String sql =
				" update tjl_comment " +
						" set content=? where commentNO=? ";
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, vo.getContent());
			stmt.setInt(2, vo.getCommentNO());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	// 댓글 삭제
	public void deleteComment(CommentVO vo) {
		String sql =
				" delete tjl_comment where commentNO=? ";
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, vo.getCommentNO());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	// 게시글 삭제시 댓글 삭제
	public void deleteComment(int boardNO) {
		String sql =
				" delete tjl_comment where boardNO=? ";
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
	
}





















