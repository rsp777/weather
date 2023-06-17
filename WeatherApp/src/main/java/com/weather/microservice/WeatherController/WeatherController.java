package com.weather.microservice.WeatherController;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.ClientProtocolException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.weather.microservice.WeatherInfo.WeatherInfo;
import com.weather.microservice.WeatherInfo.Wind;
import com.weather.microservice.WeatherService.WeatherService;
import com.weather.microservice.exceptions.CityNotFoundException;
import com.weather.microservice.exceptions.EmptySpaceException;

@RestController
@RequestMapping(value = "/weather",method = RequestMethod.GET)
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

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/{city}")
	public WeatherInfo getWeather(@PathVariable String city) throws IOException, EmptySpaceException, CityNotFoundException {
		WeatherInfo weatherInfo = weatherService.getWeatherInfo(city);
		
		if (weatherInfo.getCod().equals("404")) {
			logger.info("404 Not Found : " + city);
			throw new CityNotFoundException(city);
		}
		
		else {
				return weatherInfo;
		}
//		return weatherInfo;
	}



	// @GetMapping("/wind/{city}")
	// public Wind getWind(@PathVariable String city) throws ClientProtocolException, IOException {
	// 	logger.info("Inside getWind : " + city);
	// 	return weatherService.getWind(city);
	// }
}
