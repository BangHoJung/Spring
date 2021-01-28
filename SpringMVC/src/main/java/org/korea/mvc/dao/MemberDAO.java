package org.korea.mvc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.korea.mvc.dto.MemberDTO;

import config.DBManager;

public class MemberDAO {
	private DBManager manager;
	
	public MemberDAO(DBManager manager) {
		this.manager = manager;
		System.out.println("DAO Constructor");
	}

	public MemberDTO loginMember(String id, String pass) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDTO dto = null;
		String sql = "SELECT * FROM member WHERE id=? AND pass=?";
		
		try {
			pstmt = manager.getConnection().prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new MemberDTO(rs.getString(1),null, rs.getString(3), rs.getInt(4), rs.getInt(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			manager.close(pstmt, rs);
		}
		
		return dto;
	}
	
	
}
