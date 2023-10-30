package com.lalibrary.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lalibrary.dto.UserVO;
import com.lalibrary.util.DBUtil;

public class UserDAO {
	Connection conn;
	Statement st;
	PreparedStatement pst;
	ResultSet rs;

	//회원 조회
	public List<UserVO> selectUser(String target, String sqlParam){
		List<UserVO> showuser = new ArrayList<>();
		String sql = null;
		switch (target) {
			case "all": {
				sql = "select * from users";
				break;
			}
			case "user_name": {
				sql = "select * from users where user_name LIKE '%" + sqlParam + "%'";
				break;
			}
			case "user_id": {
				sql = "select * from users where user_id = '"+sqlParam+"'" ;
				break;
			}
			case "library_id": {
				sql = "select * from users where library_id = '"+sqlParam+"'" ;
				break;
			}
		}
			
		conn = DBUtil.getConnection();

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {		
				UserVO users = makeUserList(rs);//reset에서 읽어서 VO만들기
				showuser.add(users);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnection(conn, st, rs);
		}
		
		return showuser;
	}

	//회원 가입
	public int insertSignUp(UserVO memberInfo) {
		int count = 0;
		String sql = "{call add_new_user(?, ?, ?, ?)}";
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, memberInfo.getLibrary_id());
			pst.setString(2, memberInfo.getUser_name());
			pst.setString(3, memberInfo.getLoc());
			pst.setString(4, memberInfo.getPhone_no());
			
			count = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnection(conn, pst, rs);
		}
				
		return count;		
	}
	
	//로그인
	public UserVO signinCheck(String id, int password) {
		UserVO user = null;
		String sql = "select *"
					+ " from users where user_id = ? and password = ?";
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			pst.setInt(2, password);
			rs = pst.executeQuery();
			while(rs.next()) {
				user = makeUserList(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnection(conn, st, rs);
		}
		return user;
	}
	
	//회원-UserVO
	private UserVO makeUserList(ResultSet rs) throws SQLException {
		UserVO user = new UserVO();
		user.setUser_id( rs.getString(1));
		user.setLibrary_id(rs.getString(2));
		user.setUser_name(rs.getString(3));
		user.setJoin_date(rs.getString(4));
		user.setLoc(rs.getString(5));
		user.setPhone_no(rs.getString(6));
		
		return user;
	}


}

	
