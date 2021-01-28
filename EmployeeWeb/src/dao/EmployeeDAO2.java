package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import org.json.JSONArray;


import config.DBConfig;
import dto.EmployeeDTO;

public class EmployeeDAO2 {
	private Connection conn;
	public EmployeeDAO2() {
		try {
			Class.forName(DBConfig.DB_DRIVER);
			conn = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.DB_USER, DBConfig.DB_PASS);
			conn.setAutoCommit(true);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String searchEmployee_position(int position) {
		ArrayList<EmployeeDTO> list = new ArrayList<EmployeeDTO>();
		String result=null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM employee WHERE position >=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, position);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new EmployeeDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
			JSONArray arr = new JSONArray(list);
			result = arr.toString();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public String searchEmployee_lowSalary(int count) {
		ArrayList<EmployeeDTO> list = new ArrayList<EmployeeDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result=null;
		
		String sql = "SELECT re.*,ROWNUM FROM (SELECT e.eno,name,department,"
				+ "(SELECT name FROM position_list WHERE position=pno) position,s.salary FROM employee e, employee_salary s"
				+ " WHERE e.eno=s.eno ORDER BY salary) re WHERE ROWNUM <=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, count);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new EmployeeDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
			}
			JSONArray arr = new JSONArray(list);
			result = arr.toString();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}
	
	public EmployeeDTO searchEmployee_eno(String eno) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EmployeeDTO dto = null;
		String sql = "SELECT e.eno,e.name,e.department,p.name,s.salary FROM employee e, employee_salary s, position_list p "
				+ "WHERE e.eno = s.eno AND e.position=p.pno AND e.eno = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, eno);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new EmployeeDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return dto;
	}
	
	public ArrayList<EmployeeDTO> searchEmployeeAll() {
		ArrayList<EmployeeDTO> list = new ArrayList<EmployeeDTO>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT e.eno,e.name,e.department,p.name FROM employee e,position_list p WHERE e.position=p.pno";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new EmployeeDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}

	public ArrayList<EmployeeDTO> searchEmployee_name(String name) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<EmployeeDTO> list = new ArrayList<EmployeeDTO>();
		name = "%" + name + "%";
		String sql = "SELECT e.eno,e.name,e.department,p.name FROM employee e, position_list p WHERE e.position=p.pno AND e.name LIKE ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(new EmployeeDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(list.size()==0) {
			throw new Exception("데이터가 없습니다");
		}
		
		return list;
	}
	
	
}
