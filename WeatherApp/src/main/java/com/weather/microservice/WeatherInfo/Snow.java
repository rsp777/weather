package com.weather.microservice.WeatherInfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Snow {

	private double oneHourSnow;
	private double threeHourSnow;
	/**
	 * @return the oneHourSnow
	 */
	public double getOneHourSnow() {
		return oneHourSnow;
	}
	/**
	 * @param oneHourSnow the oneHourSnow to set
	 */
	public void setOneHourSnow(double oneHourSnow) {
		this.oneHourSnow = oneHourSnow;
	}
	/**
	 * @return the threeHourSnow
	 */
	public double getThreeHourSnow() {
		return threeHourSnow;
	}
	/**
	 * @param threeHourSnow the threeHourSnow to set
	 */
	public void setThreeHourSnow(double threeHourSnow) {
		this.threeHourSnow = threeHourSnow;
	}
	
}
