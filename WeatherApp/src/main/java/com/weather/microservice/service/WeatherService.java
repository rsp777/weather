package com.weather.microservice.service;

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
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.microservice.weatherInfo.WeatherInfo;

@Service
public class WeatherService {

	private final Logger logger = Logger.getLogger(WeatherService.class.getName());

//	@Autowired
//	private Environment environment;

	private static final String API_KEY = "f777fa895ebe5cb38334176de19e5693";
	private static final String WEATHER_API_URL = "http://api.openweathermap.org/data/2.5/weather";
	private static final String PARAMS = "%s?q=%s&appid=%s&units=metric&lang=en";

	private WeatherInfo weatherInfo;
	private final HttpClient httpClient;
	private final ObjectMapper objectMapper;

	public String encodedCity;

	public WeatherService() {
		httpClient = HttpClients.createDefault();
		objectMapper = new ObjectMapper();

	}

	public WeatherInfo getWeatherInfo(String city) throws IOException, ClientProtocolException {
		if (city != null) {
			encodedCity = encodedCity(city);
			String jsonWeatherResponse = jsonWeatherResponse(PARAMS, WEATHER_API_URL, encodedCity, API_KEY);
			objectMapper.setSerializationInclusion(Include.NON_NULL);
			weatherInfo = objectMapper.readValue(jsonWeatherResponse, WeatherInfo.class);
			logger.info("Weather fetched for " + weatherInfo.getName());
			logger.info("Status : " + weatherInfo.getCod());
			return weatherInfo;
		} else {
			logger.info(weatherInfo.getName());
		}
		return weatherInfo;
	}

	public String jsonWeatherResponse(String PARAMS, String WEATHER_API_URL, String city, String API_KEY)
			throws ClientProtocolException, IOException {
		String url = String.format(PARAMS, WEATHER_API_URL, city, API_KEY);
//		logger.info("Rest URL : " + url);
		HttpGet request = new HttpGet(url);
		HttpResponse response = httpClient.execute(request);
		HttpEntity entity = response.getEntity();
		String json = EntityUtils.toString(entity);
		logger.info("response inside " + getClass() + ": " + response.getStatusLine().getStatusCode());
		return json;
	}

		public String encodedCity(String city) {
//		if (city.matches(".*\\s.*") && city != null) {
		if (city != null) {
			logger.info("City with space : " + city);
			String trimmedCity = city.trim();
			encodedCity = URLEncoder.encode(trimmedCity, StandardCharsets.UTF_8);
//			Pattern p = Pattern.compile("\\s+");
//			Matcher mat = p.matcher(city);
//			encodedCity = mat.replaceAll("");
			logger.info("City with no space : " + encodedCity);
			return encodedCity;
		}
		return city;

	}

}
