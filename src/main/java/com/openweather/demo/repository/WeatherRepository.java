package com.openweather.demo.repository;

import org.springframework.stereotype.Repository;

import com.openweather.demo.model.Weather;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Integer> {

}
