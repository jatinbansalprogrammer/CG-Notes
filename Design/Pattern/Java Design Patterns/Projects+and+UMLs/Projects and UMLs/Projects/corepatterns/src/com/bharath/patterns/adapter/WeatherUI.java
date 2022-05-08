package com.bharath.patterns.adapter;

public class WeatherUI {

	public void showTemperature(int zipcode) {
		WeatherAdapter adapter = new WeatherAdapter();
		System.out.println(adapter.findTemperature(zipcode));

	}

	public static void main(String[] args) {
		WeatherUI ui = new WeatherUI();
		ui.showTemperature(19406);
	}

}
