package com.bharath.patterns.flyweight;

public class Circle extends Shape {

	private String label;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Circle() {
		label = "Cicle";
	}

	@Override
	public void draw(int radius, String fillColor, String lineColor) {
		System.out.println("Drawing a " + label + "  with radius " + radius + " Fill color " + fillColor
				+ " Line Color " + lineColor);
	}

}
