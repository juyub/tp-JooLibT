package vo;

import java.util.Date;

/*
	CREATE TABLE borrows (
	    borrowno NUMBER(10, 0) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	    userno NUMBER(10, 0) NOT NULL,
	    bookno NUMBER(10, 0) NOT NULL,
	    borrowdate DATE NOT NULL,
	    duedate DATE NOT NULL,
	    returndate DATE,
	    FOREIGN KEY (userno) REFERENCES users(userno),
	    FOREIGN KEY (bookno) REFERENCES books(bookno)
	);
*/

public class BorrowVO {

	private int borrowno;
    private int userno;
    private int bookno;
    private Date borrowdate;
    private Date duedate;
    private Date returndate;
    
    private String username;
    private String booktitle;
	
    public int getBorrowno() {
		return borrowno;
	}
	public void setBorrowno(int borrowno) {
		this.borrowno = borrowno;
	}
	public int getUserno() {
		return userno;
	}
	public void setUserno(int userno) {
		this.userno = userno;
	}
	public int getBookno() {
		return bookno;
	}
	public void setBookno(int bookno) {
		this.bookno = bookno;
	}
	public Date getBorrowdate() {
		return borrowdate;
	}
	public void setBorrowdate(Date borrowdate) {
		this.borrowdate = borrowdate;
	}
	public Date getDuedate() {
		return duedate;
	}
	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}
	public Date getReturndate() {
		return returndate;
	}
	public void setReturndate(Date returndate) {
		this.returndate = returndate;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBooktitle() {
		return booktitle;
	}
	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}
	@Override
	public String toString() {
		return "BorrowVO [borrowno=" + borrowno + ", userno=" + userno + ", bookno=" + bookno + ", borrowdate="
				+ borrowdate + ", duedate=" + duedate + ", returndate=" + returndate + ", username=" + username
				+ ", booktitle=" + booktitle + "]";
	}
	
    
}
