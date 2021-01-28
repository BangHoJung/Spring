package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {
	private Connection conn;
      
	public DBManager() {
		try {
			//db 접속을 하고 connection을 하나 초기화 
			Class.forName(DBConfig.DB_DRIVER);
			conn = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.DB_USER, DBConfig.DB_PASS);
			//auto commit 비 활성화
			conn.setAutoCommit(false);
			System.out.println("DB 연결 완료");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return conn;
	}
	
	public void close(PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs != null)
			rs.close();
			if(pstmt != null)
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
