package com.weather.microservice.WeatherController;

import java.io.IOException;
import java.util.logging.Logger;

import org.apache.http.client.ClientProtocolException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weather.microservice.WeatherInfo.WeatherInfo;
import com.weather.microservice.WeatherInfo.Wind;
import com.weather.microservice.WeatherService.WeatherService;

@RestController
public class WeatherController {

	private final WeatherService weatherService;
	private final Logger logger = Logger.getLogger(WeatherService.class.getName());

	public WeatherController() {

		weatherService = new WeatherService();

	}
	
	
	@GetMapping("/")
	public String welcome() throws IOException {
		String welcome = "<b>Welcome to the Weather App backed by Springboot</b>";
		logger.info(welcome);
		return welcome;
	}
	
//	@CrossOrigin(origins = "http://127.0.0.1:8080")
	@CrossOrigin(origins = "http://192.168.1.5:8080")
	@GetMapping("/weather/{city}")
	public WeatherInfo getWeather(@PathVariable String city) throws IOException {
		logger.info("City : " + city);
		
		return weatherService.getWeatherInfo(city);
	}
	
	
	@GetMapping("/weather/wind/{city}")
	public Wind getWind(@PathVariable String city) throws ClientProtocolException, IOException {
		logger.info("Inside getWind : " + city);
		return weatherService.getWind(city);
	}	
}
