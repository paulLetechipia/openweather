package com.openweather.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Weather {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer idWeather;
	private String main;
	private String description;
	private String icon;

	public Weather() {
		super();

	}

	public Weather(Weather weather) {
		super();
		this.id = null;
		this.idWeather = weather.getId();
		this.main = weather.getMain();
		this.description = weather.getDescription();
		this.icon = weather.getIcon();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMain() {
		return main;
	}

	public void setMain(String main) {
		this.main = main;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getIdWeather() {
		return idWeather;
	}

	public void setIdWeather(Integer idWeather) {
		this.idWeather = idWeather;
	}

	@Override
	public String toString() {
		return "Weather [id=" + id + ", idWeather=" + idWeather + ", main=" + main + ", description=" + description
				+ ", icon=" + icon + "]";
	}

}
