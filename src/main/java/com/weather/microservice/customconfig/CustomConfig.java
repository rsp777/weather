//package com.weather.microservice.customconfig;
//
//import java.util.logging.Logger;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//
//import com.weather.microservice.WeatherApplication;
//import com.weather.microservice.WeatherService.WeatherService;
//
//@Configuration
//@SpringBootApplication
//@PropertySource(value = "classpath:/application.properties")
//public class CustomConfig {
//
//	
//	private final Logger logger = Logger.getLogger(CustomConfig.class.getName());
//	
//	@Value("$weather.myweatherkey")
//	private String weatherkey; 
//	
//
////	public String getProperty(String keyName) {
////		logger.info("weather key : "+env.getProperty(keyName));
////		return env.getProperty(keyName);
////	}
////	CustomConfig configg = new CustomConfig();
//	
//	public String getWeatherkey() {
//		return weatherkey;
//	}
//
//
//	public void setWeatherkey(String weatherkey) {
//		this.weatherkey = weatherkey;
//	}
//
//
//	public static void main(String[] args) {
//		SpringApplication.run(CustomConfig.class, args);
//		CustomConfig c = new CustomConfig();
//		System.out.println(" WeatherKey : "+c.weatherkey);
//	}
//}
