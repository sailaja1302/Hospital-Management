package com.hsptl.appointment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hsptl.staff.dto.DoctorResponseDTO;

@FeignClient(name ="HsptlManagementStaffService")
public interface DoctorClient {
	
	@GetMapping("/doctor/{doctorId}")
	DoctorResponseDTO getDoctorByDoctorId(@PathVariable ("doctorId") Long doctorId);

}
