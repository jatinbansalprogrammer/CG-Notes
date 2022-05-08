package com.luv2code.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloSpringApp {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigFile.class);
		FortuneService fortuneService1 = context.getBean("getBaseballCoach", BaseballCoach.class).getFortuneService();
		FortuneService fortuneService2 = context.getBean("getTrackCoach", TrackCoach.class).getFortuneService();
		System.out.println(fortuneService1 == fortuneService2);
	}

}
