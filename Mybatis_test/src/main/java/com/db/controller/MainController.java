package com.db.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.db.dto.StudentDTO;
import com.db.service.StudentService;

@Controller
public class MainController {
	
	private StudentService service;

	public MainController(StudentService service) {
		this.service = service;
	}

	@RequestMapping("/main.do")
	public String main(HttpServletRequest request) {
		List<StudentDTO> list = service.searchAllStudent();
		request.setAttribute("list", list);
		return "main";
	}
	
	@RequestMapping("/insert_student.do")
	public String insertStudent(HttpServletRequest request, HttpServletResponse response) {
		String sno = request.getParameter("sno");
		String name = request.getParameter("name");
		int major = 0;
		double score = 0;
		try {
			major = Integer.parseInt(request.getParameter("major"));
			score = Double.parseDouble(request.getParameter("score"));
		} catch(Exception e) {
			try {
				response.getWriter().write("<script>alert('NumberFormatException');history.back();</script>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		
		try {
			service.insertStudent(new StudentDTO(sno, name, major, score));
		} catch (Exception e) {
			try {
				response.getWriter().write("<script>alert('" + e.getMessage() + "');history.back();</script>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return main(request);
		
	}
	
	@RequestMapping("alter_student.do")
	public String alterStudent(HttpServletRequest request,HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		String type = request.getParameter("type");
		String sno = request.getParameter("sno");
		if(type.equals("delete")) {
			try {
				service.deleteStudent(sno);
			} catch (Exception e) {
				try {
					response.getWriter().write("<script>alert('" + e.getMessage() + "');history.back();</script>");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		}
		else {
			String name = request.getParameter("name");
			int major = 0;
			double score = 0;
			try {
				major = Integer.parseInt(request.getParameter("major"));
				score = Double.parseDouble(request.getParameter("score"));
			} catch(Exception e) {
				try {
					response.getWriter().write("<script>alert('NumberFormatException');history.back();</script>");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			try {
				service.updateStudent(new StudentDTO(sno, name, major, score));
			} catch (Exception e) {
				try {
					response.getWriter().write("<script>alert('" + e.getMessage() + "');history.back();</script>");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		}
		return main(request);
	}
}
