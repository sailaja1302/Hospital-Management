package com.hsptl.patient.client;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="HsptlManagementAppointmentService")
public interface AppointmentClient {
	
	@GetMapping("/appointment/doctor/{doctorId}/patient-ids")
	List<Long> getPatientIdsByDoctorAndDatesRange( @PathVariable("doctorId") Long doctorId, 
													@RequestParam("startDateTime") LocalDateTime startDateTime,
													@RequestParam("endDateTime") LocalDateTime endDateTime);
}
