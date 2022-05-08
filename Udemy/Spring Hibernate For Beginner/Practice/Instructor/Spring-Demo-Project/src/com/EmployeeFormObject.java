package com;

import org.springframework.beans.factory.annotation.Value;

public class EmployeeFormObject {
	private String name;
	private String eId;
	@Value("#{countryOptions}") 
	private String country;
	
	public EmployeeFormObject() {
		System.out.println("EmployeeFormObject()");
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String geteId() {
		return eId;
	}

	public void seteId(String eId) {
		this.eId = eId;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	
	
}
