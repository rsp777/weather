package com.weather.microservice.exceptions;

public class EmptySpaceException extends Exception{

	private String city;	
	public EmptySpaceException(String city) {
		this.city = city;
	}
	public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Bad Data Space found : "+getCity();
	}

//	@Override
//	public String getMessage() {
//		// TODO Auto-generated method stub
//		return super.getMessage();
//	}
	
	
	
	

	
	
}
