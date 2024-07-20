package com.openweather.demo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Clase que servira para acceder al application.properties y obtener los
 * valores necesarios
 * 
 * @return
 */
@Configuration
public class OpenWeatherConfiguration {

	@Value("${openweather.api.key}")
	private String apiKey;

	@Value("${openweather.api.url}")
	private String apiUrl;

	public String getApiKey() {
		return apiKey;
	}

	public String getApiUrl() {
		return apiUrl;
	}
}
