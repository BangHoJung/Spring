package student;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import student.dto.StudentDTO;
import student.service.StudentService;

@Controller
public class MainController {
	
	private StudentService service;
	
	public MainController(StudentService service) {
		this.service = service;
	}

	@RequestMapping("/")
	public String index() {
		
		return "student_search";
	}
	
	@RequestMapping("search_all.do")
	public void SearchAll(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		String kind = request.getParameter("kind");
		String search = request.getParameter("search");
		if(search.equals(null)) search="";
		
		List<StudentDTO> list =  service.searchAllStudent(kind,search);
		
		JSONObject jsonObject = new JSONObject();
    	jsonObject.put("kind",kind);
    	jsonObject.put("search",search);
    	JSONArray jsonArray = new JSONArray(list);
    	
    	if(jsonArray.length() > 0) {
    		jsonObject.put("result",jsonArray);
    		jsonObject.put("code", 200);
    		jsonObject.put("message", "정상적으로 조회되었습니다");
    	}
    	else {
    		jsonObject.put("code", 500);
    		jsonObject.put("result","None");
    		jsonObject.put("message", "조회된 데이터가 없습니다");
    	}
    	try {
			response.getWriter().write((jsonObject.toString()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("send_log.do")
	public void SendLog(HttpServletRequest request, HttpServletResponse response) {
		String log_date = request.getParameter("log_date");
		int error_code = Integer.parseInt(request.getParameter("error_code"));
		String content = request.getParameter("content");
		
		System.out.println(log_date + "," + error_code + "," + content);
		int count = service.insertLog(log_date, error_code ,content);
		System.out.println(count);
		try {
			response.getWriter().write("count : " + count);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
