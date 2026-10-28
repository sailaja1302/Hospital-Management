package com.hsptl.patient.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(PatientNotFoundException.class)
	public ResponseEntity<String> handlePantientNotFoundException(PatientNotFoundException patientNotFoundException){
		return new ResponseEntity<> (patientNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
	}

}
