package org.korea.mvc.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.korea.mvc.dto.EmployeeDTO;
import org.korea.mvc.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmployeeController {
	private EmployeeService service;
	
	public EmployeeController(EmployeeService service) {
		this.service=service;
	}
	
	@RequestMapping("/main_emp") //url만 입력했을때 www.google.com
	public String main() {
		return "main_emp";
	}
	
	@RequestMapping("/search_all_employee.do")
	public String searchAllEmployeeController(HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		String result = "";
		ArrayList<EmployeeDTO> list =  service.searchAllEmployee();
		
		JSONArray arr = new JSONArray(list);
		result = arr.toString();
		System.out.println(result);
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
