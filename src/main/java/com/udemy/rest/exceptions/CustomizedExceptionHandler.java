package com.udemy.rest.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.udemy.rest.dtos.ExceptionMessageFormat;

@ControllerAdvice
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler{
	
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public final ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFoundException ex, WebRequest request) {
		ExceptionMessageFormat exceptionMessageFormat = new ExceptionMessageFormat(new Date(),ex.getMessage(),request.getDescription(false));
	  
		return new ResponseEntity<>(exceptionMessageFormat,HttpStatus.NOT_FOUND);
	}

}
