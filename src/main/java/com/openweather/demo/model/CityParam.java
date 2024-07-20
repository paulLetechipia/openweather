package com.openweather.demo.model;

import javax.validation.constraints.NotEmpty;

public class CityParam {
	
	@NotEmpty(message = "City name is required")
	private String city;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
