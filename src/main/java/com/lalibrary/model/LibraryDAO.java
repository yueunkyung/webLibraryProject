package com.lalibrary.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//import com.lalibrary.dto.BorrowBookVO;
import com.lalibrary.dto.LibraryVO;
import com.lalibrary.util.DBUtil;

public class LibraryDAO {
	Connection conn;
	Statement st;
	PreparedStatement pst;
	ResultSet rs;
	
	//도서관 전체 조회
	public List<LibraryVO> selectLibraryAll(){
		List<LibraryVO> libraryAll = new ArrayList<>();
		String sql = "select * from lalibrary";
		conn = DBUtil.getConnection();

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				LibraryVO library = makeLalibraryList(rs);//reset에서 읽어서 VO만들기
				libraryAll.add(library);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnection(conn, st, rs);
		}
		
		return libraryAll;
	}
	
	//도서관-LibraryVO
	private LibraryVO makeLalibraryList(ResultSet rs) throws SQLException{
		LibraryVO libr = new LibraryVO();
		libr.setLibrary_id(rs.getString(1));
		libr.setLibrary_name(rs.getString(2));
		libr.setLoc(rs.getString(3));
		libr.setMember_count(rs.getInt(4));
		
		return libr;
	}


}

	
