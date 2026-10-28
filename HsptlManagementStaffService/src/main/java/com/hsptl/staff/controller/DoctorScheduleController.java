package com.hsptl.staff.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hsptl.staff.dto.DoctorScheduleAvailableDatesDTO;
import com.hsptl.staff.dto.DoctorScheduleUnavailableDatesDTO;
import com.hsptl.staff.service.DoctorScheduleService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/doctorSchedule")
public class DoctorScheduleController {
	
	public final DoctorScheduleService doctorScheduleService;
	
	@DeleteMapping("/markAvailable")
	public ResponseEntity<String> markDoctorAvailable(@RequestBody DoctorScheduleAvailableDatesDTO doctorAvailable){
		return new ResponseEntity<>(doctorScheduleService.markDoctorAvailable(doctorAvailable),HttpStatus.OK);
	}
	
	@PostMapping("/markUnavailable")
	public ResponseEntity<String> markDoctorUnavailable(@RequestBody DoctorScheduleUnavailableDatesDTO doctorUnavailable){
		return new ResponseEntity<>(doctorScheduleService.markDoctorUnavailable(doctorUnavailable),HttpStatus.OK);
	}
	
	
}
