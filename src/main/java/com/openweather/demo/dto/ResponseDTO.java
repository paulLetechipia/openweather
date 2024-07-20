package com.openweather.demo.dto;

import java.io.Serializable;

public class ResponseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int responseCode;
	private String errorMessage;
	private Object response;
	
	
	public ResponseDTO() {
		
	}
	
	public ResponseDTO(int responseCode, String errorMessage, Object response) {
		super();
		this.responseCode = responseCode;
		this.errorMessage = errorMessage;
		this.response = response;
	}



	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}
	
	

}
