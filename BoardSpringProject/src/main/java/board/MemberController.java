package board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import board.service.MemberService;
import board.vo.MemberVO;
import jdk.nashorn.api.scripting.JSObject;

@Controller
public class MemberController {
	
	private MemberService service;

	public MemberController(MemberService service) {
		this.service = service;
	}

	@RequestMapping("/")
	public String index() {
		System.out.println("main");
		return "main";
	}
	@RequestMapping("main.do")
	public String main(HttpServletRequest request,HttpSession session) {
		String param = "";
		if(request.getQueryString()!=null) {
			param += "?"+request.getQueryString();
		}
		session.setAttribute("last", request.getRequestURI()+param);
		System.out.println("last : " + request.getRequestURI()+param);
		return "main";
	}
	
	@RequestMapping("login_view.do")
	public String loginView() {
		return "member/login";
	}
	
	@RequestMapping("login.do")
	public String login(HttpServletRequest request,HttpServletResponse response ,HttpSession session) {
		response.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		MemberVO vo = service.loginMember(id,pass);
		
		if(vo != null) {
			session.setAttribute("login", true);
			session.setAttribute("id", vo.getId());
			session.setAttribute("name", vo.getName());
			session.setAttribute("grade", vo.getGrade());
			session.setAttribute("grade_name", vo.getGrade_name());
			return "main";
		}
		else {
			session.setAttribute("login", false);
			try {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write("<script>alert('일치하는 계정이 없습니다.');history.back();</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	@RequestMapping("logout.do")
	public String logout(HttpSession session, HttpServletRequest request) {
		String last = (String) session.getAttribute("last");
		session.invalidate();
		session = request.getSession();
		session.setAttribute("last", last);
		return "main";
	}
	
	@RequestMapping("update_member_view.do")
	public String updateMemberView(HttpSession session,HttpServletRequest request ) {
		String id = (String) session.getAttribute("id");
		MemberVO vo = service.searchMember(id);
		request.setAttribute("vo", vo);
		session.setMaxInactiveInterval(60*30);
		return "member/update_member_view";
	}
	
	@RequestMapping("update_member.do")
	public String updateMember(HttpSession session,HttpServletRequest request, HttpServletResponse response) {
		
		String id = (String)session.getAttribute("id");
		if(id == null) {
			try {
				response.setContentType("type/html; charset=utf-8");
				response.getWriter().write("<script>alert('로그인 세션이 만료되었습니다');location.href='main.do';</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		
		service.updateMember(id,pass,name,age);
		session.setMaxInactiveInterval(60*30);
		
		return "main";
	}
	
	@RequestMapping("member_manage.do")
	public String MemberManagee(HttpSession session,HttpServletResponse response, HttpServletRequest request) {
		if(session.getAttribute("login") == null || !session.getAttribute("grade_name").equals("master")) {
			try {
				response.getWriter().write("<script>alert('권한이 없습니다.');history.back();</script>");
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String param = "";
		if(request.getQueryString()!=null) {
			param += "?"+request.getQueryString();
		}
		session.setAttribute("last", request.getRequestURI()+param);
		System.out.println("last : " + request.getRequestURI()+param);
		return "member/member_manage";
	}
	
	@RequestMapping("member_manage_search.do")
	public void MemberManageSearch(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		String kind = request.getParameter("kind");
		String search = request.getParameter("search");
		if(search.equals(null)) search="";
		
		List<MemberVO> list =  service.memberManageSearch(kind,search);
		
		JSONObject jsonObject = new JSONObject();
    	jsonObject.put("kind",kind);
    	jsonObject.put("search",search);
    	JSONArray jsonArray = new JSONArray(list);
    	
    	jsonObject.put("result",jsonArray);
    	if(jsonArray.length() > 0) {
    		jsonObject.put("responseCode", 200);
    	}
    	else {
    		jsonObject.put("responseCode", 500);
    	}
    	try {
			response.getWriter().write((jsonObject.toString()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("member_manage_update.do")
	public void MemberManageUpdate(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		int grade = Integer.parseInt(request.getParameter("grade"));
		int count = service.memberManageUpdate(new MemberVO(id, null, name, age, grade));
		if(count == 0) {
			try {
				response.sendError(1000);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			response.getWriter().write(""+count);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("member_manage_delete.do")
	public void MemberManageDelete(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		int count = service.memberManageDelete(id);
		if(count == 0) {
			try {
				response.sendError(1000);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			response.getWriter().write(""+count);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("sendLog.do")
	public void sendLog(HttpServletRequest request, HttpServletResponse response) {
		String log_date = request.getParameter("log_date");
		String code_number = request.getParameter("code_number");
		String message = request.getParameter("message");
		
		System.out.println(log_date + "," + code_number + "," + message);
		service.insertLog(log_date, code_number ,message);
		
	}
}
