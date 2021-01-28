package org.korea.mvc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.korea.mvc.dto.EmployeeDTO;

import config.DBManager;

public class EmployeeDAO {

	private DBManager manager;

	public EmployeeDAO(DBManager manager) {
		this.manager = manager;
	}

	public ArrayList<EmployeeDTO> serachAllEmployee() {
		ArrayList<EmployeeDTO> list = new ArrayList<EmployeeDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM employee";
		
		try {
			pstmt = manager.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(new EmployeeDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			manager.close(pstmt, rs);
		}
		
		return list;
	}
	
}
