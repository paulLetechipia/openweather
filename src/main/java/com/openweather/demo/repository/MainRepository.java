package com.openweather.demo.repository;

import org.springframework.stereotype.Repository;

import com.openweather.demo.model.Main;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MainRepository extends JpaRepository<Main, Integer> {

}
