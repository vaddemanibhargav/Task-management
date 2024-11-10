package com.example.demo.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TaskExceptionHandler extends ResponseEntityExceptionHandler{
@ExceptionHandler(InputParameterValidation.class)
public ResponseEntity<String> validateInputFields(InputParameterValidation exception) {
	return new ResponseEntity<>(TaskConstants.INPUT_VALIDATION_FAILED, HttpStatus.BAD_REQUEST);
}
}
