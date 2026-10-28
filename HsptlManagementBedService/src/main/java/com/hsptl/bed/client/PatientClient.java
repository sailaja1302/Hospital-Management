package com.hsptl.bed.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hsptl.bed.dto.PatientResponseDTO;

@FeignClient(name="HsptlManagementPatientService")
public interface PatientClient {
	
	@GetMapping("/patient/{patientId}")
	PatientResponseDTO getPatientByPatientId(@PathVariable Long patientId);

}
