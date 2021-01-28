package org.korea.mvc.service;

import java.util.ArrayList;

import org.korea.mvc.dao.EmployeeDAO;
import org.korea.mvc.dto.EmployeeDTO;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

	private EmployeeDAO dao;

	public EmployeeService(EmployeeDAO dao) {
		this.dao = dao;
	}

	public ArrayList<EmployeeDTO> searchAllEmployee() {
		// TODO Auto-generated method stub
		return dao.serachAllEmployee();
	}
	
	
	
}
