package com.hsptl.appointment.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hsptl.appointment.dto.AppointmentRequestDTO;
import com.hsptl.appointment.dto.AppointmentResponseDTO;
import com.hsptl.appointment.dto.PatientResponseDTO;
import com.hsptl.appointment.dto.RescheduleAppointmentRequestDTO;
import com.hsptl.appointment.service.AppointmentService;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
	
	@Autowired
	AppointmentService appointmentService;
	
	@PostMapping("/newAppointment")
	public ResponseEntity<AppointmentResponseDTO> bookAppointmentFromAppointmentRequestDto(@RequestBody AppointmentRequestDTO appointmentRequestDto ){
		return new ResponseEntity<>(appointmentService.bookAppointmentFromAppointmentRequestDto(appointmentRequestDto),HttpStatus.CREATED);
	}
	
	@PutMapping("/resheduleAppointment/{appointmentId}")
	public ResponseEntity<AppointmentResponseDTO> rescheduleAppointmentFromAppointmentRequestDTO(@RequestBody RescheduleAppointmentRequestDTO rescheduleAppointmentRequestDto,@PathVariable String appointmentId){
		return new ResponseEntity<>(appointmentService.rescheduleAppointmentFromRescheduleAppointmentRequestDTO(rescheduleAppointmentRequestDto,appointmentId),HttpStatus.CREATED);
	}
	
	@GetMapping("/get/allFutureAppointments/{doctorId}")
	public ResponseEntity<List<AppointmentResponseDTO>> getAllFutureAppointmentsByDoctorId(@PathVariable("doctorId") Long doctorId){
		return new ResponseEntity<>(appointmentService.getAllFutureAppointmentsByDoctorId(doctorId),HttpStatus.OK);
	}
	
	@GetMapping("get/allAppointmentsWithDate/{date}")
	public ResponseEntity<List<AppointmentResponseDTO>> getAllAppointmentsByDate(@PathVariable("date") LocalDate date ){
		return new ResponseEntity<>(appointmentService.getAllAppointmentsByDate(date),HttpStatus.OK);
	}

	@GetMapping("get/allAppointmentsWithDate/{doctorId}/{date}")
	public ResponseEntity<List<AppointmentResponseDTO>> getAllAppointmentsByDoctorAndDate(@PathVariable Long doctorId, @PathVariable("date") LocalDate date ){
		return new ResponseEntity<>(appointmentService.getAllAppointmentsByDoctorAndDate(doctorId,date),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{appointmentId}")
	public ResponseEntity<String> deleteAppointmentByAppointmentId(@PathVariable String appointmentId){
		return new ResponseEntity<>(appointmentService.deleteAppointmentByAppointmentId(appointmentId),HttpStatus.OK);
	}
	
	@GetMapping("get/appointmentDetails/{appointmentId}")
	public ResponseEntity<AppointmentResponseDTO> getAppointmentDetailsByAppointmentId(@PathVariable String appointmentId){
		return new ResponseEntity<>(appointmentService.getAppointmentDetailsByAppointmentId(appointmentId),HttpStatus.OK);
	}
	
	@GetMapping("/doctor/{doctorId}/patient-ids")
	public ResponseEntity<List<Long>> getPatientIdsByDoctorAndDatesRange( @PathVariable("doctorId") Long doctorId, 
													@RequestParam("startDateTime") LocalDateTime startDateTime,
													@RequestParam("endDateTime") LocalDateTime endDateTime){
		return new ResponseEntity<>(appointmentService.getPatientIdsByDoctorAndDatesRange(doctorId,startDateTime,endDateTime),HttpStatus.OK);
		
	}
}
