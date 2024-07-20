package com.openweather.demo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.openweather.demo.model.OpenWeatherResponse;
import com.openweather.demo.model.Sys;
import com.openweather.demo.model.Weather;
import com.openweather.demo.repository.CloudsRepository;
import com.openweather.demo.repository.CoordRepository;
import com.openweather.demo.repository.MainRepository;
import com.openweather.demo.repository.OpenWeatherResponseRepository;
import com.openweather.demo.repository.SysRepository;
import com.openweather.demo.repository.WeatherRepository;
import com.openweather.demo.repository.WindRepository;

@Component
public class OpenWeatherResponseDao{
	
	@Autowired
	private SysRepository sysRepository;
	@Autowired
	private WeatherRepository weatherRepository;
	@Autowired
	private WindRepository windRepository;
	@Autowired
	private CoordRepository coordRepository;
	@Autowired
	private CloudsRepository cloudsRepository;
	@Autowired
	private MainRepository mainRepository;
	@Autowired
	private OpenWeatherResponseRepository openWeatherResponseRepository;

	public OpenWeatherResponse saveWeatherResponse(OpenWeatherResponse openWeatherResponse) {
		OpenWeatherResponse weatherResponse = new OpenWeatherResponse(openWeatherResponse);
		coordRepository.save(weatherResponse.getCoord());
		List<Weather> weatherList = new ArrayList<>();
		for (Weather w : weatherResponse.getWeather()) {
			Weather updatedWeather = new Weather(w);
			weatherList.add(updatedWeather);
		}
		weatherResponse.setWeather(weatherList);
		weatherRepository.saveAll(weatherResponse.getWeather());
		mainRepository.save(weatherResponse.getMain());
		windRepository.save(weatherResponse.getWind());
		cloudsRepository.save(weatherResponse.getClouds());
		Sys sys = new Sys(weatherResponse.getSys());
		weatherResponse.setSys(sys);
		sysRepository.save(weatherResponse.getSys());
		return openWeatherResponseRepository.save(weatherResponse);
	}
}
