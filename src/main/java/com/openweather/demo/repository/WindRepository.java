package com.openweather.demo.repository;

import org.springframework.stereotype.Repository;

import com.openweather.demo.model.Wind;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface WindRepository extends JpaRepository<Wind, Integer> {

}
