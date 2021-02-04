package student.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import student.dto.StudentDTO;

@Mapper
public interface StudentMapper {

	List<StudentDTO> searchAllStudent(HashMap<String, Object> map);

	int insertLog(HashMap<String, Object> map);

	
}
