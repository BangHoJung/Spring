package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import config.AppContext;
import config.DBManager;
import dao.EmployeeDAO;
import dto.EmployeeDTO;

/**
 * Servlet implementation class searchServlet
 */
@WebServlet("/search.do")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		System.out.println("search servlet");
		String name = request.getParameter("name");
		System.out.println(name);
		ArrayList<EmployeeDTO> list;
		//EmployeeDAO dao = new EmployeeDAO(AppContext.getContext().getBean(DBManager.class));
		try {
			//list = dao.searchEmployee_name(name);
			list = AppContext.getContext().getBean(EmployeeDAO.class).searchEmployee_name(name);
			JSONArray arr = new JSONArray(list);
			System.out.println(arr.toString());
			response.getWriter().write(arr.toString());
		} catch (Exception e) {
			//e.printStackTrace();
			response.sendError(444,"데이터가 없습니다");
		}
//		JSONObject json = new JSONObject();
//		json.put("list", arr);
//		System.out.println(""+json);
//		response.getWriter().write(json.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
