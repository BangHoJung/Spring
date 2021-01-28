package org.korea.mvc.service;

import java.util.ArrayList;

import org.korea.mvc.dao.StudentDAO;
import org.korea.mvc.dto.StudentDTO;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	private StudentDAO dao;

	public StudentService(StudentDAO dao) {
		this.dao = dao;
	}

	public ArrayList<StudentDTO> searchAllStudent() {
		return dao.searchAllStudent();
	}
	
	
}
