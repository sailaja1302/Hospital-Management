package com.hsptl.staff.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DoctorNotFoundException.class)
	public ResponseEntity<String> handleDoctorNotFoundException(DoctorNotFoundException doctorNotFoundException){
		return new ResponseEntity<>(doctorNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(StaffNotFoundException.class)
	public ResponseEntity<String> handleStaffNotFoundException(StaffNotFoundException staffNotFoundException){
		return new ResponseEntity<>(staffNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DoctorUnavailableException.class)
	public ResponseEntity<String> handleDoctorUnavailableException(DoctorUnavailableException doctorUnavailableException){
		return new ResponseEntity<>(doctorUnavailableException.getMessage(),HttpStatus.CONFLICT);
	}
}
