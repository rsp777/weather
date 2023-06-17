package com.weather.microservice.exceptions;

public class CityNotFoundException extends Exception{

    // private String city;
    public CityNotFoundException(String city) {
		 super("City not found: " + city);
	}

	// public String getCity() {
    //     return city;
    // }

    // public void setCity(String city) {
    //     this.city = city;
    // }

    // @Override
    // public synchronized Throwable getCause() {
    //     // TODO Auto-generated method stub
    //     return super.getCause();
    // }

    // @Override
    // public String getMessage() {
    //     // TODO Auto-generated method stub
    //     return "City Not found : "+getCity();
    // }

    // @Override
    // public StackTraceElement[] getStackTrace() {
    //     // TODO Auto-generated method stub
    //     return super.getStackTrace();
    // }
    

    
}
