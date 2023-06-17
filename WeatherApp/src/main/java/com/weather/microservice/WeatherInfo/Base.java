package com.weather.microservice.WeatherInfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Base {

	private String base;

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}
	
	
	
}
