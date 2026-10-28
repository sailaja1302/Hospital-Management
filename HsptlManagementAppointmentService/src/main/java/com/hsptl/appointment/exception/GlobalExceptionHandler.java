package com.hsptl.appointment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AppointmentNotFoundException.class)
	public ResponseEntity<String> handleAppontmentNotFoundException(AppointmentNotFoundException appointmentNotFoundException){
		return new ResponseEntity<>(appointmentNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AppointmentAlreadyExistsException.class)
	public ResponseEntity<String> handleAppointmentAlreadyExistsException(AppointmentAlreadyExistsException appointmentAlreadyExistsException){
		return new ResponseEntity<>(appointmentAlreadyExistsException.getMessage(),HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(InvalidTimeException.class)
	public ResponseEntity<String> handleInvalidTimeException(InvalidTimeException invalidTimeException){
		return new ResponseEntity<>(invalidTimeException.getMessage(),HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(DoctorNotFoundException.class)
	public ResponseEntity<String> handleDoctorNotFoundException(DoctorNotFoundException doctorNotFoundException){
		return new ResponseEntity<>(doctorNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
	}
}
