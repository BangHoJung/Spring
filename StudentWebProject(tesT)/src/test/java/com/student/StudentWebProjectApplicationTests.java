package com.student;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class StudentWebProjectApplicationTests {
	
	@Autowired
	StudentMapper mapper;
	
	
	@BeforeAll
	static void start() {
		System.out.println("start");
	}

	@Test
	void contextLoads() {
		System.out.println(mapper.selectAllStudent().toString());
	}
	
	@DisplayName("학생정보 검색 테스트")
	@Test
	void testA() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("mode", "name");
		map.put("search", "수");
		List<StudentDTO> list =  mapper.selectStudentMode(map);
		if(list.size()==0) {
			fail("해당 학생이 없습니다.");
		}
	}

}
