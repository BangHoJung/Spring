package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import dao.EmployeeDAO;
import dao.EmployeeDAO2;
import dto.EmployeeDTO;

/**
 * Servlet implementation class Search2Servlet
 */
@WebServlet("/search2.do")
public class Search2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search2Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		System.out.println("search2 servlet");
		String name = request.getParameter("name");
		System.out.println(name);
		ArrayList<EmployeeDTO> list;
		try {
			EmployeeDAO2 dao = new EmployeeDAO2();
			list = dao.searchEmployee_name(name);
			JSONArray arr = new JSONArray(list);
			System.out.println(arr.toString());
			response.getWriter().write(arr.toString());
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(444,e.getMessage());
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
