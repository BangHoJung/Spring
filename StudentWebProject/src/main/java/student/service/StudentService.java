package student.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import student.dto.StudentDTO;
import student.mapper.StudentMapper;

@Service
public class StudentService {

	StudentMapper mapper;

	public StudentService(StudentMapper mapper) {
		this.mapper = mapper;
	}

	public List<StudentDTO> searchAllStudent(String kind, String search) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("kind", kind);
		map.put("search", search);
		return mapper.searchAllStudent(map);
	}

	public int insertLog(String log_date, int error_code, String content) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("log_date", log_date);
		map.put("error_code", error_code);
		map.put("content", content);
		return mapper.insertLog(map);
	}
	
	
}
