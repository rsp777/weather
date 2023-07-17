package com.weather.microservice.Controller;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.weather.microservice.config.Config;
import com.weather.microservice.exceptions.CityNotFoundException;
import com.weather.microservice.service.WeatherService;
import com.weather.microservice.weatherInfo.WeatherInfo;

@RestController
@RequestMapping(value = "/weather", method = RequestMethod.GET)
// @CrossOrigin(origins = "*", allowedHeaders = "*")
public class WeatherController {

	@Autowired
	public Config  config;
	private WeatherService weatherService;
	private final Logger logger = Logger.getLogger(WeatherController.class.getName());
	private WeatherInfo weatherInfo;

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
	public WeatherInfo getWeather(@PathVariable String city)
			throws CityNotFoundException, ClientProtocolException, IOException {
		String weatherUrl = config.getUrl();
		String weatherKey = config.getKey();
		String weatherParams = config.getParams();
		weatherInfo = weatherService.getWeatherInfo(city,weatherUrl,weatherKey,weatherParams);
		logger.info("getWeatherInfo("+city+") : "+weatherInfo);
		
		if (weatherInfo.getCod().equals("404")) {
			logger.info("City Not Found");
			throw new CityNotFoundException("city not found : " + city);
		}

		else {
			return weatherInfo;
		}

	}
	
	

	@ExceptionHandler
	public ResponseEntity<WeatherResponse> notFoundResponseEntity(CityNotFoundException cnfe) {

		WeatherResponse weatherResponse = new WeatherResponse();
		weatherResponse.setCod(HttpStatus.NOT_FOUND.value());
		weatherResponse.setMessage(cnfe.getMessage());
		return new ResponseEntity<>(weatherResponse, HttpStatus.NOT_FOUND);

	}

}
