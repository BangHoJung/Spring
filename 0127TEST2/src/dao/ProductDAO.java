package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import config.DBManager;
import dto.ProductDTO;

public class ProductDAO {
	private static ProductDAO instance = new ProductDAO();
	DBManager manager;
	private ProductDAO() {
		manager = DBManager.getInstance();
	}
	
	public static ProductDAO getInstance() {
		if(instance == null) instance = new ProductDAO();
		return instance;
	}

	public void insertProduct(ProductDTO productDTO) throws Exception {
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO product VALUES(?,?,?,?,?)";
		
		try {
			pstmt = manager.getConnection().prepareStatement(sql);
			pstmt.setString(1, productDTO.getPno());
			pstmt.setString(2, productDTO.getPname());
			pstmt.setInt(3, productDTO.getPrice());
			pstmt.setInt(4, productDTO.getEa());
			pstmt.setString(5, productDTO.getMaker());
			
			int count = pstmt.executeUpdate();
			if(count == 0) throw new Exception("1001");
			manager.getConnection().commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new Exception("500");
		} finally {
			manager.close(pstmt, null);
		}
	}

	public ArrayList<ProductDTO> searchAllProduct() {
		ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT product_no,product_name,maker,price,ea FROM product";
		
		try {
			pstmt = manager.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(new ProductDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5)));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			manager.close(pstmt, rs);
		}
		return list;
	}

	public ProductDTO searchProduct_pno(String pno) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM product WHERE product_no = ?";
		ProductDTO dto = null;
		
		try {
			pstmt = manager.getConnection().prepareStatement(sql);
			pstmt.setString(1, pno);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new ProductDTO(rs.getString(1), rs.getString(2), rs.getString(5), rs.getInt(3), rs.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			manager.close(pstmt, rs);
		}
		
		return dto;
		
	}
	
	
}
