package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.ProductDAO;
import dto.ProductDTO;

/**
 * Servlet implementation class ProductInputServlet
 */
@WebServlet("/productInput.do")
public class ProductInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductInputServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		System.out.println("productInput servlet");
		String pno = request.getParameter("pno");
		String pname = request.getParameter("pname");
		String maker = request.getParameter("maker");
		int price=0;
		int ea=0;
		if(pno.length()<1 || pname.length() <1 || maker.length()<1) {
			response.sendError(1001);
			return;
		}
		try {
			price = Integer.parseInt(request.getParameter("price"));
			ea = Integer.parseInt(request.getParameter("ea"));
		} catch(Exception e) {
			response.sendError(1002);
			return;
		}
		
		if(price < 0 || ea < 0) {
			response.sendError(1003);
			return;
		}
		
		try {
			ProductDAO.getInstance().insertProduct(new ProductDTO(pno,pname,maker,price,ea));
			ArrayList<ProductDTO> list = ProductDAO.getInstance().searchAllProduct();
			JSONObject obj = new JSONObject();
			JSONArray arr = new JSONArray(list);
			obj.put("list", arr);
			response.getWriter().write(""+obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			response.sendError(Integer.parseInt(e.getMessage()));
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
