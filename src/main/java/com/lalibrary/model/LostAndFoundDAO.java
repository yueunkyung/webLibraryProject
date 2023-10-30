package com.lalibrary.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lalibrary.dto.LostAndFoundVO;
import com.lalibrary.util.DBUtil;

public class LostAndFoundDAO {
	Connection conn;
	Statement st;
	PreparedStatement pst;
	ResultSet rs;

	// 분실물 보관센터-분실물 조회
	public List<LostAndFoundVO> selectLostAndFound(LostAndFoundVO lostItem) {
		List<LostAndFoundVO> showlist = new ArrayList<>();
		String sql = null;
		System.out.println("lost.getLibrary_id()"+lostItem.getLibrary_id());
		if( lostItem.getLibrary_id()!= null && lostItem.getProperty_name()!= null) {
			sql = "select * from lost_and_found where library_id ='" + lostItem.getLibrary_id() + "' and property_name LIKE '%" + lostItem.getProperty_name() + "%'";
		} else if (lostItem.getLibrary_id()!= null) {
			sql = "select * from lost_and_found where library_id ='" + lostItem.getLibrary_id() + "'";
		} else if (lostItem.getProperty_name()!= null) {
			sql = "select * from lost_and_found where property_name LIKE '%" + lostItem.getProperty_name() + "%'";
		} else {
			sql = "select * from lost_and_found";
		}
		conn = DBUtil.getConnection();

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				LostAndFoundVO item = makeLostItemList(rs);// reset에서 읽어서 VO만들기
				showlist.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnection(conn, st, rs);
		}
		return showlist;
	};

	// 분실물 등록
	public int insertLostItem(LostAndFoundVO itemInfo) {
		int count = 0;
		String sql = "{call add_new_lost(?,?,?)}";
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, itemInfo.getLibrary_id());
			pst.setString(2, itemInfo.getProperty_name());
			pst.setString(3, itemInfo.getFound_date());

			count = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnection(conn, pst, rs);
		}

		return count;
	}

	// 분실물 수령
	public int deleteLostItem(LostAndFoundVO itemId) {
		int count = 0;
		String sql = "delete from lost_and_found where property_id = ?";
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, itemId.getProperty_id());
			count = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnection(conn, pst, rs);
		}

		return count;
	}

	// 분실물 보관센터-LostAndFoundVO
	private LostAndFoundVO makeLostItemList(ResultSet rs) throws SQLException {
		LostAndFoundVO lostItem = new LostAndFoundVO();
		lostItem.setProperty_id(rs.getString(1));
		lostItem.setLibrary_id(rs.getString(2));
		lostItem.setProperty_name(rs.getString(3));
		lostItem.setFound_date(rs.getString(4));

		return lostItem;
	}

}
