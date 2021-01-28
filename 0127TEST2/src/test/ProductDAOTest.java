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
import dao.ProductDAO;
import dto.ProductDTO;

class ProductDAOTest {

	private static Connection conn = null;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		File file = new File("product.json");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String result="";
		
		while(true) {
			String str = br.readLine();
			if(str == null) break;
			result += str;
		}
		
		conn = DBManager.getInstance().getConnection();
		conn.setAutoCommit(false);
		String sql = "INSERT INTO product VALUES(?,?,?,?,?)";
		PreparedStatement pstmt = null;
		JSONArray arr = new JSONArray(result);
		
		for(int i=0;i<arr.length();i++) {
			pstmt = conn.prepareStatement(sql);
			JSONObject obj = new JSONObject(arr.get(i).toString());
			pstmt.setString(1, obj.getString("product_no"));
			pstmt.setString(2, obj.getString("product_name"));
			pstmt.setInt(3, obj.getInt("price"));
			pstmt.setInt(4, obj.getInt("ea"));
			pstmt.setString(5, obj.getString("maker"));
			pstmt.executeUpdate();
			pstmt.close();
		}
	}

	@DisplayName("제품정보 검색 테스트")
	@Test
	void test() {
		String pno = "89451111152";
//		String pno = "425512627683";
		ProductDTO dto =  ProductDAO.getInstance().searchProduct_pno(pno);
		if(dto == null) {
			fail("Not Found");
		}
		else {
			System.out.println(dto);
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
