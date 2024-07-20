package com.openweather.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.openweather.demo.configuration.OpenWeatherConfiguration;
import com.openweather.demo.dao.OpenWeatherResponseDao;
import com.openweather.demo.dto.ResponseDTO;
import com.openweather.demo.model.OpenWeatherResponse;
import com.openweather.demo.repository.OpenWeatherResponseRepository;

@Service
public class OpenWeatherServiceImpl implements OpenWeatherService {

	@Autowired
	private OpenWeatherConfiguration openWeatherConfiguration;
	@Autowired
	private OpenWeatherResponseRepository openWeatherResponseRepository;
	@Autowired
	private OpenWeatherResponseDao openWeatherResponseDao;


	private RestTemplate restTemplate = new RestTemplate();
	
	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public ResponseDTO getWeather(String city) {

		if (null == city || city.isEmpty()) {
			return new ResponseDTO(HttpStatus.BAD_REQUEST.value(), "La ciudad a buscar no puede ser vacía.", null);
		}
		String url = String.format("%s?q=%s&appid=%s", openWeatherConfiguration.getApiUrl(), city,
				openWeatherConfiguration.getApiKey());
		OpenWeatherResponse openWeatherResponse = null;
		try {
			openWeatherResponse = restTemplate.getForObject(url, OpenWeatherResponse.class);
			openWeatherResponseDao.saveWeatherResponse(openWeatherResponse);
			return new ResponseDTO(HttpStatus.OK.value(), "", openWeatherResponse);
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			switch (e.getStatusCode().value()) {
			case 404:
				return new ResponseDTO(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), null);
			default:
				OpenWeatherResponse lastOpenWeatherResponse = getByCityName(city);
				if (null == lastOpenWeatherResponse) {
					return new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "El servicio no está disponible.",
							null);
				}
				return new ResponseDTO(HttpStatus.PARTIAL_CONTENT.value(),
						"No se pudieron recuperar los datos actualizados.", lastOpenWeatherResponse);
			}
		}

	}

	public ResponseDTO getHistory() {
		List<OpenWeatherResponse> lastOpenWeatherResponse = openWeatherResponseRepository.findLastTen();
		return new ResponseDTO(HttpStatus.OK.value(), "", lastOpenWeatherResponse);
	}

	private OpenWeatherResponse getByCityName(String city) {
		List<OpenWeatherResponse> openWeatherResponseList = openWeatherResponseRepository.findByNameOrderByIdDesc(city);
		if (!openWeatherResponseList.isEmpty()) {
			return openWeatherResponseList.get(0);
		} else {
			return null;
		}
	}

}
