package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dao.EmployeeDAO;
import dto.EmployeeDTO;

class EmployeeDTOTest {
	
	@BeforeAll
	static void testUp() {
		System.out.println("테스트 맨처음 수행할 곳");
	}
	@AfterAll
	static void done() {
		System.out.println("테스트 맨마지막 수행할 곳");
	}

	@BeforeEach
	void init() throws Exception {
		System.out.println("테스트 작업전 수행");
	}

	@DisplayName("사원검색(직급) 테스트")
	@Test
	void testSearchEmployee_position() {
		System.out.println("testSearchEmployee_position");
		String result = EmployeeDAO.getInstance().searchEmployee_position(5);
		if(result.length() == 2) {
			fail("데이터가 없습니다");//작업 실패시 나타날 행동
		}
		else {
			System.out.println(result);
		}
	}

	@DisplayName("사원검색(하위연봉)")
	@Test
	void testSearchEmployee_lowSalary() {
		System.out.println("testSearchEmployee_lowSalary");
		String result = EmployeeDAO.getInstance().searchEmployee_lowSalary(5);
		if(result.length()<3) {
			fail("데이터가 없습니다");
		}
		else {
			System.out.println(result);
		}
	}
	
	@DisplayName("사원검색(사원번호)")
	@Test
	void testSearchEmployee_eno() {
		EmployeeDTO dto = new EmployeeDTO("TQ98", "강병헌", "영업", "과장",6200);
		//eqauls 비교
		assertEquals(dto, EmployeeDAO.getInstance().searchEmployee_eno("TQ98"));
		
//		EmployeeDTO dto = EmployeeDAO.getInstance().searchEmployee_eno("TQ98");
//		// == 비교
//		assertSame(dto, EmployeeDAO.getInstance().searchEmployee_eno("TQ98"));
	}
	
	@DisplayName("빈리스트 확인 테스트")
	@Test
	void testEmptyEmployeeList() {
		assertTrue(!EmployeeDAO.getInstance().searchEmployeeAll().isEmpty());
	}
	
	@AfterEach
	void end() {
		System.out.println("테스트 작업 후 실행");
	}
	

}
