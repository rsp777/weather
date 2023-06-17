package com.weather.microservice.WeatherService;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.microservice.WeatherInfo.WeatherInfo;
import com.weather.microservice.WeatherInfo.Wind;
import com.weather.microservice.config.Config;
import com.weather.microservice.exceptions.CityNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class WeatherService {

	private final Logger logger = Logger.getLogger(WeatherService.class.getName());

	private static final String API_KEY = "f777fa895ebe5cb38334176de19e5693";
	private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather";
	private final String URL = "http://localhost:8080/weather/";

	private final HttpClient httpClient;
	private final ObjectMapper objectMapper;

	public String encodedCity;
	@Autowired
	private HttpServletRequest requestt;

	public WeatherService() {
		httpClient = HttpClients.createDefault();
		objectMapper = new ObjectMapper();

	}

	public WeatherInfo getWeatherInfo(String city) throws IOException, ClientProtocolException, CityNotFoundException {

		WeatherInfo weatherInfo;

		if (city.matches(".*\\s.*") && city != null) {
			encodedCity = encodedCity(city);
			String jsonResponse = jsonResponse(encodedCity);
			weatherInfo = objectMapper.readValue(jsonResponse, WeatherInfo.class);
			logger.info("Weather info fetched for " + city.toUpperCase());
			logger.info("Status : " + weatherInfo.getCod());
			return weatherInfo;
		} 
		else {

			String jsonResponse = jsonResponse(city);
			weatherInfo = objectMapper.readValue(jsonResponse, WeatherInfo.class);
			
			return weatherInfo;

		}

	}

	public WeatherInfo asd() {
		return null;
	}

	public String jsonResponse(String city) throws ClientProtocolException, IOException {
		String url = String.format("%s?q=%s&appid=%s&units=metric&lang=en", API_URL, city, API_KEY);
		logger.info("API_KEY : " + API_KEY);
		logger.info("API_URL : " + API_URL);
		logger.info("Rest URL : " + url);

		HttpGet request = new HttpGet(url);
		HttpResponse response = httpClient.execute(request);
		HttpEntity entity = response.getEntity();
		String json = EntityUtils.toString(entity);
		logger.info("json : " + json);
		logger.info("response inside " + getClass() + ": " + response.getStatusLine());
		return json;
	}

	public String encodedCity(String city) {
		if (city.matches(".*\\s.*") && city != null) {
			logger.info("City with space : " + city);
			Pattern p = Pattern.compile("[\\s]");
			Matcher mat = p.matcher(city);
			encodedCity = mat.replaceAll("");
			logger.info("City with no space : " + encodedCity);

			return encodedCity;
		}
		return city;

	}

	// public Wind getWind(String city) throws ClientProtocolException, IOException
	// {
	// // RestTemplate restTemplate = new RestTemplate();
	// // restTemplate.getForObject(URL, null)
	// String url = String.format(URL, API_KEY).concat(city);
	// // String.for
	// // logger.info("Wind City : "+city);
	// // logger.info("Wind URL : "+url);
	// HttpGet request = new HttpGet(url);
	// HttpResponse response = httpClient.execute(request);
	// HttpEntity entity = response.getEntity();
	// String json = EntityUtils.toString(entity);
	// // logger.info("Response from getWeatherInfo ; "+json);
	// WeatherInfo weatherInfo = new WeatherInfo();
	// weatherInfo = objectMapper.readValue(json, WeatherInfo.class);
	// // logger.info("Wind : "+weatherInfo.getWind().toString());
	// logger.info("response : " + response);
	// return weatherInfo.getWind();

	// }

}
