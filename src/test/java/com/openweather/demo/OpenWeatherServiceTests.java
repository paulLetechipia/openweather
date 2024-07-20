package com.openweather.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.openweather.demo.configuration.OpenWeatherConfiguration;
import com.openweather.demo.dao.OpenWeatherResponseDao;
import com.openweather.demo.dto.ResponseDTO;
import com.openweather.demo.model.Clouds;
import com.openweather.demo.model.Coord;
import com.openweather.demo.model.Main;
import com.openweather.demo.model.OpenWeatherResponse;
import com.openweather.demo.model.Sys;
import com.openweather.demo.model.Weather;
import com.openweather.demo.model.Wind;
import com.openweather.demo.repository.CloudsRepository;
import com.openweather.demo.repository.CoordRepository;
import com.openweather.demo.repository.MainRepository;
import com.openweather.demo.repository.OpenWeatherResponseRepository;
import com.openweather.demo.repository.SysRepository;
import com.openweather.demo.repository.WeatherRepository;
import com.openweather.demo.repository.WindRepository;
import com.openweather.demo.service.OpenWeatherServiceImpl;

@SpringBootTest
public class OpenWeatherServiceTests {

	@Mock
    private OpenWeatherConfiguration openWeatherConfiguration;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private OpenWeatherResponseRepository openWeatherResponseRepository;

    @Mock
    private OpenWeatherResponseDao openWeatherResponseDao;

    @Mock
    private CoordRepository coordRepository;

    @Mock
    private CloudsRepository cloudsRepository;

    @Mock
    private MainRepository mainRepository;

    @Mock
    private SysRepository sysRepository;

    @Mock
    private WeatherRepository weatherRepository;

    @Mock
    private WindRepository windRepository;

    @InjectMocks
    private OpenWeatherServiceImpl openWeatherService;
    
    @Mock
    private OpenWeatherServiceImpl openWeatherServiceMock;
    
    private OpenWeatherResponse createOpenWeatherResponse() {
    	OpenWeatherResponse res = new OpenWeatherResponse();
    	OpenWeatherResponse weatherResponse = new OpenWeatherResponse();
    	weatherResponse.setBase("stations");
    	weatherResponse.setVisibility(10000);
    	weatherResponse.setDt(1721444196);
    	weatherResponse.setTimezone(-21600);
    	weatherResponse.setIdApiResponse(Long.valueOf("4011741"));
    	weatherResponse.setName("Durango");
    	weatherResponse.setCod(200);
    	Coord coord = new Coord();
    	coord.setLat(new BigDecimal(24.8333));
    	coord.setLon(new BigDecimal(24.8333));
    	Weather weather = new Weather();
    	weather.setIdWeather(804);
    	weather.setMain("Clouds");
    	weather.setDescription("overcast clouds");
    	weather.setIcon("04n");
    	Main main = new Main();
    	main.setTemp(new BigDecimal(292.94));
    	main.setFeels_like(new BigDecimal(292.83));
    	main.setTemp_min(new BigDecimal(292.94));
    	main.setTemp_max(new BigDecimal(292.94));
    	main.setPressure(1016);
    	main.setHumidity(71);
    	main.setSea_level(1016);
    	main.setGrnd_level(794);
    	Wind wind = new Wind();
    	wind.setSpeed(new BigDecimal(3.81));
    	wind.setDeg(58);
    	wind.setGust(new BigDecimal(8.76));
    	Clouds clouds = new Clouds();
    	clouds.setAll(98);
    	Sys sys = new Sys();
    	sys.setCountry("MX");
    	sys.setSunrise(Long.valueOf("1721391671"));
    	sys.setSunrise(Long.valueOf("1721440186"));
    	return res;
    }
    
    
    @Test
    public void testGetWeather_Success() {
        String city = "Durango";
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather";
        String apiKey = "00d6370031bbd97181ed7674c0f2e939";
        String url = String.format("%s?q=%s&appid=%s", apiUrl, city, apiKey);
        OpenWeatherResponse mockResponse = createOpenWeatherResponse();
        openWeatherService.setRestTemplate(restTemplate);
        when(restTemplate.getForObject(url, OpenWeatherResponse.class)).thenReturn(mockResponse);
        when(openWeatherConfiguration.getApiUrl()).thenReturn(apiUrl);
        when(openWeatherConfiguration.getApiKey()).thenReturn(apiKey);

        ResponseDTO response = openWeatherService.getWeather(city);

        assertEquals(HttpStatus.OK.value(), response.getResponseCode());
        assertEquals("", response.getErrorMessage());

        verify(openWeatherResponseDao, times(1)).saveWeatherResponse(mockResponse);
        verify(openWeatherResponseRepository, never()).save(any(OpenWeatherResponse.class));
    }
    
    @Test
    public void testGetWeather_ClientError() {
        String city = "cualquiera";
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather";
        String apiKey = "00d6370031bbd97181ed7674c0f2e939";
        String url = String.format("%s?q=%s&appid=%s", apiUrl, city, apiKey);
        openWeatherService.setRestTemplate(restTemplate);
        when(restTemplate.getForObject(url, OpenWeatherResponse.class))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));
        when(openWeatherConfiguration.getApiUrl()).thenReturn(apiUrl);
        when(openWeatherConfiguration.getApiKey()).thenReturn(apiKey);

        ResponseDTO response = openWeatherService.getWeather(city);

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getResponseCode());
        assertEquals(HttpStatus.NOT_FOUND.name(), response.getErrorMessage());
        assertEquals(null, response.getResponse());

        verify(openWeatherResponseRepository, never()).save(any());
    }
    
    @Test
    public void testGetWeather_ServerError() {
        String city = "cualquiera";
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather";
        String apiKey = "00d6370031bbd97181ed7674c0f2e939";
        ResponseDTO expectedResponse = new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(), null);
        String url = String.format("%s?q=%s&appid=%s", apiUrl, city, apiKey);
        when(restTemplate.getForObject(url, OpenWeatherResponse.class))
                .thenThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR));
        when(openWeatherConfiguration.getApiUrl()).thenReturn(apiUrl);
        when(openWeatherConfiguration.getApiKey()).thenReturn(apiKey);
        when(openWeatherServiceMock.getWeather(city)).thenReturn(expectedResponse);

        ResponseDTO response = openWeatherServiceMock.getWeather(city);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getResponseCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.name(), response.getErrorMessage());
        assertEquals(null, response.getResponse());

        verify(openWeatherResponseRepository, never()).save(any());
    }
    
    @Test
    public void testGetHistory() {
    	List<OpenWeatherResponse> openWeatherResponseList = new ArrayList<OpenWeatherResponse>();
    	openWeatherResponseList.add(createOpenWeatherResponse());
        
        when(openWeatherResponseRepository.findLastTen()).thenReturn(openWeatherResponseList);

        ResponseDTO response = openWeatherService.getHistory();

        assertEquals(HttpStatus.OK.value(), response.getResponseCode());
        assertEquals("", response.getErrorMessage());
        assertEquals(openWeatherResponseList, response.getResponse());

        verify(openWeatherResponseRepository, times(1)).findLastTen();
    }
	
}
