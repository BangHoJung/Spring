package org.korea.mvc.app;

import org.korea.mvc.dao.EmployeeDAO;
import org.korea.mvc.dao.MemberDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import config.DBManager;

@Configuration
public class AppContext {
	
	@Bean
	public DBManager getManager() {
		return new DBManager();
	}
	
	@Bean
	public MemberDAO getMember() {
		return new MemberDAO(getManager());
	}
	
	@Bean
	public EmployeeDAO getEmployee() {
		return new EmployeeDAO(getManager());
	}
}
