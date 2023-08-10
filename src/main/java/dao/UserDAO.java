package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.JDBCUtil;
import vo.BookVO;
import vo.UserVO;

public class UserDAO {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	// 로그인 메서드
	private static String USER_GET = " select * from users " + " where userid = ? and password = ? ";

	public UserVO getUser(UserVO vo) {
		UserVO user = null;

		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_GET);
			stmt.setString(1, vo.getUserid());
			stmt.setString(2, vo.getPassword());

			rs = stmt.executeQuery();
			if (rs.next()) {
				user = new UserVO();
				user.setUserno(rs.getInt("userno"));
				user.setUserid(rs.getString("userid"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setBorrown(rs.getInt("borrown"));
				user.setRole(rs.getString("role"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		return user;
	}

	// 회원가입 메서드
	private static String USER_ADD = " insert into users (userid, password, name, phone) " + " values (?,?,?,?) ";

	public void addUser(UserVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_ADD);
			stmt.setString(1, vo.getUserid());
			stmt.setString(2, vo.getPassword());
			stmt.setString(3, vo.getName());
			stmt.setString(4, vo.getPhone());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	// 전체 사용자 목록 조회 메서드
	private static String USER_LIST = " select * from users order by userno desc";

	public List<UserVO> getUserList(UserVO vo) {
		List<UserVO> userList = new ArrayList<UserVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_LIST);
			rs = stmt.executeQuery();
			while (rs.next()) {
				UserVO user = new UserVO();
				user.setUserno(rs.getInt("userno"));
				user.setUserid(rs.getString("userid"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setPhone(rs.getString("phone"));
				user.setBorrown(rs.getInt("borrown"));
				user.setJoindate(rs.getDate("joindate"));
				user.setRole(rs.getString("role"));
				userList.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return userList;
	}

	// userno로 사용자 정보 조회 메서드
	private static String USER_GET_NO = " select * from users " + " where userno = ? ";

	public UserVO getUserNo(UserVO vo) {
		UserVO user = null;

		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_GET_NO);
			stmt.setInt(1, vo.getUserno());

			rs = stmt.executeQuery();
			if (rs.next()) {
				user = new UserVO();
				user.setUserno(rs.getInt("userno"));
				user.setUserid(rs.getString("userid"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setPhone(rs.getString("phone"));
				user.setBorrown(rs.getInt("borrown"));
				user.setJoindate(rs.getDate("joindate"));
				user.setRole(rs.getString("role"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		return user;
	}

	// 사용자 정보 수정 메서드
	private static String USER_UPDATE = " update users "
			+ " set password=?, phone=?, borrown=?, role=? where userno=? ";

	public void updateUser(UserVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_UPDATE);
			stmt.setString(1, vo.getPassword());
			stmt.setString(2, vo.getPhone());
			stmt.setInt(3, vo.getBorrown());
			stmt.setString(4, vo.getRole());
			stmt.setInt(5, vo.getUserno());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	// 사용자 정보 삭제 메서드
	private static String USER_DElETE = " delete users " + " where userno = ? ";

	public void deleteUser(UserVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_DElETE);
			stmt.setInt(1, vo.getUserno());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

//	public UserVO getUserByID(String id) {
//		String query = " select * from users where userid = ? ";
//		UserVO user = null;
//
//		try {
//			conn = JDBCUtil.getConnection();
//			stmt = conn.prepareStatement(query);
//			stmt.setString(1, id);
//
//			rs = stmt.executeQuery();
//			if (rs.next()) {
//				user = new UserVO();
//				user.setUserno(rs.getInt("userno"));
//				user.setUserid(rs.getString("userid"));
//				user.setPassword(rs.getString("password"));
//				user.setName(rs.getString("name"));
//				user.setBorrown(rs.getInt("borrown"));
//				user.setRole(rs.getString("role"));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			JDBCUtil.close(stmt, conn);
//		}
//		return user;
//	}

	// 중복ID를 체크하는 메서드
	public boolean checkId(String id) {
		boolean check = false; // 중복ID유무
		String query = " select * from users where userid = ? ";

		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, id);
			rs = stmt.executeQuery();

			check = rs.next();// true
		} catch (Exception e) {
			e.printStackTrace();
		} finally { 
			JDBCUtil.close(rs, stmt, conn);
		}
		return check;
	}

	// 회원 검색 - 이름
	public List<UserVO> searchUserName(UserVO vo) {
		List<UserVO> userList = new ArrayList<UserVO>();
		String query = " select * from users where name LIKE '%' || ? || '%' ";
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, vo.getName());
			
			rs = stmt.executeQuery();
			while (rs.next()) {
				UserVO user = new UserVO();
				user.setUserno(rs.getInt("userno"));
				user.setUserid(rs.getString("userid"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setPhone(rs.getString("phone"));
				user.setBorrown(rs.getInt("borrown"));
				user.setJoindate(rs.getDate("joindate"));
				user.setRole(rs.getString("role"));
				userList.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return userList;
	}
	// 회원 검색 - 연락처
	public List<UserVO> searchUserPhone(UserVO vo) {
		List<UserVO> userList = new ArrayList<UserVO>();
		String query = " select * from users where phone LIKE '%' || ? || '%' ";
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, vo.getPhone());
			
			rs = stmt.executeQuery();
			while (rs.next()) {
				UserVO user = new UserVO();
				user.setUserno(rs.getInt("userno"));
				user.setUserid(rs.getString("userid"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setPhone(rs.getString("phone"));
				user.setBorrown(rs.getInt("borrown"));
				user.setJoindate(rs.getDate("joindate"));
				user.setRole(rs.getString("role"));
				userList.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return userList;
	}
	
	

}


























