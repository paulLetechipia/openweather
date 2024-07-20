package com.openweather.demo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openweather.demo.dto.ResponseDTO;
import com.openweather.demo.model.CityParam;
import com.openweather.demo.service.OpenWeatherServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.validation.Valid;

@RestController
@Validated
public class OpenWeatherController {

	private static final Logger LOGGER = Logger.getLogger(OpenWeatherController.class.getName());

	@Autowired
	OpenWeatherServiceImpl openWeatherService;

	@PostMapping("/weatherCityPost")
	@Operation(summary = "Obtiene el clima", description = "A traves de este endpoint post se consulta clima de una ciudad en el api de openWeather.")
	@ApiResponse(responseCode = "200", description = "OK, estatus correcto")
	@ApiResponse(responseCode = "400", description = "Bad Request, La ciudad a buscar no puede ser vacía")
	@ApiResponse(responseCode = "404", description = "Not Found, No se encontró la ciudad")
	@ApiResponse(responseCode = "500", description = "Internal Server Error, Problema interno del servidor")
	public ResponseDTO getWeatherCity(@Valid @RequestBody CityParam cityParam) {
		ResponseDTO response = openWeatherService.getWeather(cityParam.getCity());
		return response;
	}

	@GetMapping("/weatherCity")
	@Operation(summary = "Obtiene el clima", description = "A traves de este endpoint get se consulta clima de una ciudad en el api de openWeather.")
	@ApiResponse(responseCode = "200", description = "OK, estatus correcto")
	@ApiResponse(responseCode = "400", description = "Bad Request, La ciudad a buscar no puede ser vacía")
	@ApiResponse(responseCode = "404", description = "Not Found, No se encontró la ciudad")
	@ApiResponse(responseCode = "500", description = "Internal Server Error, Problema interno del servidor")
	public ResponseDTO getWeatherCity(
			@Parameter(description = "Nombre de la ciudad a buscar.", required = true) @RequestParam String city) {
		LOGGER.info("getWeatherCity = " + city);
		ResponseDTO response = openWeatherService.getWeather(city);
		return response;
	}

	@GetMapping("/history")
	@Operation(summary = "Obtiene los ultimos 10 registros consultados.", description = "A traves de este endpoint get se consulta el historial de los 10 ultimos registros consultados en el api de openWeather.")
	@ApiResponse(responseCode = "200", description = "OK, estatus correcto")
	@ApiResponse(responseCode = "500", description = "Internal Server Error, Problema interno del servidor")
	public ResponseDTO getHistory() {
		ResponseDTO response = openWeatherService.getHistory();
		return response;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
}
