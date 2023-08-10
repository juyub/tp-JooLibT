package vo;

/*
	CREATE TABLE books (
	    bookno NUMBER(10, 0) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	    title VARCHAR2(255) NOT NULL,
	    author VARCHAR2(255) NOT NULL,
	    publisher VARCHAR2(255),
	    publicationyear NUMBER(4, 0),
	    isbn VARCHAR2(13) UNIQUE,
	    category VARCHAR2(255),
	    totaln NUMBER(10, 0),
	    availablen NUMBER(10, 0)
	);
*/

public class BookVO {

	private int bookno;
    private String title;
    private String author;
    private String publisher;
    private int publicationyear;
    private String isbn;
    private String category;
    private int totaln;
    private int availablen;
    
	public int getBookno() {
		return bookno;
	}
	public void setBookno(int bookno) {
		this.bookno = bookno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getPublicationyear() {
		return publicationyear;
	}
	public void setPublicationyear(int publicationyear) {
		this.publicationyear = publicationyear;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getTotaln() {
		return totaln;
	}
	public void setTotaln(int totaln) {
		this.totaln = totaln;
	}
	public int getAvailablen() {
		return availablen;
	}
	public void setAvailablen(int availablen) {
		this.availablen = availablen;
	}
	
}
