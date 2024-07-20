package com.openweather.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Sys {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer type;
	private Integer idSys;
	private String country;
	private Long sunrise;
	private Long sunset;
	
	public Sys() {
		super();
	}
	
	public Sys(Sys sys) {
		super();
		this.id = null;
		this.type = sys.getType();
		this.idSys = sys.getId();
		this.country = sys.getCountry();
		this.sunrise = sys.getSunrise();
		this.sunset = sys.getSunset();
	}

	public Sys(Integer id, Integer type, Integer idSys, String country, Long sunrise, Long sunset) {
		super();
		this.id = id;
		this.type = type;
		this.idSys = idSys;
		this.country = country;
		this.sunrise = sunrise;
		this.sunset = sunset;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Long getSunrise() {
		return sunrise;
	}

	public void setSunrise(Long sunrise) {
		this.sunrise = sunrise;
	}

	public Long getSunset() {
		return sunset;
	}

	public void setSunset(Long sunset) {
		this.sunset = sunset;
	}

	public Integer getIdSys() {
		return idSys;
	}

	public void setIdSys(Integer idSys) {
		this.idSys = idSys;
	}

	@Override
	public String toString() {
		return "Sys [id=" + id + ", type=" + type + ", idSys=" + idSys + ", country=" + country + ", sunrise=" + sunrise
				+ ", sunset=" + sunset + "]";
	}

	

}
