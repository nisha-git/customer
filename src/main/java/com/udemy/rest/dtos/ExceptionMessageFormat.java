package com.udemy.rest.dtos;

import java.util.Date;

public class ExceptionMessageFormat {

	private Date time;
	private String message;
	private String details;
	
	
	public ExceptionMessageFormat() {
		
	}

	public ExceptionMessageFormat(Date time, String message, String details) {
		super();
		this.message = message;
		this.details = details;
		this.time = time;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String status) {
		this.message = status;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
}
