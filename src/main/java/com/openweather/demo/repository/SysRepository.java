package com.openweather.demo.repository;

import org.springframework.stereotype.Repository;

import com.openweather.demo.model.Sys;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SysRepository extends JpaRepository<Sys, Integer> {

}
