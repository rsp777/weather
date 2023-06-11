package com.weather.microservice.WeatherController;

import java.io.IOException;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weather.microservice.WeatherInfo.WeatherInfo;
import com.weather.microservice.WeatherService.WeatherService;

@RestController
public class WeatherController {

	private final WeatherService weatherService;
	private final Logger logger = Logger.getLogger(WeatherService.class.getName());

	public WeatherController() {

		weatherService = new WeatherService();

	}

	@GetMapping("/weather/{city}") 
//	@RequestMapping(value= "/weather",
//			params = {"city"},method = RequestMethod.GET)
	public WeatherInfo getWeather(@PathVariable String city) throws IOException {
		logger.info("City : "+city);
		System.out.println("City : "+city);
		return weatherService.getWeatherInfo(city);
	}

}
