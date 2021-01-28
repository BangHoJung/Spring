package org.korea.mvc.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

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
	public String MainStudent(HttpSession session) {
		System.out.println("searchAllStudent");
		String result="";
		ArrayList<StudentDTO> list =  service.searchAllStudent();
		session.setAttribute("list", list);
		return "main_std";
	}
	
//	@RequestMapping("/search_all_student.do")
//	public String searchAllStudentController(HttpSession session) {
//		System.out.println("searchAllStudent");
//		String result="";
//		ArrayList<StudentDTO> list =  service.searchAllStudent();
//		session.setAttribute("list", list);
//		return "main_std";
//	}
}
