package vo;

import java.util.Date;

/*
	CREATE TABLE users (
        userno NUMBER(10, 0) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
        userid VARCHAR2(255) NOT NULL UNIQUE,
        password VARCHAR2(255) NOT NULL,
        name VARCHAR2(255) NOT NULL,
        phone VARCHAR2(20) NOT NULL,
        borrown NUMBER(2, 0) DEFAULT '3',
        joindate DATE DEFAULT CURRENT_DATE,
        ROLE VARCHAR2(5) DEFAULT 'user'
	);
 */

public class UserVO {

	private int userno;
    private String userid;
    private String password;
    private String name;
    private String phone;
    private int borrown;
    private Date joindate;
    private String role;
	
	public int getUserno() {
		return userno;
	}
	public void setUserno(int userno) {
		this.userno = userno;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getBorrown() {
		return borrown;
	}
	public void setBorrown(int borrown) {
		this.borrown = borrown;
	}
	public Date getJoindate() {
		return joindate;
	}
	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
