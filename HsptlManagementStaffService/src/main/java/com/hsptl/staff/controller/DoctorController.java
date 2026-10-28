package com.hsptl.staff.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hsptl.staff.dto.DoctorRequestDTO;
import com.hsptl.staff.dto.DoctorResponseDTO;
import com.hsptl.staff.service.DoctorService;
import com.hsptl.staff.service.StaffService;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	DoctorService doctorService;
	
	@Autowired
	StaffService staffService;
	
	@GetMapping("/{doctorId}")
	public ResponseEntity<DoctorResponseDTO> getDoctorByDoctorId(@PathVariable Long doctorId){
		return new ResponseEntity<>(doctorService.getDoctorByDoctorId(doctorId),HttpStatus.OK);
	}
	
	@GetMapping("/isAvailable/{doctorId}")
	public ResponseEntity<Boolean> isDoctorAvailableByDoctorId(@PathVariable Long doctorId){
		return new ResponseEntity<>(doctorService.isDoctorAvailableByDoctorId(doctorId),HttpStatus.OK);
	}
	

}
