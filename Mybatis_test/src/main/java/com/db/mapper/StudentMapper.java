package com.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.db.dto.StudentDTO;

@Mapper
public interface StudentMapper {

	public List<StudentDTO> searchAllStudent();

	public int insertStudent(StudentDTO studentDTO);

	public int updateStudent(StudentDTO studentDTO);

	public int deleteStudent(String sno);
}
