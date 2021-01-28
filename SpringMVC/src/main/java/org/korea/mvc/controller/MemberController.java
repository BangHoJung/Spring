package org.korea.mvc.controller;


import java.io.IOException;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.korea.mvc.dto.MemberDTO;
import org.korea.mvc.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MemberController {
	
	private MemberService service;

	public MemberController(MemberService service) {
		this.service = service;
	}
	
	@RequestMapping("/main") //url만 입력했을때 www.google.com
	public String main() {
		System.out.println("main()");
		return "main";
	}

	@RequestMapping("/login_action.do")
	public String loginAction(HttpServletRequest request, HttpSession session) {
		System.out.println("loginAction()");
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		System.out.println(id + pass);
		MemberDTO dto = service.loginMember(id,pass);
		if(dto == null) {
			return "main";
		}
		session.setAttribute("id", dto.getId());
		session.setAttribute("name", dto.getName());
		session.setAttribute("grade", dto.getGrade());
		session.setAttribute("grade_name", dto.getGrade_name());
		session.setAttribute("login", true);
		
		return "main";
	}
	
	@RequestMapping("/ajax.do")
	public String ajax(HttpServletResponse response) {
		String date = Calendar.getInstance().getTime().toString();
		System.out.println(date);
		try {
			response.getWriter().write(date);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
