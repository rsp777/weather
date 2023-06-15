package com.weather.microservice.WeatherInfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Wind {

	private double speed;
	private int deg;
	private double gust;

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getDeg() {
		return deg;
	}

	public void setDeg(int deg) {
		this.deg = deg;
	}

	public double getGust() {
		return gust;
	}

	public void setGust(double gust) {
		this.gust = gust;
	}

	@Override
	public String toString() {
		return "Wind {speed=" + speed + ", deg=" + deg + ", gust=" + gust + "}";
	}

	
}
