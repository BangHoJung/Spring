package config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dao.EmployeeDAO;
import dto.EmployeeDTO;

@Configuration
public class AppContext {
	private static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppContext.class);

	public static AnnotationConfigApplicationContext getContext() {
		return context;
	}
	
	@Bean
	public DBManager getManager() {
		DBManager manager = new DBManager();
		return manager;
	}
	
	@Bean
	public EmployeeDAO getDAO() {
		EmployeeDAO dao = new EmployeeDAO(getManager());
		return dao;
	}
	
	@Bean
	public EmployeeDTO getDTO() {
		EmployeeDTO dto = new EmployeeDTO();
		return dto;
	}
}
