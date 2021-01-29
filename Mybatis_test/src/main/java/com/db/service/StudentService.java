package com.db.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.db.dto.StudentDTO;
import com.db.mapper.StudentMapper;

@Service
public class StudentService {
	private StudentMapper mapper;

	public StudentService(StudentMapper mapper) {
		this.mapper = mapper;
	}
	
	public List<StudentDTO> searchAllStudent() {
		return mapper.searchAllStudent();
	}

	public void insertStudent(StudentDTO studentDTO) throws Exception {
		int count = mapper.insertStudent(studentDTO);
		System.out.println(""+count);
		if(count == 0) {
			throw new Exception("SQL EXCEPTION");
		}
		
	}

	public void updateStudent(StudentDTO studentDTO) throws Exception {
		int count = mapper.updateStudent(studentDTO);
		if(count == 0) {
			throw new Exception("SQL EXCEPTION");
		}
	}

	public void deleteStudent(String sno) throws Exception {
		int count = mapper.deleteStudent(sno);
		if(count == 0) {
			throw new Exception("SQL EXCEPTION");
		}
		
	}
	
}
