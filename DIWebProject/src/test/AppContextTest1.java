package test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;

import config.AppContext;
import config.DBManager;
import dao.EmployeeDAO;
import dto.EmployeeDTO;

class AppContextTest1 {
	
	EmployeeDTO dto1 = AppContext.getContext().getBean(EmployeeDTO.class);
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}
	
	@DisplayName("DTO객체 하나 생성")
	@Test
	void getDTOTest() {
		//EmployeeDAO dao = AppContext.getContext().getBean(EmployeeDAO.class);
		for(int i=0;i<10000;i++) {
			dto1.init("KK"+i, "하하", "인사", 1,0);
			System.out.println(dto1.toString());
			//dao.insertEmployee(AppContext.getContext().getBean(EmployeeDTO.class));
		}
//		ArrayList<EmployeeDTO> list =  dao.searchEmployeeAll();
//		for(int i=0;i<list.size();i++) {
//			System.out.println(list.get(i));
//		}
		//fail("Not yet implemented");
	}

	@DisplayName("DTO객체 모두 생성")
	@Test
	void createDTOTest() {
		//EmployeeDAO dao = AppContext.getContext().getBean(EmployeeDAO.class);
		for(int i=0;i<10000;i++) {
			//dao.insertEmployee(new EmployeeDTO("KK"+i, "하하", "인사", 1, 0));
			EmployeeDTO dto = new EmployeeDTO("KK"+i, "하하", "인사", 1, 0);
			System.out.println(dto.toString());
		}
//		ArrayList<EmployeeDTO> list =  dao.searchEmployeeAll();
//		for(int i=0;i<list.size();i++) {
//			System.out.println(list.get(i));
//		}
		//fail("Not yet implemented");
	}
	
	
	
	
	
	@AfterEach
	void end() {
	}

}
