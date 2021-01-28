package com.korea.springdi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {
	
	@Bean
	public Greeting greeter() {
		Greeting g = new Greeting(1000, "di-test");
		return g;
	}
	
	@Bean
	public Person getPerson() {
		Person p = new Person("하하",(int)(Math.random()*20)+1);
		return p;
	}
}
