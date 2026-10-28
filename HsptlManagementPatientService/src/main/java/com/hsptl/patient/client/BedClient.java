package com.hsptl.patient.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="HsptlManagementBedService")
public interface BedClient {
	
	@GetMapping("/bed/assignBed/{bedId}/patientId}")
	Long getBedByBedId(@PathVariable Long bedId);

}
