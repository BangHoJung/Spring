package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import config.DBManager;
import dto.EmployeeDTO;
import oracle.jdbc.proxy.annotation.Pre;

public class EmployeeDAO {
	private static EmployeeDAO instance = new EmployeeDAO();
	DBManager manager;
	private EmployeeDAO() {
		manager = DBManager.getInstance();
	}
	
	public static EmployeeDAO getInstance() {
		if(instance == null) instance = new EmployeeDAO();
		return instance;
	}

	public String searchEmployee_position(int position) {
		ArrayList<EmployeeDTO> list = new ArrayList<EmployeeDTO>();
		String result=null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM employee WHERE position >=?";
		try {
			pstmt = manager.getConnection().prepareStatement(sql);
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
			manager.close(pstmt, rs);
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
			pstmt = manager.getConnection().prepareStatement(sql);
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
			manager.close(pstmt, rs);
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
			pstmt = manager.getConnection().prepareStatement(sql);
			pstmt.setString(1, eno);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new EmployeeDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			manager.close(pstmt, rs);
		}
		
		return dto;
	}
	
	public ArrayList<EmployeeDTO> searchEmployeeAll() {
		ArrayList<EmployeeDTO> list = new ArrayList<EmployeeDTO>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT e.eno,e.name,e.department,p.name FROM employee e,position_list p WHERE e.position=p.pno";
		try {
			pstmt = manager.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new EmployeeDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
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
