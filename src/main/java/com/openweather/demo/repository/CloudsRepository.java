package com.openweather.demo.repository;

import org.springframework.stereotype.Repository;

import com.openweather.demo.model.Clouds;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CloudsRepository extends JpaRepository<Clouds, Integer> {

}
