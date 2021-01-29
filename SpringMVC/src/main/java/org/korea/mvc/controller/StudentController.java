package org.korea.mvc.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.korea.mvc.dto.StudentDTO;
import org.korea.mvc.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {

	private StudentService service;

	public StudentController(StudentService service) {
		this.service = service;
	}
	
	@RequestMapping("/main_std")
	public String MainStudent(HttpServletRequest request) {
		searchAllStudentController(request);
		return null;
	}
	
	@RequestMapping("/search_all_student.do")
	public String searchAllStudentController(HttpServletRequest request) {
		System.out.println("searchAllStudent");
		String result="";
		ArrayList<StudentDTO> list =  service.searchAllStudent();
		request.setAttribute("list", list);
		return "main_std";
	}
}
