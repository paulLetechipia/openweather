package com.openweather.demo.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class OpenWeatherResponse implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "id_coord")
    private Coord coord;
    
    private String base;
    
    @ManyToOne
    @JoinColumn(name = "id_main")
    private Main main;
    
    private int visibility;
    
    @ManyToOne
    @JoinColumn(name = "id_wind")
    private Wind wind;
    
    @ManyToOne
    @JoinColumn(name = "id_clouds")
    private Clouds clouds;
    
    private long dt;
    
    @ManyToOne
    @JoinColumn(name = "id_sys")
    private Sys sys;
    
    private int timezone;
    private Long idApiResponse;
    private String name;
    private int cod;
    
    @OneToMany
    @JoinTable(
        name = "WeatherResponse_Weather",
        joinColumns = @JoinColumn(name = "weather_response_id"),
        inverseJoinColumns = @JoinColumn(name = "weather_id")
    )
    private List<Weather> weather;

    
    
	public OpenWeatherResponse() {
		super();
	}

	public OpenWeatherResponse(OpenWeatherResponse openWeatherResponse) {
		super();
		this.id = null;
		this.coord = openWeatherResponse.getCoord();
		this.base = openWeatherResponse.getBase();
		this.main = openWeatherResponse.getMain();
		this.visibility = openWeatherResponse.getVisibility();
		this.wind = openWeatherResponse.getWind();
		this.clouds = openWeatherResponse.getClouds();
		this.dt = openWeatherResponse.getDt();
		this.sys = openWeatherResponse.getSys();
		this.timezone = openWeatherResponse.getTimezone();
		this.idApiResponse = openWeatherResponse.getId();
		this.name = openWeatherResponse.getName();
		this.cod = openWeatherResponse.getCod();
		this.weather = openWeatherResponse.getWeather();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Coord getCoord() {
		return coord;
	}

	public void setCoord(Coord coord) {
		this.coord = coord;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public int getVisibility() {
		return visibility;
	}

	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}

	public Wind getWind() {
		return wind;
	}

	public void setWind(Wind wind) {
		this.wind = wind;
	}

	public Clouds getClouds() {
		return clouds;
	}

	public void setClouds(Clouds clouds) {
		this.clouds = clouds;
	}

	public long getDt() {
		return dt;
	}

	public void setDt(long dt) {
		this.dt = dt;
	}

	public Sys getSys() {
		return sys;
	}

	public void setSys(Sys sys) {
		this.sys = sys;
	}

	public int getTimezone() {
		return timezone;
	}

	public void setTimezone(int timezone) {
		this.timezone = timezone;
	}

	public Long getIdApiResponse() {
		return idApiResponse;
	}

	public void setIdApiResponse(Long idApiResponse) {
		this.idApiResponse = idApiResponse;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public List<Weather> getWeather() {
		return weather;
	}

	public void setWeather(List<Weather> weather) {
		this.weather = weather;
	}

	@Override
	public String toString() {
		return "OpenWeatherResponse [id=" + id + ", coord=" + coord + ", base=" + base + ", main=" + main
				+ ", visibility=" + visibility + ", wind=" + wind + ", clouds=" + clouds + ", dt=" + dt + ", sys=" + sys
				+ ", timezone=" + timezone + ", idApiResponse=" + idApiResponse + ", name=" + name + ", cod=" + cod
				+ ", weather=" + weather + "]";
	}

	
	
    
	

}
