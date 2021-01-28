package org.korea.mvc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.korea.mvc.dto.StudentDTO;

import config.DBManager;

public class StudentDAO {

	private DBManager manager;

	public StudentDAO(DBManager manager) {
		this.manager = manager;
	}

	public ArrayList<StudentDTO> searchAllStudent() {
		ArrayList<StudentDTO> list = new ArrayList<StudentDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM student";
		
		try {
			pstmt = manager.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(new StudentDTO(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4)));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			manager.close(pstmt, rs);
		}
		
		return list;
	}
	
	
}
