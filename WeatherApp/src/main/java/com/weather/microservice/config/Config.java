package com.weather.microservice.config;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.weather.microservice.WeatherApplication;
import com.weather.microservice.WeatherService.WeatherService;

@Component
@EnableAutoConfiguration
public class Config { 
    private final Logger logger = Logger.getLogger(Config.class.getName());

     @Value("${api.key}")
    private String key;
     
     @Value("${api.url}") 
    private String url;

    // public static void main(String[] args) {
    //     SpringApplication.run(Config.class, args);
    //     Config c = new Config();
        
    //     System.out.println("KEY : " + c.getKey());
    // }
    // @Autowired
    // public Config(@Value("${api.url}") String apiUrl,@Value("${api.key}") String
    // apiKey ){
    // this.apiKey = apiKey;
    // this.apiUrl = apiUrl;
    // }

    public String getKey() {
        logger.info("Getter Key : "+key);
        return key;
    }


    public void setKey(String key) {
        logger.info("Setter Key : "+key);
        this.key = key;
    }

    public String getUrl() {
        logger.info("Getter url : "+url);
        return url;
    }


    public void setUrl(String url) {
        logger.info("Getter url : "+url);
        this.url = url;
    }

}
