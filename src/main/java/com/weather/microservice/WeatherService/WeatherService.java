package com.weather.microservice.WeatherService;

import java.io.IOException;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.bootstrap.HttpServer;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.microservice.WeatherInfo.Weather;
import com.weather.microservice.WeatherInfo.WeatherInfo;
import com.weather.microservice.WeatherInfo.Wind;

@Service

@PropertySource("classpath:application.properties") 
public class WeatherService {

	private final Logger logger = Logger.getLogger(WeatherService.class.getName());
	
//	@Value("$API_KEY")
	private static final String API_KEY = "f777fa895ebe5cb38334176de19e5693";
	private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather";
	private static final String URL = "http://localhost:8080/weather/";
	private final HttpClient httpClient;
	private final ObjectMapper objectMapper;

	public WeatherService() {
		httpClient = HttpClients.createDefault();
		objectMapper = new ObjectMapper();

	}
	
	
	public WeatherInfo getWeatherInfo(String city) throws IOException {
		String url = String.format("%s?q=%s&appid=%s&units=metric&lang=en", API_URL, city, API_KEY);
//		logger.info("Inside : " + getClass());
//		logger.info("API_KEY : " + API_KEY);
//		logger.info("API_URL : " + API_URL);
		logger.info("Rest URL : " + url);
		HttpGet request = new HttpGet(url);
		HttpResponse response = httpClient.execute(request);
		HttpEntity entity = response.getEntity();
		String json = EntityUtils.toString(entity);
        
		WeatherInfo weatherInfo = objectMapper.readValue(json, WeatherInfo.class);
		
		logger.info("Weather info fetched for " + city.toUpperCase());
		logger.info("response : "+response.getStatusLine());

		return weatherInfo;
	}
	
	public Wind getWind(String city) throws ClientProtocolException, IOException {
//		RestTemplate restTemplate = new RestTemplate();
//		restTemplate.getForObject(URL, null)
		String url = String.format(URL,API_KEY).concat(city);
//		String.for
//		logger.info("Wind City : "+city);
//		logger.info("Wind URL : "+url);
		HttpGet request = new HttpGet(url);
		HttpResponse response = httpClient.execute(request);
		HttpEntity entity = response.getEntity();
		String json = EntityUtils.toString(entity);
//		logger.info("Response from getWeatherInfo ; "+json);
		WeatherInfo weatherInfo = new WeatherInfo();
					weatherInfo = objectMapper.readValue(json, WeatherInfo.class);
//		logger.info("Wind : "+weatherInfo.getWind().toString());
		logger.info("response : "+response);
		 return weatherInfo.getWind();
		
	}
	

}
