package com.weather.microservice.Controller;

import java.io.IOException;
import java.util.logging.Logger;
import org.apache.http.client.ClientProtocolException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.weather.microservice.exceptions.CityNotFoundException;
import com.weather.microservice.service.WeatherService;
import com.weather.microservice.weatherInfo.WeatherInfo;

@RestController
@RequestMapping(value = "/forecast", method = RequestMethod.GET)
// @CrossOrigin(origins = "*", allowedHeaders = "*")
public class ForecastController {

	private final WeatherService weatherService;
	private final Logger logger = Logger.getLogger(ForecastController.class.getName());

	public ForecastController() {

		weatherService = new WeatherService();

	}

	@GetMapping("/")
	public String welcome() throws IOException {
		String welcome = "<b>Welcome to the Weather App backed by Springboot</b>";
		logger.info(welcome);
		return welcome;
	}

	@ExceptionHandler
	public ResponseEntity<WeatherResponse> notFoundResponseEntity(CityNotFoundException cnfe) {

		WeatherResponse weatherResponse = new WeatherResponse();
		weatherResponse.setCod(HttpStatus.NOT_FOUND.value());
		weatherResponse.setMessage(cnfe.getMessage());

		return new ResponseEntity<>(weatherResponse, HttpStatus.NOT_FOUND);

	}

}
