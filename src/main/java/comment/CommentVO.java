package comment;

import java.sql.Timestamp;

/*
   	CREATE TABLE tjl_comment (
		commentNO number(10) PRIMARY KEY,
		boardNO number(10),
		userid VARCHAR2(50),
		CONTENT VARCHAR2(2000),
		regtime TIMESTAMP DEFAULT SYSTIMESTAMP,
		FOREIGN KEY (userid) REFERENCES users(userid),
		FOREIGN KEY (boardNO) REFERENCES tjl_board(boardNO)
	);
	
	create sequence seq_tjl_comment_commentNO nocache;
*/

public class CommentVO {
	
	private int commentNO;
	private int boardNO;
	private String userid;
	private String content;
	private Timestamp regtime;
	
	public int getCommentNO() {
		return commentNO;
	}
	public void setCommentNO(int commentNO) {
		this.commentNO = commentNO;
	}
	public int getBoardNO() {
		return boardNO;
	}
	public void setBoardNO(int boardNO) {
		this.boardNO = boardNO;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getRegtime() {
		return regtime;
	}
	public void setRegtime(Timestamp regtime) {
		this.regtime = regtime;
	}
	
}
