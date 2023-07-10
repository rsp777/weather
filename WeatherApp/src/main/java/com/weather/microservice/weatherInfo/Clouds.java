package com.weather.microservice.weatherInfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Clouds {

	private Integer all;

	public Integer getAll() {
		return all;
	}

	public void setAll(Integer all) {
		this.all = all;
	}

	@Override
	public String toString() {
		return "Clouds [all=" + all + "]";
	}
	
	
}
