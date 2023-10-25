package com.lalibrary.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	
	//DB연결
	public static Connection getConnection() {
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String userid="LALI", password="1234";
		Connection conn = null;	//연결

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, userid, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return conn;
	}
	
	//자원반납(DB연결해제, statement해제, resultset 해제)
	public static void dbDisConnection(Connection conn, Statement st, ResultSet rs) {
		try {
			if(rs!=null) rs.close();
			if(st!=null) st.close();
			if(conn!=null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
