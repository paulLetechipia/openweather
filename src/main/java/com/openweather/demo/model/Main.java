package com.openweather.demo.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Main {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private BigDecimal temp;
	private BigDecimal feels_like;
	private BigDecimal temp_min;
	private BigDecimal temp_max;
	private Integer pressure;
	private Integer humidity;
	private Integer sea_level;
	private Integer grnd_level;

	public BigDecimal getTemp() {
		return temp;
	}

	public void setTemp(BigDecimal temp) {
		this.temp = temp;
	}

	public BigDecimal getFeels_like() {
		return feels_like;
	}

	public void setFeels_like(BigDecimal feels_like) {
		this.feels_like = feels_like;
	}

	public BigDecimal getTemp_min() {
		return temp_min;
	}

	public void setTemp_min(BigDecimal temp_min) {
		this.temp_min = temp_min;
	}

	public BigDecimal getTemp_max() {
		return temp_max;
	}

	public void setTemp_max(BigDecimal temp_max) {
		this.temp_max = temp_max;
	}

	public Integer getPressure() {
		return pressure;
	}

	public void setPressure(Integer pressure) {
		this.pressure = pressure;
	}

	public Integer getHumidity() {
		return humidity;
	}

	public void setHumidity(Integer humidity) {
		this.humidity = humidity;
	}

	public Integer getSea_level() {
		return sea_level;
	}

	public void setSea_level(Integer sea_level) {
		this.sea_level = sea_level;
	}

	public Integer getGrnd_level() {
		return grnd_level;
	}

	public void setGrnd_level(Integer grnd_level) {
		this.grnd_level = grnd_level;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Main [id=" + id + ", temp=" + temp + ", feels_like=" + feels_like + ", temp_min=" + temp_min
				+ ", temp_max=" + temp_max + ", pressure=" + pressure + ", humidity=" + humidity + ", sea_level="
				+ sea_level + ", grnd_level=" + grnd_level + "]";
	}

	

}
