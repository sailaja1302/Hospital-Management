package com.hsptl.patient.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hsptl.patient.dto.PatientRegisterRequestDTO;
import com.hsptl.patient.dto.PatientResponseDTO;
import com.hsptl.patient.entity.Patient;
import com.hsptl.patient.exception.PatientNotFoundException;
import com.hsptl.patient.service.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {
	
	@Autowired
	PatientService patientService;
	
	@PostMapping("/newPatient")
	public ResponseEntity<PatientResponseDTO> patientResponseFromPatientRegisterRequestDTO(@RequestBody PatientRegisterRequestDTO patientRequest) {
		return new ResponseEntity<>(patientService.patientResponseFromPatientRegisterRequestDTO(patientRequest),HttpStatus.CREATED);
	}

	@PutMapping("/update/{patientId}")
	public ResponseEntity<PatientResponseDTO> updatePatientFromPatientRegisterRequestDTO(@RequestBody PatientRegisterRequestDTO patientRequest , @PathVariable Long patientId){
		return new ResponseEntity<> (patientService.updatePatientFromPatientRegisterRequestDTO(patientRequest,patientId),HttpStatus.OK);
	}
	
	@GetMapping("/get/allPatients")
	public List<PatientResponseDTO> getAllPatients(){
		return (patientService.getAllPatients());
		
	}
	
	@GetMapping("/get/patientId/{patientId}")
	public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable Long patientId){
		return new ResponseEntity<>(patientService.getPatientById(patientId),HttpStatus.OK);
	}
	
	@GetMapping("/get/patientName/{name}")
	public ResponseEntity<PatientResponseDTO> getPatientByName(@PathVariable String name){
		return new ResponseEntity<> (patientService.getPatientByName(name),HttpStatus.OK);
	}
	
	@GetMapping("/appointment/doctor/{doctorId}")
	public ResponseEntity<List<PatientResponseDTO>> getPatientIdsByDoctorAndDatesRange( @PathVariable("doctorId") Long doctorId, 
										@RequestParam("startDateTime") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDateTime startDateTime,
										@RequestParam("endDateTime") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDateTime endDateTime) {
		
		List<PatientResponseDTO> doctorsPatients = patientService.getPatientByDoctorAndDatesRange(doctorId, startDateTime, endDateTime);
	
		if(doctorsPatients.isEmpty()) {
			throw new PatientNotFoundException("No patients found for the Doctor");
		}
		return ResponseEntity.ok(doctorsPatients);
	}
	
	@GetMapping("/{patientId}")
	public ResponseEntity<PatientResponseDTO> getPatientByPatientId(@PathVariable Long patientId){
		return new ResponseEntity<>(patientService.getPatientById(patientId),HttpStatus.OK);
	}
	
}
