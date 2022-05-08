package com.luv2code.springdemo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Country {
	private Map<String, String> countryMap;
	
	public Country() {
		countryMap = new HashMap<String, String>();
		countryMap.put("IN", "india");
		countryMap.put("AU", "Australia");
		countryMap.put("US", "US");
	}

	public Map<String, String> getCountryMap() {
		return countryMap;
	}

	public void setCountryMap(Map<String, String> countryMap) {
		this.countryMap = countryMap;
	}
	
	
	
	
	
}
