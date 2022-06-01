package com.LearningSpring.payroll.controllers;

import com.LearningSpring.payroll.models.ApiException;
import com.LearningSpring.payroll.models.ApiRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value = {ApiRequestException.class})
	public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
		HttpStatus badRequest = HttpStatus.NOT_ACCEPTABLE;
		ApiException apiException = new ApiException(
				e.getMessage(),
				badRequest,
				ZonedDateTime.now(ZoneId.of("Z"))
		);
		return new ResponseEntity<>(apiException, badRequest);
	}

}
