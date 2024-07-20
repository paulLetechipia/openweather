package com.openweather.demo.repository;

import org.springframework.stereotype.Repository;

import com.openweather.demo.model.OpenWeatherResponse;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface OpenWeatherResponseRepository extends JpaRepository<OpenWeatherResponse, Long> {

	@Query(value = "SELECT TOP 10 * FROM open_weather_response ORDER BY id DESC", nativeQuery = true)
	List<OpenWeatherResponse> findLastTen();
	
	List<OpenWeatherResponse> findByNameOrderByIdDesc(String city);
	
}
