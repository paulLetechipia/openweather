package com.openweather.demo.repository;

import org.springframework.stereotype.Repository;

import com.openweather.demo.model.Coord;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CoordRepository extends JpaRepository<Coord, Integer> {

}
