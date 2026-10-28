package com.hsptl.bed.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RoomNotFoundException.class)
	public ResponseEntity<String> handleRoomNotFoundException(RoomNotFoundException roomNotFoundException){
		return new ResponseEntity<>(roomNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BedNotFoundException.class)
	public ResponseEntity<String> handleBedNotFoundException(BedNotFoundException bedNotFoundException){
		return new ResponseEntity<>(bedNotFoundException.getMessage(),HttpStatus.NOT_FOUND);	
	}
	
	@ExceptionHandler(BedUnavailableException.class)
	public ResponseEntity<String> handleBedUnvailableException(BedUnavailableException bedUnavailableException){
		return new ResponseEntity<>(bedUnavailableException.getMessage(),HttpStatus.CONFLICT);
	}
	
	public ResponseEntity<String> handlePatientNotFoundException(PatientNotFoundException patientNotFoundException){
		return new ResponseEntity<>(patientNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
	}
}
