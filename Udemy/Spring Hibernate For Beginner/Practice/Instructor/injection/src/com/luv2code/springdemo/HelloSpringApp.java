package com.luv2code.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloSpringApp {

	public static void main(String[] args) {

		// load the spring configuration file
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationFile.class);
		
		System.out.println(context.getBean("properties"));;
		context.close();
	}

}







