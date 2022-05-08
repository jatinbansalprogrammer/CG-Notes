package com.luv2code.springdemo;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ConfigFile {
	@Bean
	@Scope(value=ConfigurableBeanFactory.SCOPE_SINGLETON)
	FortuneService getFortuneService() {
		return new HappyFortuneService();
	}

	@Bean
	public Coach getBaseballCoach() {
		return new BaseballCoach(getFortuneService());
	}

	@Bean
	public Coach getTrackCoach() {
		return new TrackCoach(getFortuneService());
	}

	@Bean
	public BeanPostProcessorByMe getBeanPostProcessorByMe() {
		return new BeanPostProcessorByMe();
	}
}
