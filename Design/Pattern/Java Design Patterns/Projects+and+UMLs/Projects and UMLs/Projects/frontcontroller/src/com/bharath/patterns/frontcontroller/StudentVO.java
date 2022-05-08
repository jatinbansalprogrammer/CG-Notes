package com.bharath.patterns.frontcontroller;

public class StudentVO {

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name;

	public StudentVO(int id, String name) {
		this.id = id;
		this.name = name;
	}

}
