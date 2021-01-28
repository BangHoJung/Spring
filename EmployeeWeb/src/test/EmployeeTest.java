package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import config.DBManager;
import dao.EmployeeDAO;
import dto.EmployeeDTO;

class EmployeeTest {
	private static Connection conn = null;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		// json 파일 읽어서 출력
		File file = new File("employee.json");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String result="";
		
		while(true) {
			String str = br.readLine();
			if(str == null) break;
			result += str;
		}
		System.out.println(result);
		//DB 연결
		conn = DBManager.getInstance().getConnection();
		conn.setAutoCommit(false);
		String sql = "INSERT INTO employee VALUES(?,?,?,?)";
		PreparedStatement pstmt = null;
		JSONArray arr = new JSONArray(result);
		
		for(int i=0;i<arr.length();i++) {
			pstmt = conn.prepareStatement(sql);
			JSONObject obj = new JSONObject(arr.get(i).toString());
			pstmt.setString(1, obj.getString("eno"));
			pstmt.setString(2, obj.getString("name"));
			pstmt.setString(3, obj.getString("department"));
			pstmt.setInt(4, obj.getInt("position"));
			pstmt.executeUpdate();
			pstmt.close();
		}
		
//		// json 처리
//		ArrayList<EmployeeDTO> list = new ArrayList<EmployeeDTO>();
//		JSONArray arr = new JSONArray(result);
//		for(int i=0;i<arr.length();i++) {
//			JSONObject obj = new JSONObject(arr.get(i).toString());
//			System.out.println("name : "+obj.get("name"));
//			list.add(new EmployeeDTO(obj.getString("eno"), obj.getString("name"), obj.getString("department"), obj.getInt("position"), obj.getInt("salary")));
//		}
		
	}

	@DisplayName("전체 사원 정보 조회")
	@Test
	void test() {
		ArrayList<EmployeeDTO> list = EmployeeDAO.getInstance().searchEmployeeAll();
		if(list.size() == 0) 
		{
			fail("Not yet implemented");
		}
		else {
			for(int i=0;i<list.size();i++) {
				System.out.println(list.get(i));
			}
		}
	}
	
	@DisplayName("해당 사원 정보 삭제")
	@Test
	public void testDeleteEmployee() {
		String eno = "MR62";
		String sql = "DELETE FROM employee WHERE eno = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, eno);
			
			int count = pstmt.executeUpdate();
			if(count == 0) {
				fail("사원정보 삭제 테스트 실패");
			}
			else{
				System.out.println("삭제 성공");
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	@AfterAll
	public static void end() {
		try {
			conn.rollback();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
