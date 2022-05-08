package com.infiniteskills.maven;

import org.apache.commons.lang3.StringUtils;

public class Application {

	public static void main(String[] args) {
		
		String test = "test";
		
		System.out.println(isNumeric(test));
	}

	private static Boolean isNumeric(String test) {
		return StringUtils.isNumeric(test);
	}
}
