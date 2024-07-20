package com.openweather.demo;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.openweather.demo.controller.OpenWeatherController;
import com.openweather.demo.dto.ResponseDTO;
import com.openweather.demo.model.CityParam;
import com.openweather.demo.model.OpenWeatherResponse;
import com.openweather.demo.service.OpenWeatherServiceImpl;

@SpringBootTest
public class OpenWeatherControllerTests {

	private MockMvc mockMvc;
	
	@Mock
    private OpenWeatherServiceImpl openWeatherServiceMock;

    @InjectMocks
    private OpenWeatherController openWeatherController;
	
	@Test
    public void testGetWeatherCityPost() throws Exception {
		
		mockMvc = MockMvcBuilders.standaloneSetup(openWeatherController)
                .build();
		
        CityParam cityParam = new CityParam();
        cityParam.setCity("Durango");
        ResponseDTO expectedResponse = new ResponseDTO(200, "", new OpenWeatherResponse());

        when(openWeatherServiceMock.getWeather("Durango")).thenReturn(expectedResponse);

        mockMvc.perform(post("/weatherCityPost")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"city\": \"Durango\"}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage", Matchers.notNullValue()));

        verify(openWeatherServiceMock, times(1)).getWeather("Durango");
    }
	
	@Test
    public void testGetWeatherCity() throws Exception {
		
		mockMvc = MockMvcBuilders.standaloneSetup(openWeatherController)
                .build();
		
        CityParam cityParam = new CityParam();
        cityParam.setCity("Durango");
        ResponseDTO expectedResponse = new ResponseDTO(200, "", new OpenWeatherResponse());

        when(openWeatherServiceMock.getWeather("Durango")).thenReturn(expectedResponse);

        mockMvc.perform(get("/weatherCity")
        		.param("city", "Durango"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage", Matchers.notNullValue()));

        verify(openWeatherServiceMock, times(1)).getWeather("Durango");
    }
	
	@Test
    public void testGetHistory() throws Exception {
		
		mockMvc = MockMvcBuilders.standaloneSetup(openWeatherController)
                .build();
		
        CityParam cityParam = new CityParam();
        cityParam.setCity("Durango");
        ResponseDTO expectedResponse = new ResponseDTO(200, "", new ArrayList<OpenWeatherResponse>());

        when(openWeatherServiceMock.getHistory()).thenReturn(expectedResponse);

        mockMvc.perform(get("/history"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.responseCode").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage", Matchers.notNullValue()));

        verify(openWeatherServiceMock, times(1)).getHistory();
    }
}
