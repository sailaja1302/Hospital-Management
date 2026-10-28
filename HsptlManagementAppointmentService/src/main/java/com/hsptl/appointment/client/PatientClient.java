package com.hsptl.appointment.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hsptl.appointment.dto.PatientResponseDTO;

@FeignClient(name="HsptlManagementPatientService")
public interface PatientClient {

	@GetMapping("/{patientId}")
	List<Long> getPatientsByPatientId(@PathVariable("patientId") Long patientId);

//	List<String> getPatientNameByPatientId(@PathVariable("patientId") Long patientId);
}
