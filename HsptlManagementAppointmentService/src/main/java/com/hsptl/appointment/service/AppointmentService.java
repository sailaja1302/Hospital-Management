package com.hsptl.appointment.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.hsptl.appointment.dto.AppointmentRequestDTO;
import com.hsptl.appointment.dto.AppointmentResponseDTO;
import com.hsptl.appointment.dto.PatientResponseDTO;
import com.hsptl.appointment.dto.RescheduleAppointmentRequestDTO;

public interface AppointmentService {
	
	public AppointmentResponseDTO bookAppointmentFromAppointmentRequestDto(@RequestBody AppointmentRequestDTO appointmentRequestDto );

	public AppointmentResponseDTO rescheduleAppointmentFromRescheduleAppointmentRequestDTO(@RequestBody RescheduleAppointmentRequestDTO rescheduleAppointmentRequestDto,@PathVariable String appointmentId);
	
	public List<AppointmentResponseDTO> getAllFutureAppointmentsByDoctorId(@PathVariable Long doctorId);
	
	public List<AppointmentResponseDTO> getAllAppointmentsByDate(@PathVariable("date") LocalDate date );
	
	public String deleteAppointmentByAppointmentId(@PathVariable String appointmentId);
	
	public AppointmentResponseDTO getAppointmentDetailsByAppointmentId(@PathVariable String appointmentId);
	
	public List<Long> getPatientIdsByDoctorAndDatesRange(@PathVariable("doctorId") Long doctorId, 
														@RequestParam("startDateTime") LocalDateTime startDateTime,
														@RequestParam("endDateTime") LocalDateTime endDateTime);
	
	public List<AppointmentResponseDTO> getAllAppointmentsByDoctorAndDate(@PathVariable Long doctorId, @PathVariable("date") LocalDate date );
}
