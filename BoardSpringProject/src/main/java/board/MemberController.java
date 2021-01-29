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
//		String param = "";
//		if(request.getQueryString()!=null) {
//			param += "?"+request.getQueryString();
//		}
//		app.setAttribute("last", request.getRequestURI()+param);
//		System.out.println(request.getRequestURI()+param);
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
	public String logout(HttpSession session) {
		session.invalidate();
		return "main";
	}
	
	@RequestMapping("member_manage.do")
	public String MemberManagee(HttpSession session,HttpServletResponse response) {
		if(session.getAttribute("login") == null || !session.getAttribute("grade").equals("master")) {
			try {
				response.getWriter().write("<script>alert('권한이 없습니다.');history.back();</script>");
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "member/member_manage";
	}
	
	@RequestMapping("member_manage_search.do")
	public void MemberManageSearch(HttpServletRequest request, HttpServletResponse response) {
		String kind = request.getParameter("kind");
		String search = request.getParameter("search");
		if(search.equals(null)) search="";
		
		List<MemberVO> list =  service.memberManageSearch(kind,search);
		
		JSONObject jsonObject = new JSONObject();
    	jsonObject.put("kind",kind);
    	jsonObject.put("search",search);
    	JSONArray jsonArray = new JSONArray(list);
    	
    	jsonObject.put("result",jsonArray);
    	try {
			response.getWriter().write((jsonObject.toString()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
