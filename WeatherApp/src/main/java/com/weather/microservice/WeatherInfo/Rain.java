package com.weather.microservice.WeatherInfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Rain {

	private double oneHourRain;
	private double threeHourRain;
	/**
	 * @return the oneHourRain
	 */
	public double getOneHourRain() {
		return oneHourRain;
	}
	/**
	 * @param oneHourRain the oneHourRain to set
	 */
	public void setOneHourRain(double oneHourRain) {
		this.oneHourRain = oneHourRain;
	}
	/**
	 * @return the threeHourRain
	 */
	public double getThreeHourRain() {
		return threeHourRain;
	}
	/**
	 * @param threeHourRain the threeHourRain to set
	 */
	public void setThreeHourRain(double threeHourRain) {
		this.threeHourRain = threeHourRain;
	}
	
	
}
