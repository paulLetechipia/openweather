package com.openweather.demo.service;

import com.openweather.demo.dto.ResponseDTO;

public interface OpenWeatherService {

	public ResponseDTO getWeather(String city);

	public ResponseDTO getHistory();
}
