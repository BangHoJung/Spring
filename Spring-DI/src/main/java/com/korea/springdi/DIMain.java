package com.korea.springdi;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DIMain {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppContext.class);
		Greeting g1 = context.getBean("greeter",Greeting.class);
		Greeting g2 = context.getBean("greeter",Greeting.class);
		
		g1.printInfo();
		g2.printInfo();
		
		Person p1 = context.getBean("getPerson",Person.class);
		Person p2 = context.getBean("getPerson",Person.class);
		Person p3 = context.getBean(Person.class);
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);
		
	}

}
